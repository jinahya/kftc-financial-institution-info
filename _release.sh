#!/bin/sh
# #%L
# kftc-financial-institution-info
# %%
# Copyright (C) 2024 - 2025 Jinahya, Inc.
# %%
# Licensed under the Apache License, Version 2.0 (the "License").
# #L%
#
# Releases this library and deploys the tag to Maven Central, following RELEASE.md:
#
#   1. check preconditions (on develop, clean git status, SSH push URL, not behind
#      origin, JDK 25) -- _refresh.sh has already committed the refresh
#   2. git switch -c release/<release>
#   3. ./mvnw release:prepare release:perform
#   4. merge release/<release>~1 into main
#   5. merge release/<release> into develop
#   6. delete release/<release>
#   7. git push origin main develop <release>
#   then deploy the tag: ./mvnw -Pcentral clean deploy
#
# Versions default to RELEASE.md's rule and are passed to the release plugin as
# -DreleaseVersion / -DdevelopmentVersion: <release> = x.y.z+<today> and develop =
# x.y.z-SNAPSHOT. x.y.z is parsed from develop's pom.xml when it is x.y.z-SNAPSHOT
# (so a structural change is just setting develop to e.g. 2.1.0-SNAPSHOT);
# otherwise (an old x.y.z+date-SNAPSHOT, bumped by the plugin) from the last tag.
# Everything is shown and confirmed once before anything is done.
#
# Usage:
#   sh ./_release.sh                          # release + deploy, default versions
#   sh ./_release.sh --release-version 2.0.2+20261103 --development-version 2.0.2-SNAPSHOT
#   sh ./_release.sh --no-deploy              # release only; deploy later with --deploy-only
#   sh ./_release.sh --deploy-only <release>  # deploy an existing tag
#   sh ./_release.sh -y ...                   # don't ask for confirmation
set -eu

ROOT=$(cd "$(dirname "$0")" && pwd)
cd "$ROOT"

ARTIFACT=kftc-financial-institution-info
GROUP_PATH=io/github/jinahya

RELEASE_VERSION=
DEVELOPMENT_VERSION=
DEPLOY=1
DEPLOY_ONLY=
YES=0
while [ $# -gt 0 ]; do
    case "$1" in
        --release-version) RELEASE_VERSION=${2:?}; shift ;;
        --development-version) DEVELOPMENT_VERSION=${2:?}; shift ;;
        --no-deploy) DEPLOY=0 ;;
        --deploy-only) DEPLOY_ONLY=${2:?}; shift ;;
        -y|--yes) YES=1 ;;
        *) printf 'unknown option: %s\n' "$1" >&2; exit 2 ;;
    esac
    shift
done

STEP=start
say() { STEP=$1; printf '\n==> %s\n' "$1"; }
die() { printf 'ERROR: %s\n' "$1" >&2; exit 1; }
on_exit() {
    rc=$?
    [ "$rc" -eq 0 ] || [ "$STEP" = aborted ] && return
    printf '\nFAILED at: %s\n' "$STEP" >&2
    printf 'branch: %s\n' "$(git symbolic-ref -q --short HEAD || git rev-parse --short HEAD)" >&2
    git --no-pager status --short >&2 || true
    printf 'Nothing is rolled back; see RELEASE.md and continue by hand.\n' >&2
}
trap on_exit EXIT

confirm() {
    [ "$YES" -eq 1 ] && return
    printf '%s [y/N] ' "$1"
    read -r answer
    case "$answer" in y|Y|yes) ;; *) STEP=aborted; printf 'aborted; nothing done\n' >&2; exit 1 ;; esac
}

check_jdk() {
    ./mvnw -v 2>/dev/null | grep -q '^Java version: 25' || die "./mvnw -v must say Java 25"
}

deploy() {
    say "Deploy $1 to Maven Central"
    git rev-parse -q --verify "refs/tags/$1" >/dev/null || die "no such tag: $1"
    git switch -q -d "$1"
    ./mvnw -Pcentral clean deploy
    git switch -q develop
    url="https://repo1.maven.org/maven2/$GROUP_PATH/$ARTIFACT/$(printf %s "$1" | sed 's/+/%2B/g')/$ARTIFACT-$(printf %s "$1" | sed 's/+/%2B/g').pom"
    code=$(curl -s -o /dev/null -w '%{http_code}' "$url" || true)
    if [ "$code" = 200 ]; then
        printf '  on Central: %s\n' "$url"
    else
        printf '  not on repo1 yet (HTTP %s); it can take a while: %s\n' "$code" "$url"
    fi
}

# --- deploy an existing tag only ----------------------------------------------
if [ -n "$DEPLOY_ONLY" ]; then
    check_jdk
    [ -z "$(git status --porcelain)" ] || die "working tree is not clean"
    confirm "Deploy tag $DEPLOY_ONLY to Maven Central?"
    deploy "$DEPLOY_ONLY"
    STEP=done
    exit 0
fi

# --- 1. precondition and versions ---------------------------------------------
say "1. Precondition"
[ "$(git symbolic-ref -q --short HEAD)" = develop ] || die "not on develop"
if [ -n "$(git status --porcelain)" ]; then
    git --no-pager status --short >&2
    die "git status is not clean -- commit (sh ./_refresh.sh does) or clean up first"
fi
case "$(git remote get-url --push origin)" in
    git@*|ssh://*) ;;
    *) die "origin's push URL is not SSH" ;;
esac
git fetch -q origin --tags
for b in develop main; do
    [ "$(git rev-list --count "$b..origin/$b")" -eq 0 ] || die "$b is behind origin/$b"
done
check_jdk

LAST_TAG=$(git describe --tags --abbrev=0 develop)
POM_VERSION=$(./mvnw -q help:evaluate -Dexpression=project.version -DforceStdout 2>/dev/null)
case "$POM_VERSION" in
    *+*) BASE=${LAST_TAG%%+*}; BASE_FROM="last tag $LAST_TAG" ;;
    *-SNAPSHOT) BASE=${POM_VERSION%-SNAPSHOT}; BASE_FROM="pom.xml $POM_VERSION" ;;
    *) die "pom.xml version is not a -SNAPSHOT: $POM_VERSION" ;;
esac
printf '%s\n' "$BASE" | grep -Eq '^[0-9]+\.[0-9]+\.[0-9]+$' || [ -n "$RELEASE_VERSION" ] \
    || die "can't get x.y.z from $BASE_FROM; pass --release-version and --development-version"
TODAY=$(date +%Y%m%d)
: "${RELEASE_VERSION:=$BASE+$TODAY}"
: "${DEVELOPMENT_VERSION:=$BASE-SNAPSHOT}"
git rev-parse -q --verify "refs/tags/$RELEASE_VERSION" >/dev/null && die "tag $RELEASE_VERSION already exists"

printf '  last tag             %s\n' "$LAST_TAG"
printf '  x.y.z                %s (from %s)\n' "$BASE" "$BASE_FROM"
printf '  release version      %s\n' "$RELEASE_VERSION"
printf '  development version  %s\n' "$DEVELOPMENT_VERSION"
printf '  deploy               %s\n' "$([ "$DEPLOY" -eq 1 ] && echo yes || echo no)"
if git diff --quiet "$LAST_TAG" -- src/main/resources; then
    printf '  NOTE: no data change since %s (src/main/resources unchanged)\n' "$LAST_TAG"
fi
confirm "Proceed? This releases and pushes main/develop/tag$([ "$DEPLOY" -eq 1 ] && echo ', then deploys')."

# --- 2. release branch --------------------------------------------------------
say "2. Release branch release/$RELEASE_VERSION"
git switch -q -c "release/$RELEASE_VERSION"

# --- 3. release ---------------------------------------------------------------
say "3. release:prepare release:perform"
./mvnw -B release:prepare release:perform \
    -DreleaseVersion="$RELEASE_VERSION" -DdevelopmentVersion="$DEVELOPMENT_VERSION"

# --- 4. main ------------------------------------------------------------------
say "4. Merge release/$RELEASE_VERSION~1 into main"
git switch -q main
git merge --no-edit "release/$RELEASE_VERSION~1"

# --- 5. develop ---------------------------------------------------------------
say "5. Merge release/$RELEASE_VERSION into develop"
git switch -q develop
git merge --no-edit "release/$RELEASE_VERSION"

# --- 6. cleanup ---------------------------------------------------------------
say "6. Delete release/$RELEASE_VERSION"
git branch -d "release/$RELEASE_VERSION"

# --- 7. push ------------------------------------------------------------------
say "7. Push main, develop and $RELEASE_VERSION"
git push origin main develop "$RELEASE_VERSION"

# --- deploy -------------------------------------------------------------------
if [ "$DEPLOY" -eq 1 ]; then
    deploy "$RELEASE_VERSION"
else
    printf '\nReleased %s. Deploy later with: sh ./_release.sh --deploy-only %s\n' "$RELEASE_VERSION" "$RELEASE_VERSION"
fi
STEP=done

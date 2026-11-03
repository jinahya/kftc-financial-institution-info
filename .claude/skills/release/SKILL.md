---
name: release
description: Release this library and deploy the tag to Maven Central, following `RELEASE.md` -- check develop is clean, release branch, release:prepare/perform, merge into main and develop, push, then deploy the tag. Use when the author runs `/release`, or asks to "release", "cut a release", "deploy the data", usually right after a checked `_refresh.sh` run.
---

# release

Release and deploy by running `_release.sh`, which implements `RELEASE.md` ("Release" steps 1-7, then "Deploy").
Read `RELEASE.md` first; if it and the script disagree, stop and report instead of picking one.

The author normally ran `sh ./_refresh.sh`, which commits the refresh on `develop` when it fully succeeds, and checked
it before calling this skill; don't re-run it, and don't commit anything yourself. The script needs `develop` with a
clean git status; if it isn't, stop and report what is uncommitted.

## Steps

1. **Preview.** Run the script and decline its question, to see the checks and the versions:
   ```sh
   echo n | sh ./_release.sh [--release-version ... --development-version ...] [--no-deploy]
   ```
   If a check fails (not on `develop`, git status not clean, behind `origin`, not JDK 25, tag already exists, ...),
   stop and report it.
   Pass `--release-version`/`--development-version` only when the author gave them; otherwise the defaults are
   right: `x.y.z+<today>` and `x.y.z-SNAPSHOT`, `x.y.z` parsed from `develop`'s `pom.xml` (see "Version" in
   `RELEASE.md`). For a structural change, the author sets `develop` to the new `x.y.z-SNAPSHOT` first.
2. **Confirm once.** Show the author that summary -- versions, deploy yes/no, and the NOTE if there is no data
   change -- and ask once. That answer covers the release commits, the push and the deploy; don't ask again.
3. **Run.** Same arguments, plus `-y`:
   ```sh
   sh ./_release.sh -y [same arguments]
   ```
   It takes a while (the release builds and tests the tag, and deploy waits for Central to validate the bundle);
   run it in the background and report when it finishes.

## When it fails

The script stops at the failing step and prints it with the current branch and status; it never rolls back. Report
that, plus whether `release/<release>` and the tag exist and what was pushed. Don't retry, roll back or delete
anything without the author. If only the deploy failed, the release stands; deploy later with
`sh ./_release.sh --deploy-only <release>`. Deploy needs GPG and the `central` server credentials in
`~/.m2/settings.xml`.

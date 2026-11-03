#!/bin/sh
# #%L
# kftc-financial-institution-info
# %%
# Copyright (C) 2024 - 2025 Jinahya, Inc.
# %%
# Licensed under the Apache License, Version 2.0 (the "License").
# #L%
#
# Installs everything _refresh.sh needs. Safe to re-run: anything already present
# is left as is.
#
#   - Google Chrome  (driven by tools/kftc-download via Playwright's 'chrome' channel)
#   - node + npm     (runs tools/kftc-download)
#   - playwright     (npm package only -- no browser download)
#   - python3        (_textToXlsx.py, stdlib only)
#   - sha1sum        (_sha1.sh)
#   - JDK 25 + Maven (build; the proto profile uses the bundled Gradle wrapper)
#
# Missing tools are installed with Homebrew (macOS). Elsewhere, install the
# reported ones with your package manager and re-run.
#
# Usage:
#   sh ./_install.sh
set -eu

ROOT=$(cd "$(dirname "$0")" && pwd)
cd "$ROOT"

say() { printf '\n==> %s\n' "$1"; }
die() { printf 'ERROR: %s\n' "$1" >&2; exit 1; }
has() { command -v "$1" >/dev/null 2>&1; }

HAS_BREW=0
has brew && HAS_BREW=1
MISSING=""

# need <command> <brew formula> [cask]
need() {
    if has "$1"; then
        printf '  ok       %s\n' "$1"
        return
    fi
    if [ "$HAS_BREW" -eq 1 ]; then
        printf '  install  %s (brew %s)\n' "$1" "$2"
        if [ "${3:-}" = cask ]; then brew install --cask "$2"; else brew install "$2"; fi
    else
        MISSING="$MISSING $1"
    fi
}

# --- command-line tools -------------------------------------------------------
say "Checking tools"
need node node
need npm node
need python3 python
need sha1sum coreutils
need mvn maven
need git git

# --- JDK 25 -------------------------------------------------------------------
JAVA_MAJOR=0
if has java; then
    JAVA_MAJOR=$(java -version 2>&1 | sed -n 's/.*version "\([0-9]*\).*/\1/p' | head -1)
fi
if [ "${JAVA_MAJOR:-0}" -ge 25 ] 2>/dev/null; then
    printf '  ok       java %s\n' "$JAVA_MAJOR"
elif [ "$HAS_BREW" -eq 1 ]; then
    printf '  install  JDK 25 (brew openjdk@25)\n'
    brew install openjdk@25
    # openjdk@25 is keg-only; tell the user how to put it on PATH/JAVA_HOME
    printf '  note     openjdk@25 is keg-only -- set JAVA_HOME, e.g.\n'
    printf '             export JAVA_HOME="%s/libexec/openjdk.jdk/Contents/Home"\n' "$(brew --prefix openjdk@25)"
else
    MISSING="$MISSING jdk25"
fi

# --- Google Chrome ------------------------------------------------------------
case "$(uname -s)" in
    Darwin)
        if [ -d "/Applications/Google Chrome.app" ] || [ -d "$HOME/Applications/Google Chrome.app" ]; then
            printf '  ok       Google Chrome\n'
        elif [ "$HAS_BREW" -eq 1 ]; then
            printf '  install  Google Chrome (brew --cask google-chrome)\n'
            brew install --cask google-chrome
        else
            MISSING="$MISSING google-chrome"
        fi
        ;;
    *)
        if has google-chrome || has google-chrome-stable; then
            printf '  ok       Google Chrome\n'
        else
            MISSING="$MISSING google-chrome"
        fi
        ;;
esac

[ -z "$MISSING" ] || die "missing:$MISSING -- install them and re-run"

# --- downloader dependencies --------------------------------------------------
# Only the playwright npm package; the browser is the Chrome checked above.
say "Installing tools/kftc-download dependencies"
( cd "$ROOT/tools/kftc-download" && PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm ci )

say "Done. Run: sh ./_refresh.sh"

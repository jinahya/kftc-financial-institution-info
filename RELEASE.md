<!--
AGENT INSTRUCTIONS (not rendered). Run them when the author mentions this file or asks to release.

What this file is: the procedure for releasing, and later deploying, this library. It is adapted from the sibling
`jakarta-persistence-applied-book/SUBMODULE_RELEASE.asciidoc` and is still being defined with the author; run only
the steps written here, and never add a step the author has not agreed to.
-->

# Release

Releasing and deploying are separate:

* **Release** makes the tag `x.y.z+<yyyyMMdd>` and pushes `main`, `develop` and the tag. Nothing goes to Central.
* **Deploy** uploads the tag to Maven Central, any time later.

A release normally follows a data refresh: the author runs `sh ./_refresh.sh` (see `_WORKFLOW.md`), checks that it
all worked, and then releases. When the full build succeeds, `_refresh.sh` itself commits the refresh on `develop`
as `Update files on <yyyy-MM-dd>`, so the release starts from a clean `develop`.

`sh ./_release.sh` runs everything below -- Release steps 1-7, then Deploy -- after showing the versions and
asking once (`-y` skips the question; `--no-deploy`, `--deploy-only <release>`,
`--release-version`, `--development-version`; see its header). The steps are written out here so they can also be
done by hand.

## JDK and Maven

Run on JDK 25 (`.java-version` is `25`; check that `./mvnw -v` says 25) and through `./mvnw`, not a system `mvn`.

## Parent

The parent is `io.github.jinahya:jinahya-parent`. Its `maven-release-plugin` configuration is what the steps below
rely on:

```xml
<tagNameFormat>@{project.version}</tagNameFormat>
<pushChanges>false</pushChanges>
<localCheckout>true</localCheckout>
<goals>install</goals>
```

* `tagNameFormat`: the tag is the bare version, e.g. `2.0.1+20260928`.
* `pushChanges` `false`: `release:prepare` pushes nothing; Release step 7 does.
* `goals` `install`: `release:perform` installs the release into `~/.m2/repository` but does not deploy; Deploy does.

## Version

Versions are `x.y.z+<yyyyMMdd>`. Unlike the sibling libraries, which run an `x.y.z` release train, this library
keeps `x.y.z` as long as its overall structure does not change; the date, the data's date, is what moves from one
release to the next (`2.0.1+20260814`, `2.0.1+20260928`, ...). So a data refresh is released as the same `x.y.z`
with a new date, and `x.y.z` changes only with a structural change, which the author decides.

Between releases `develop` holds `x.y.z-SNAPSHOT`, a placeholder for the next release: its date is not known until
that release, so the snapshot has none. `release:prepare` would suggest an incremented `x.y.z` with the old date
(`2.0.2+20260928-SNAPSHOT`), so both versions are always passed explicitly:

* `-DreleaseVersion=x.y.z+<yyyyMMdd>`, the date being the release's;
* `-DdevelopmentVersion=x.y.z-SNAPSHOT`, the same `x.y.z`.

`x.y.z` is the one in `develop`'s `pom.xml` (`x.y.z-SNAPSHOT`), so a structural change is made by setting `develop`
to the new `x.y.z-SNAPSHOT` (e.g. `2.1.0-SNAPSHOT`) before releasing. A `pom.xml` still at an old, plugin-bumped
`x.y.z+<date>-SNAPSHOT` says nothing about `x.y.z`; then it is the one in the last tag.

## Release

Below, `<release>` is the release version, `x.y.z+<yyyyMMdd>` (e.g. `2.0.1+20261103`).

1. **Precondition.** On `develop` with a clean git status (nothing modified, staged or untracked), and `git remote -v`
   shows an SSH URL for `(push)`. If any of these does not hold, stop and report it.
2. **Release branch.**
   ```sh
   git switch -c release/<release>
   ```
3. **Release.**
   ```sh
   ./mvnw release:prepare release:perform -DreleaseVersion=<release> -DdevelopmentVersion=x.y.z-SNAPSHOT
   ```
   `release/<release>` then has two new commits and one new tag:
   * the commit that sets the version to `<release>`, which the tag points at;
   * the commit that sets the version to `x.y.z-SNAPSHOT`;
   * the tag `<release>`.
4. **Main.** Merge the first of those commits, the released version:
   ```sh
   git switch main
   git merge release/<release>~1
   ```
5. **Develop.** Merge the whole release branch, which ends with the snapshot version:
   ```sh
   git switch develop
   git merge release/<release>
   ```
   So `main` always holds the latest released version, and `develop` the next snapshot.
6. **Cleanup.**
   ```sh
   git branch -d release/<release>
   ```
7. **Push.**
   ```sh
   git push origin main develop <release>
   ```

## Deploy

Any time after the release. From `develop`, check out the tag (detached), deploy it, and switch back:

```sh
git switch -d <release>
./mvnw -Pcentral clean deploy && git switch -
```

## Not applicable here

The sibling procedure's submodule pointers, book tag and sibling-dependency SNAPSHOT handling do not apply: this
library is a standalone repository with no runtime dependencies.

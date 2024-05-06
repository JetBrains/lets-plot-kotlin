## Releasing the Project

### Make Version

##### 1. Update CHANGELOG.md file

##### 2. Set release version

- remove _"-SNAPSHOT"_ qualifier (the 'version' property in the root 'build.gradle.kts').

> Also, update the artifacts' version in [README.md](../README.md), [USAGE_BATIK_JFX_JS.md](../USAGE_BATIK_JFX_JS.md) and [geotools.md](../docs/geotools.md).

##### 3. Build and release artifacts to Sonatype repository / Maven Central

- `./gradlew clean`
- `./gradlew build`
- `./gradlew :plot-api:publishAllPublicationsToSonatypeRepository publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository`
- `./gradlew findSonatypeStagingRepository closeAndReleaseSonatypeStagingRepository`

> **Note**: For more details see [PUBLISHING.md](PUBLISHING.md).


##### 4. Prepare to the next dev cycle

- Increment the version and add _"-SNAPSHOT"_ qualifier (the 'version' property in the root 'build.gradle.kts')
- Push all to git and add the version git tag:
  - `git add --all && git commit -m "Release vX.X.X" && git push`
  - `git tag vX.X.X && git push --tags`

##### 5. Re-generate the documentation (HTML)
   
- Update `version` in [build.gradle.kts](../build.gradle.kts) for the `dokka` subproject
  `"dokka" -> <version>`

- `./gradlew dokkaHtml`

- Update `version` in [v.list](../Writerside/v.list) for the Writerside.

- [Build a Web Archive with Writerside](https://www.jetbrains.com/help/writerside/local-build.html).

- Move the content of docs/api-reference/ and of zip file with Web Archive to the docs/kotlin/ directory of the [lets-plot-docs repository](https://github.com/JetBrains/lets-plot-docs).

Lets-Plot Kotlin API URL: https://lets-plot.org/kotlin/index.html  
See also: [docs/README.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/README.md) 


### Update Lets-Plot `library descriptor` in Jupyter Kotlin Kernel

- Edit [lets-plot.json](https://github.com/Kotlin/kotlin-jupyter-libraries/blob/master/lets-plot.json) and 
[lets-plot-gt.json](https://github.com/Kotlin/kotlin-jupyter-libraries/blob/master/lets-plot-gt.json)
in the [kotlin-jupyter-libraries](https://github.com/Kotlin/kotlin-jupyter-libraries) GitHub project.

- Create a PR and get it merged into 'master'.

> **Note**: the new descriptor will become the default only with the next release of the Kotlin Kernel.
> 
> Until then, the `%useLatestDescriptors` line magic must be included in notebooks.    

### Demo Notebooks

##### 1. Edit `binder/environment.yml` file.

This is optional - needed only if newer version of 'kotlin-jupyter-kernel' is required.

##### 2. Update demo notebooks

- Make sure the `%useLatestDescriptors` line magic is present.
- Remove parameters in the `%use lets-plot` line magic.
- Re-run all notebooks.

### Add the GitHub Release:
 
 * Open the link: https://github.com/JetBrains/lets-plot-kotlin/releases/new
 * Fill `Tag version` and `Release title` with the released version: "vX.X.X"
 * Fill the description field - copy from the CHANGELOG.md
 

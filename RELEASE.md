## Releasing the project

### Make version

##### 1. Update CHANGELOG.md file

##### 2. Set release version in 'build.gradle.kts'

- remove _"-alphaN"_ qualifier for the 'version' property.

> Also, update the artifacts' version in [LPK_APP.md](LPK_APP.md).

##### 3. Build and release JVM artifacts to Maven Central

a) Upload to the Nexus staging repository:
        
- `./gradlew :clean`
- `./gradlew :plot-api:publishAllPublicationsToSonatypeRepository publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository`

> **Note**: Publish tasks should be invoked with a single command to avoid splitting the staging repository.
> 
> Check artifacts are uploaded to the Nexus staging repository:
>
> https://oss.sonatype.org/index.html#stagingRepositories
>
> In the "Content" tab enter ‘Path lookup’: org/jetbrains/lets-plot
>
> Note: check SNAPSHOT artifacts at:
>
> https://oss.sonatype.org/index.html#view-repositories;snapshots~browsestorage


b) Publish all artifacts to the Nexus "Releases" repository (from the staging):

- `./gradlew findSonatypeStagingRepository closeSonatypeStagingRepository`

> Check artifacts `lets-plot-kotlin-[jvm, js, kernel, metadata, geotools]` are uploaded to the Nexus Releases repository:
>
> https://oss.sonatype.org/index.html#view-repositories;releases~browsestorage
>
> In the "Browse Storage" tab enter ‘Path lookup’: org/jetbrains/lets-plot


> **Note**: PGP signature is required for publishing.
>
> See:
>
> - https://central.sonatype.org/pages/working-with-pgp-signatures.html
> - [build_settings.template.yml](https://github.com/JetBrains/lets-plot-kotlin/blob/master/build_settings.template.yml)

> **Note**: Individually Lets-Plot Kotlin API artifacts can be published with commands:
>
> - "Kotlin kernel":
>
>  - `./gradlew publishLetsPlotKotlinKernelPublicationToSonatypeRepository`
>
>
> - Multi-platform:
>  - `./gradlew :plot-api:publishKotlinMultiplatformPublicationToSonatypeRepository`
>  - `./gradlew :plot-api:publishJvmPublicationToSonatypeRepository`
>  - `./gradlew :plot-api:publishJsPublicationToSonatypeRepository`
>  
> **Note**: Each publish command will open a separate Nexus staging repository.
> In case you want to upload artifacts to one repository, call the necessary tasks with one command.


##### 4. Prepare to the next dev cycle

- increment the version and add _"-alpha1"_ qualifier (The 'version' property in the root 'build.gradle.kts')
- push the new dev version to GitHub.

##### 5. Re-generate the API Reference (HTML)
   
- Update `version` in [build.gradle.kts](build.gradle.kts) for the `dokka` subproject
  `"dokka" -> <version>`

- `./gradlew dokkaHtml`

- Move the content of docs/api-reference/ to the docs/kotlin/ directory of the [lets-plot-docs repository](https://github.com/JetBrains/lets-plot-docs).

Lets-Plot Kotlin API URL: https://lets-plot.org/kotlin/index.html  
See also: [docs/README.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/README.md) 


### Update Lets-Plot `library descriptor` in Jupyter Kotlin Kernel

- Edit [lets-plot.json](https://github.com/Kotlin/kotlin-jupyter-libraries/blob/master/lets-plot.json) and 
[lets-plot-gt.json](https://github.com/Kotlin/kotlin-jupyter-libraries/blob/master/lets-plot-gt.json)
in the [kotlin-jupyter-libraries](https://github.com/Kotlin/kotlin-jupyter-libraries) GitHub project.

- Create a PR and get it merged into 'master'.

Note: the new descriptor will become the default only with the next release of the Kotlin Kernel.
Until then, the `%useLatestDescriptors` line magic must be included in notebooks.    

### Release demo notebooks

##### 1. Edit `binder/environment.yml` file.

This is optional - needed only if newer version of 'kotlin-jupyter-kernel' is required.

##### 2. Update demo notebooks

- Make sure the `%useLatestDescriptors` line magic is present.
- Remove parameters in the `%use lets-plot` line magic.
- Re-run all notebooks.

##### 3. Push the updated demo notebooks and add "demo-tag":

```
git add --all && git commit -m "Updated demo notebooks, add v1.2.3demos1 repo tag" && git push

git tag v1.2.3demos1 && git push --tags
```

In [README.md](README.md) and [docs/README.md](docs/README.md) update the Binder link with new "demo" tag (above).

### Add the GitHub release:
 
 * Open the link: https://github.com/JetBrains/lets-plot-kotlin/releases/new
 * Fill `Tag version` and `Release title` with the released version: "vX.X.X"
 * Fill the description field - copy from the CHANGELOG.md
 

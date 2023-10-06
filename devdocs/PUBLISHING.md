### Publishing to Local Maven Repository

> **Note**: our custom local Maven repository is located at `<project root>/.maven-publish-dev-repo`.

> **Note**: set **version** to "0.0.0-SNAPSHOT".

`./gradlew :plot-api:publishAllPublicationsToMavenLocalRepository`

`./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenLocalRepository`


### Publishing to Sonatype Maven Repository

> **Note**: When publishing to Sonatype, PGP signature is required.
>
> See: https://central.sonatype.org/pages/working-with-pgp-signatures.html
        
                                                               
#### Credentials
                                 
In the `local.properties` file add the following properties:
```properties
sonatype.username=<your Sonatype username>
sonatype.password=<your Sonatype password>
```

#### SNAPSHOT version

Specify "x.y.z-SNAPSHOT" version in `build.gradle.kts` file.

`./gradlew :plot-api:publishAllPublicationsToSonatypeRepository`

`./gradlew publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository`

> You can find published SNAPSHOT artifacts here https://oss.sonatype.org/index.html#view-repositories;snapshots~browsestorage \
> In the "Browse Storage" tab enter ‘Path lookup’: org/jetbrains/lets-plot


#### "Release" version

  a) Specify RELEASE or PRE-RELEASE (i.e. "x.y.z-alpha1", "x.y.z-rc1" etc.) version in `build.gradle.kts` file.

  b) Upload to the Nexus staging repository:

> **Note**: Publish tasks should be invoked with a single command to avoid splitting of the staging repository.

`./gradlew :plot-api:publishAllPublicationsToSonatypeRepository publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository`

> Check artifacts are uploaded to staging repository:
>
> https://oss.sonatype.org/index.html#stagingRepositories
>
> Should see repository: "orgjetbrainslets-plot-NNNN" (where NNNN is a number)
> with profile: "org.jetbrains.lets-plot".

  c) Publish all artifacts to "Releases" repository (from the staging):

`./gradlew findSonatypeStagingRepository closeAndReleaseSonatypeStagingRepository`

> Check artifacts are uploaded to Nexus Releases repository:
>
> https://oss.sonatype.org/index.html#view-repositories;releases~browsestorage
>
> In the "Browse Storage" tab enter ‘Path lookup’: org/jetbrains/lets-plot


### Miscellanea

Individually Lets-Plot Kotlin API artifacts can be published with commands:

- "Kotlin kernel":
  - `./gradlew publishLetsPlotKotlinKernelPublicationToSonatypeRepository`
                                       

- Multi-platform:
  - `./gradlew :plot-api:publishKotlinMultiplatformPublicationToSonatypeRepository`
  - `./gradlew :plot-api:publishJvmPublicationToSonatypeRepository`
  - `./gradlew :plot-api:publishJsPublicationToSonatypeRepository`

> **Note**: Each publish command will open a separate Nexus staging repository.
> 
> In case you want to upload artifacts to one repository, call the necessary tasks with one command.



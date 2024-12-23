### Publishing to Local Maven Repository

> **Note**: our custom local Maven repository location is `<project root>/.maven-publish-dev-repo`.

> **Note**: make sure that **version** is set to "0.0.0-SNAPSHOT" in `build.gradle.kts`.
```bash
./gradlew :plot-api:publishAllPublicationsToMavenLocalRepository
./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenLocalRepository
./gradlew publishletsPlotKotlinJupyterPublicationToMavenLocalRepository
./gradlew publishletsPlotKotlinGeotoolsJupyterPublicationToMavenLocalRepository
./gradlew publishLetsPlotKotlinJsonPublicationToMavenLocalRepository
```

### Publishing to Sonatype Maven Repository
                   
> **Note**: when publishing a "Release" version to Sonatype, PGP signature is required.
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
```bash
./gradlew :plot-api:publishAllPublicationsToSonatypeRepository
./gradlew publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository
./gradlew publishletsPlotKotlinJupyterPublicationToSonatypeRepository
./gradlew publishletsPlotKotlinGeotoolsJupyterPublicationToSonatypeRepository
./gradlew publishLetsPlotKotlinJsonPublicationToSonatypeRepository
```
> You can find published SNAPSHOT artifacts here https://oss.sonatype.org/index.html#view-repositories;snapshots~browsestorage \
> In the "Browse Storage" tab enter ‘Path lookup’: org/jetbrains/lets-plot

> **Note**: SNAPSHOT artifacts are available at "https://oss.sonatype.org/content/repositories/snapshots" repository.

#### "Release" version

  a) Specify RELEASE or PRE-RELEASE (i.e. "x.y.z-alpha1", "x.y.z-rc1" etc.) version in `build.gradle.kts` file.

  b) Upload to the Nexus staging repository:

> **Note**: Publish tasks should be invoked with a single command to avoid splitting of the staging repository.

```shell
./gradlew :plot-api:publishAllPublicationsToSonatypeRepository \
          publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository \
          publishletsPlotKotlinJupyterPublicationToSonatypeRepository \
          publishletsPlotKotlinGeotoolsJupyterPublicationToSonatypeRepository \
          publishLetsPlotKotlinJsonPublicationToSonatypeRepository
```

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


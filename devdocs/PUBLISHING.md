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

### Publishing to Sonatype Central repository
                   
> **Note**: when publishing a "Release" version to Sonatype, PGP signature is required.
>
> See: https://central.sonatype.org/pages/working-with-pgp-signatures.html
        
                                                               
#### Credentials
                                 
In the `local.properties` file add the following properties:
```properties
sonatype.username=<your Sonatype username>
sonatype.password=<your Sonatype password>
sonatype.profileID=<your Sonatype profile ID>
```

#### SNAPSHOT version

Specify "x.y.z-SNAPSHOT" version in `build.gradle.kts` file.
```bash
./gradlew :plot-api:publishAllPublicationsToMavenRepository
./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenRepository
./gradlew publishletsPlotKotlinJupyterPublicationToMavenRepository
./gradlew publishletsPlotKotlinGeotoolsJupyterPublicationToMavenRepository
./gradlew publishLetsPlotKotlinJsonPublicationToMavenRepository
```

> **Note**: SNAPSHOT artifacts are available at "https://central.sonatype.com/repository/maven-snapshots/" repository.

#### "Release" version

  a) Specify RELEASE or PRE-RELEASE (i.e. "x.y.z-alpha1", "x.y.z-rc1" etc.) version in `build.gradle.kts` file.

  b) Build and publish artifacts to a local build directory:

```shell
./gradlew :plot-api:publishAllPublicationsToMavenRepository \
          publishLetsPlotKotlinGeoToolsPublicationToMavenRepository \
          publishletsPlotKotlinJupyterPublicationToMavenRepository \
          publishletsPlotKotlinGeotoolsJupyterPublicationToMavenRepository \
          publishLetsPlotKotlinJsonPublicationToMavenRepository
```

> Check all artifacts are published to the local directory:
>
> `<project root>/build/maven/artifacts`
>

  c) Package and upload all artifacts to the Sonatype Central repository:

`./gradlew uploadMavenArtifacts`

  d) Check artifacts are uploaded to the Sonatype Central repository and have the status "Validated":

 https://central.sonatype.com/publishing/deployments

  e) Push the button "Publish"

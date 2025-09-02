## Releasing the Project

### Make Version

##### 1. Update CHANGELOG.md file
Also update "What's New" section in the README.md file.

##### 2. Set the Release Version

- remove _"-SNAPSHOT"_ qualifier (the 'version' property in the root [build.gradle.kts](../build.gradle.kts)).

> Update the artifact version in (also GeoTools version if needed): 
>  - [README.md](../README.md)
>  - [USAGE_BATIK_JFX_JS.md](../USAGE_BATIK_JFX_JS.md)
>  - [geospatial-charts.md](../Writerside/topics/geospatial_charts.md).

##### 3. Build and publish artifacts to Sonatype Central repository
                           
```shell
./gradlew clean
./gradlew build

./gradlew :plot-api:publishAllPublicationsToMavenRepository \
          publishLetsPlotKotlinGeoToolsPublicationToMavenRepository \
          publishletsPlotKotlinJupyterPublicationToMavenRepository \
          publishletsPlotKotlinGeotoolsJupyterPublicationToMavenRepository \
          publishLetsPlotKotlinJsonPublicationToMavenRepository


./gradlew uploadMavenArtifacts
```
Go to the Sonatype Central Repository deployments page:

https://central.sonatype.com/publishing/deployments

Check all artifacts were uploaded and validated, then push the "Publish" button.

> **Note**: For more details see [PUBLISHING.md](PUBLISHING.md).


##### 4. Prepare for the next dev cycle

- Increment the version and add _"-SNAPSHOT"_ qualifier (the 'version' property in the root [build.gradle.kts](../build.gradle.kts))
- Push all to git and add the version git tag:
  - `git add --all && git commit -m "Release vX.X.X" && git push`
  - `git tag vX.X.X && git push --tags`
              

### Add the GitHub Release:

* Open the link: https://github.com/JetBrains/lets-plot-kotlin/releases/new
* Fill `Tag version` and `Release title` with the released version: "vX.X.X"
* Fill the description field - copy from the CHANGELOG.md

### Update Lets-Plot `library descriptor` in Jupyter Kotlin Kernel

- Edit [lets-plot.json](https://github.com/Kotlin/kotlin-jupyter-libraries/blob/master/lets-plot.json) and
  [lets-plot-gt.json](https://github.com/Kotlin/kotlin-jupyter-libraries/blob/master/lets-plot-gt.json)
  in the [kotlin-jupyter-libraries](https://github.com/Kotlin/kotlin-jupyter-libraries) GitHub project.

- Create a PR and get it merged into the `master`

> **Note**: the new descriptor will become the default only with the next release of the Kotlin Kernel.
>
> Until then, the `%useLatestDescriptors` line magic must be included in notebooks.


### Demo Notebooks

##### 1. Edit `binder/environment.yml` file.

This is optional: needed only if a newer version of 'kotlin-jupyter-kernel' is required.

##### 2. Update demo notebooks

- Make sure the `%useLatestDescriptors` line magic is present.
- Remove parameters in the `%use lets-plot` line magic.
- Re-run all notebooks.


### Regenerate Documentation Website
      
##### Set Release Version

- In [build.gradle.kts](../docs/dokka/build.gradle.kts) update `libVersion` for the Dokka.

- In [v.list](../Writerside/v.list) update `version` for the Writerside.
  Check the values of `current_year` and `web_root`.

##### Regenerate Documentation Website
                                      
Follow the steps in [DOKKA_AND_WRITERSIDE.md](DOKKA_AND_WRITERSIDE.md) to regenerate the documentation website.

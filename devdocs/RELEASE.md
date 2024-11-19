## Releasing the Project

### Make Version

##### 1. Update CHANGELOG.md file

##### 2. Set release version

- remove _"-SNAPSHOT"_ qualifier (the 'version' property in the root 'build.gradle.kts').

> Update the artifact version in (also GeoTools version if needed): 
>  - [README.md](../README.md)
>  - [USAGE_BATIK_JFX_JS.md](../USAGE_BATIK_JFX_JS.md)
>  - [geospatial-charts.md](../Writerside/topics/geospatial_charts.md).

##### 3. Build and release artifacts to Sonatype repository / Maven Central
                           
```shell
./gradlew clean
./gradlew build

./gradlew :plot-api:publishAllPublicationsToSonatypeRepository \
          publishLetsPlotKotlinGeoToolsPublicationToSonatypeRepository \
          publishletsPlotKotlinJupyterPublicationToSonatypeRepository \
          publishletsPlotKotlinGeotoolsJupyterPublicationToSonatypeRepository \
          publishLetsPlotKotlinJsonPublicationToSonatypeRepository

./gradlew findSonatypeStagingRepository closeAndReleaseSonatypeStagingRepository
```

> **Note**: For more details see [PUBLISHING.md](PUBLISHING.md).


##### 4. Prepare to the next dev cycle

- Increment the version and add _"-SNAPSHOT"_ qualifier (the 'version' property in the root 'build.gradle.kts')
- Push all to git and add the version git tag:
  - `git add --all && git commit -m "Release vX.X.X" && git push`
  - `git tag vX.X.X && git push --tags`

##### 5. Re-generate the documentation (HTML)

- To build documentation, you need to be able to access Docker from the console. Check with the command:
  ```Bash
  docker -v
  ```

- Update `version` in [build.gradle.kts](../build.gradle.kts) for the `dokka` subproject
  `"dokka" -> <version>`

- Update `version` in [v.list](../Writerside/v.list) for the Writerside.
  Check the values of `current_year` and `web_root`.

- Build the docs ([using Docker](https://www.jetbrains.com/help/writerside/build-with-docker.html)):

  ```Bash
  ./docs/build_docs.sh
  ```

- Now the documentation site can be explored locally with the [server](https://www.npmjs.com/package/http-server):
  ```Bash
  http-server docs/build/
  ```

- Go to the docs/kotlin/ directory of the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) repository.
  Remove everything except -lets--plot--kotlin/.
  Then move the contents of the docs/build/ directory there.

- Go to the root of the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) repository
  and run the [sitemap.py](https://github.com/JetBrains/lets-plot-docs/blob/master/utils/sitemap.py) utility
  to re-generate the sitemap.xml file:

  ```Bash
  python utils/sitemap.py -i docs/ -f docs/sitemap.xml
  ```

- Publish the documentation by committing changes to the master branch of the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) repository.

- [Publish the search index to Algolia](https://www.jetbrains.com/help/writerside/configure-search.html#create-the-build-configuration-on-ci-cd):

  - For the first run: add the Algolia admin secret API key (`aligola.key=`) to the [local.properties](../local.properties) file.

  - Run Bash script:

    ```Bash
    ./docs/update_algolia_index.sh
    ```

Lets-Plot Kotlin API URL: https://lets-plot.org/kotlin/api-reference/index.html

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
 

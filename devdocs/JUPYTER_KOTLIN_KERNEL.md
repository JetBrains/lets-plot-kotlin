## Installing Kotlin Kernel

`$ pip install kotlin-jupyter-kernel`

## Library Descriptors Location

Lets-Plot library descriptors are files:
- lets-plot.json
- lets-plot-gt.json

After installing kotlin kernel the "bundled" library descriptors are located in

> /opt/anaconda3/envs/<env name>/lib/python3.7/site-packages/run_kotlin_kernel/libraries/

> **Note:** If the `%useLatestDescriptors` **_line magic_** is included in Jupyter notebook,
> 
> Kotlin kernel will pull the latest repository version of descriptors and store them in this location:
> ~/.jupyter_kotlin/cache/
> 
         
## Notebook Tips and Tricks

- show all resolved jars:
  ```
  :classpath
  ```

- force the Kotlin Kernel to pull from the repository and apply the latest versions of all library descriptors::
  ```
  %useLatestDescriptors
  ```

- substitute the Kernel's version of the `lets-plot` library descriptor with your own descriptor:
  ```
  //%use lets-plot
  %use @/Users/me/Projects/lets-plot-kotlin/lets-plot-dev.json
  ```
 
## Conducting Experiments with Kotlin Kernel Locally

Clear Maven cache:
`$ rm -rf ~/.m2/repository/org/jetbrains/lets-plot`                          

#### Publish artifacts to the local dev-repo:
                                                     
```bash
./gradlew publishJvmPublicationToMavenLocalRepository
./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenLocalRepository
./gradlew publishletsPlotKotlinJupyterPublicationToMavenLocalRepository
./gradlew publishletsPlotKotlinGeotoolsJupyterPublicationToMavenLocalRepository
./gradlew publishLetsPlotKotlinJsonPublicationToMavenLocalRepository
```

It will publish all artifacts to `<project root>/.maven-publish-dev-repo/` folder.

#### Edit the library descriptor (lets-plot.json or lets-plot-gt.json):

> **Tip:** To make sure you have the latest version of the descriptor in "~/.jupyter_kotlin/cache", 
> execute the `%use lets-plot` statement in the notebook.
> The kernel will download a newer descriptor if available.
            
- add _Maven Local_ or _Sonatype SNAPSHOT_ repository or both:
  ```
  "repositories": [
    "file://<path to the project root>/.maven-publish-dev-repo",
    "https://oss.sonatype.org/content/repositories/snapshots"
  ],
  ```

- configure the Lets-Plot Kotlin version (the one that is set in `build.gradle.kts`):
  ```
  "properties": {
    "v": "3.1.2-alpha2",
    "isolatedFrame": ""
  },
  ```
 
- if experimenting with Lets-Plot JS which is not published:
  - Set "js" version to "dev";
> **TBD:** Currently there is no clean way to configure "dev" version for JS.
> The "dirty" way is to add "dev" to the "js" version right in the `VersionChecker.kt`.
> 
> See the `letsPlotJsVersion` property in the `VersionChecker.kt` file.
> 
  - Build "dev" JS package (see [lets-plot/js-package/README.md](https://github.com/JetBrains/lets-plot/blob/master/js-package/README.md));
  - Activate any "env" containing Python;
  - Start local HTTP-server serving the JS dev-version:
    - `$ cd lets-plot` 
    - `$ python -m http.server 8000`

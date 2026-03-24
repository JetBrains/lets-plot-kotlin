## Installing Kotlin Kernel

`$ pip install kotlin-jupyter-kernel`

## Notebook Tips and Tricks

- show all resolved jars:
  ```
  :classpath
  ```

- force the Kotlin Kernel to pull from the repository and apply the latest versions of all library descriptors:
  ```
  %useLatestDescriptors
  ```

## Conducting Experiments with Kotlin Kernel Locally

#### Clear Maven cache:

```bash
rm -rf ~/.m2/repository/org/jetbrains/lets-plot
```                          

#### Publish artifacts:

**Option 1:** Publish to Maven local repository (`~/.m2/repository`):
                              
```bash
./gradlew publishJvmPublicationToMavenLocal && \
./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenLocal && \
./gradlew publishletsPlotKotlinJupyterPublicationToMavenLocal && \
./gradlew publishletsPlotKotlinGeotoolsJupyterPublicationToMavenLocal && \
./gradlew publishLetsPlotKotlinJsonPublicationToMavenLocal
```
             
**Option 2:** Publish to Sonatype Central repository:

Typically, a SNAPSHOT or pre-release version – check the version in `build.gradle.kts` file.

See [PUBLISHING.md](PUBLISHING.md) for details on how to publish to Sonatype Central repository.
         

#### Setup the library descriptors:
                                  
Update versions in the `lets-plot.json` and `lets-plot-gt.json` files (find copies next to this document).

Copy descriptors to the `~/.jupyter_kotlin/libraries` directory (create if it does not exist):

```bash
rm -rf ~/.jupyter_kotlin/libraries && mkdir -p ~/.jupyter_kotlin/libraries
cp devdocs/lets-plot.json ~/.jupyter_kotlin/libraries
cp devdocs/lets-plot-gt.json ~/.jupyter_kotlin/libraries
```
          
#### In the Jupyter notebook:

`%use lets-plot` should work normally. However, if it is used together with Kotlin dataframe, put lets-plot before the `%use dataframe` statement:

```
%useLatestDescriptors
%use lets-plot
%use dataframe
```

Include `LetsPlot.getInfo()` to verify the correct version of the library is used.


#### When the core Lets-Plot JS is not yet published (released):
 
- if experimenting with Lets-Plot JS, which is not published:
  - Set "js" version to "dev";
> **TBD:** Currently, there is no clean way to configure "dev" version for JS.
> The "dirty" way is to add "dev" to the "js" version right in the `VersionChecker.kt`.
> 
> See the `letsPlotJsVersion` property in the `VersionChecker.kt` file.
> 
  - Build "dev" JS package (see [lets-plot/js-package/README.md](https://github.com/JetBrains/lets-plot/blob/master/js-package/README.md));
  - Activate any "env" containing Python;
  - Start local HTTP-server serving the JS dev-version:
    - `$ cd lets-plot` 
    - `$ python -m http.server 8000`

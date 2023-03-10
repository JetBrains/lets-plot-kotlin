Install:
--------
pip install kotlin-jupyter-kernel

Install dev:
------------
`pip install -i https://test.pypi.org/simple/ kotlin-jupyter-kernel`

Build and install Kernel from sources:
--------------------------------------
Github: https://github.com/Kotlin/kotlin-jupyter

`./gradlew install`

If built successfully it will install the kernel to:   
> ~/.ipython/kernels/kotlin

Normally the kernel is installed to conda env.


Library descriptors location
-----------------------------
Lets-Plot library descriptors are files:
- lets-plot.json
- lets-plot-gt.json

After installing kotlin kernel the "bundled" library descriptors are located in

a) After installing via `pip install`:  
> /opt/anaconda3/envs/<env name>/lib/python3.7/site-packages/run_kotlin_kernel/libraries/

b) After installing from local sources via `./gradlew install`:  
> ~/.jupyter_kotlin/cache/libraries/

**Note:** If the `%useLatestDescriptors` **_line magic_** is included in Jupyter notebook,  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; then Kotlin kernel will pull the latest repository version of descriptors and  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; store them in this location:
        
> ~/.jupyter_kotlin/cache/
         

About library descriptors (lets-plot.json, lets-plot-gt.json)
------------------------------------------------------------

 - edit descriptor: to conduct local experiments (see more info below).
 - remove descriptor: force the kernel to pull current published version of descriptor from 'master' at https://github.com/Kotlin/kotlin-jupyter.


--------
In notebook

`:classpath` - shows all resolved jars.

`%useLatestDescriptors` - will force Kotlin Kernel to pull and apply the latest repository version of all library descriptors.
Descriptors location:
~/.jupyter_kotlin/cache

Otherwise, Kotlin Kernel uses 'bundled' descriptors installed to:
> /opt/anaconda3/envs/<env name>/lib/python3.7/site-packages/run_kotlin_kernel/libraries

--------
Conducting experiments with Kotlin Kernel locally.
--------------------------------------------------

- Publish artifacts to the local dev-repo:

`$ ./gradlew publishLetsPlotKotlinKernelPublicationToMavenLocalRepository`  
`$ ./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenLocalRepository`

It will publish `lets-plot-kotlin-api-kernel-<version>` artifact to the `<project root>/.maven-publish-dev-repo/` folder.

- edit "lets-plot.json" (see info above about its location):

    - add Maven Local repository:
      ```
      "repositories": [
        "file://<path to the project root>/.maven-publish-dev-repo"
      ],
      ```
  
    - configure the artifact (i.e. "api") version, for example:
      ```
      "properties": {
        "api": "3.1.2-alpha2",
        "lib": "2.3.0-rc2",
        "js": "2.3.0rc2",
        "isolatedFrame": ""
      },
      ```


**Note:** when editing descriptor in "~/.jupyter_kotlin/cache" always check that you are using the latest descriptor.  
The kernel can download a newer descriptor at any moment.

**Alternatively**, if you do not want to rely on a "cached" descriptor, you can load any of your library descriptors using the **_line magic_**: 
```
%use @/Users/me/Projects/lets-plot-kotlin/lets-plot-dev.json
```
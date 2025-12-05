## Building Documentation with Dokka and Writerside

Lets-Plot Kotlin documentation website (by Writerside): https://lets-plot.org/kotlin/get-started.html. <br>
Lets-Plot Kotlin API reference (by Dokka): https://lets-plot.org/kotlin/api-reference/index.html

             
To build documentation, you need to be able to access Docker from the console. Check with the command:
```Bash
docker -v
```


##### Build the Docs ([Using Docker](https://www.jetbrains.com/help/writerside/build-with-docker.html)):

```Bash
./docs/build_docs.sh
```

Now the documentation site can be explored locally with the [http-server](https://www.npmjs.com/package/http-server) (a simple static HTTP server):
  ```Bash
  http-server docs/build/
  ```

##### Update the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) GitHub Repository.            

- Go to the docs/kotlin/ directory of the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) repository.
  Remove everything **except** -lets--plot--kotlin/, examples/ and releases/.
  Then move the contents of the docs/build/ directory there.

- Go to the root of the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) repository
  and run the [sitemap.py](https://github.com/JetBrains/lets-plot-docs/blob/master/utils/sitemap.py) utility
  to re-generate the sitemap.xml file:

  ```Bash
  python utils/sitemap.py -i docs/ -f docs/sitemap.xml
  ```

- Publish the documentation by committing changes to the “master” branch of the [lets-plot-docs](https://github.com/JetBrains/lets-plot-docs) repository.

##### Update Algolia Search Index
                                     
See: [Writerside: Configure search](https://www.jetbrains.com/help/writerside/configure-search.html#create-the-build-configuration-on-ci-cd)

- For the first run: add the Algolia admin secret API key (`aligola.key=`) to the [local.properties](../local.properties) file.

- Run Bash script:

  ```Bash
  ./docs/update_algolia_index.sh
  ```




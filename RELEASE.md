## Releasing the project

### Make version

##### 1. Update CHANGELOG.md file

##### 2. Set release version in 'build.gradle'

- remove _"-alphaN"_ qualifier for the 'version' property.

##### 3. Build and release JVM artifacts to Maven Central

a) Upload to the Nexus staging repository:

- `./gradlew publishLetsPlotKotlinApiPublicationToMavenRepository`
- `./gradlew publishLetsPlotKotlinApiKernelPublicationToMavenRepository`
- `./gradlew publishLetsPlotKotlinGeoToolsPublicationToMavenRepository`

b) Publish all artifacts to the Nexus "Releases" repository (from the staging):

- `./gradlew closeAndReleaseRepository`

Note: PGP signature is required for publishing.

See:

- https://central.sonatype.org/pages/working-with-pgp-signatures.html
- [build_settings.template.yml](https://github.com/JetBrains/lets-plot-kotlin/blob/master/build_settings.template.yml)

##### 4. Prepare to the next dev cycle

- increment the version and add _"-alpha1"_ qualifier (The 'version' property in 'build.gradle')
- push the new dev version to GitHub.

##### 5. Re-generate the API Reference (HTML)

- `./gradlew dokka`
- push the new version to GitHub with the "Reformat code" and "Update copyright" flags **ON** during the commit.

See also: [api_reference.md](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/api_reference.md) 


### Update the lets-plot `library descriptor` in Jupyter Kotlin Kernel

- Edit the [lets-plot.json](https://github.com/Kotlin/kotlin-jupyter/blob/master/libraries/lets-plot.json)
file in the [kotlin-jupyter](https://github.com/Kotlin/kotlin-jupyter) GitHub project.

- Create a PR and get it merged into 'master'.

Note: the new descriptor will become the default only with the next release of the Kotlin Kernel.
Until then, the `%useLatestDescriptors` line magic must be included in notebooks.    

### Release demo notebooks

##### 1. Edit `binder/environment.yml` file.

This is optional - needed only if newer versions of 'kotlin-jupyter-kernel' or 'numpy' are required.

##### 2. Update demo notebooks

- move from 'docs/examples/jupyter-notebooks-dev' to 'docs/examples/jupyter-notebooks'.
- Make sure the `%useLatestDescriptors` line magic is present.
- Remove parameters in the `%use lets-plot` line magic.
- Re-run all notebooks.

##### 3. Update "demo tag"

In all notebooks update the *demos-tag* part in the Binder URLs.  

For example: the Lets-Plot Kotlin API version being released is 1.2.3. 
Then the *demos-tag* is going to be **v1.2.3demos1*** add all Binder URLs
like:

`https://mybinder.org/v2/gh/JetBrains/lets-plot-kotlin/v1.2.2demos1?...`

need to be updated as: 

`https://mybinder.org/v2/gh/JetBrains/lets-plot-kotlin/v1.2.3demos1?...`

##### 4. Push the updated demo notebooks and a git tag:

`git add --all && git commit -m "Updated demo notebooks binder url to match repo tag v1.2.3demos1" && git push`

`git tag v1.2.3demos1 && git push --tags`


### Add the GitHub release:
 
 * Open the link: https://github.com/JetBrains/lets-plot-kotlin/releases/new
 * Fill `Tag version` and `Release title` with the released version: "vX.X.X"
 * Fill the description field - copy from the CHANGELOG.md

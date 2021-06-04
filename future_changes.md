## [3.0.0] - 2021-06-??

### Added

- In tooltip customization API:
  - `layerTooltips(variables)` - the new parameter `variables` defines a list of variable names, which values will be
    placed in the general multiline tooltip.
    See: [Tooltip Customization](https://github.com/JetBrains/lets-plot/blob/master/docs/tooltips.md#variables).

### Changed

- **[BREAKING CHANGE]**: The CDN for delivering the Lets-Plot JavaScript library is
  now [JSDELIVR](https://www.jsdelivr.com/?docs=gh)
  (was CDNJS).

  New URLs:
  - Lets-Plot v2.0.3: https://cdn.jsdelivr.net/gh/JetBrains/lets-plot@v2.0.3/js-package/distr/lets-plot.min.js
  - The latest version: https://cdn.jsdelivr.net/gh/JetBrains/lets-plot/js-package/distr/lets-plot.min.js


- The project was converted to a "multiplatform" project targeting JVM and JS platforms.

  To use Lets-Plot Kotlin API in your project include dependencies:
  - JVM:  `implementation "org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.0.0"`
  - JS:   `implementation "org.jetbrains.lets-plot:lets-plot-kotlin-js:3.0.0"`


- **[BREAKING CHANGE]** The JVM artifact `lets-plot-kotlin-api` no longer deployed: replaced with the
  equivalent `lets-plot-kotlin-jvm`.

- The artifact `lets-plot-kotlin-api-kernel` renamed to `lets-plot-kotlin-kernel` (this change only concerns Kotlin
  Jupyter Kernel)

### Fixed

- Poor font rendering in Swing/Batik. Related to:  [[#364](https://github.com/JetBrains/lets-plot/issues/364)]
- Exclude slf4j implementation from lets-plot-common [[#374](https://github.com/JetBrains/lets-plot/issues/374)]
- geom_boxplot: should be possible to create boxplot without specifying
  x-series [[#325](https://github.com/JetBrains/lets-plot/issues/325)]
- geom_hline: graph plotted outside of coordinate plane visible
  part [[#334](https://github.com/JetBrains/lets-plot/issues/334)]
- Draw geometry only once if layer has no aes mapping
  specified [[#73](https://github.com/JetBrains/lets-plot/issues/73)]
- map: calif.housing [[#140](https://github.com/JetBrains/lets-plot/issues/140)]
- Can't build plot: "Uncaught SyntaxError: Unexpected string" in a
  console [[#371](https://github.com/JetBrains/lets-plot/issues/371)]
- All scales should have the 'format' parameter [[#347](https://github.com/JetBrains/lets-plot/issues/347)].

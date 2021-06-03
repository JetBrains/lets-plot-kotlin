- Artifact renamed:
  'lets-plot-kotlin-api-kernel' --> 'lets-plot-kotlin-kernel'

(now deployed with: ./gradlew publishLetsPlotKotlinKernelPublicationToMavenLocalRepository )

- Artifact no longer deployed: 'lets-plot-kotlin-api' Use new: 'lets-plot-kotlin-jvm'


- Multiplatform artifacts:
  lets-plot-kotlin    
  lets-plot-kotlin-js    
  lets-plot-kotlin-jvm    
  lets-plot-kotlin-metadata

## [3.0.0] - 2021-06-??

### Changed

### Added

- In tooltip customization API:
  - `layerTooltips(variables)` - the new parameter `variables` defines a list of variable names, which values will be
    placed line by line in the general tooltip.
    See: [Tooltip Customization](https://github.com/JetBrains/lets-plot/blob/master/docs/tooltips.md#variables).

### Fixed

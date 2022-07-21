## [4.0.0] - 2022-xx-xx

### Added

### Changed

- All previously deprecated API was removed.

* Prefix "org" added to all the package names in the
  project [[#115](https://github.com/JetBrains/lets-plot-kotlin/issues/115)].

- Some definitions were moved from the `org.jetbrains.letsPlot` package to more specific sub-packages:
    - Plot theme types and functions moved to the `themes` sub-package
    - Coordinate system functions moved to the `coord` sub-package
    - Position adjustment functions moved to the `pos` sub-package

* Deprecations:
    - Position adjustment constants `identity, stack, fill, dodge, nudge` and `jitterdodge` defined in the `Pos` object
      were deprecated in favor of top level elements defined in the `org.jetbrains.letsPlot.pos` package:
      `positionIdentity, positionStack, positionFill, positionDodge(), positionNudge()` and `positionJitterDodge()`
      respectively.

### Fixed

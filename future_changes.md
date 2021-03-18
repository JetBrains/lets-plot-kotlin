## [1.3.0] - 2021-xx-xx

### Added

- `facet_wrap()` function.
- in `facet_grid()` function:
  - Ascending/descending ordering of faceting values.
  - Formatting of faceting values.

  See: [Facets demo](ToDo)

- The `guides()` function [[#52](https://github.com/JetBrains/lets-plot/issues/52)].

### Changed

- Built with Lets-Plot v2.0.1 (was v1.5.6).
- All Java Swing demos were updated to use new `plot components` (new in Lets-Plot v2.0.1).
- The "Minimal demo" was updated:
    - [`Main.kt` (Batik)](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/jvm-batik/src/main/kotlin/minimalDemo)
    - [`Main.kt` (JavaFX)](https://github.com/JetBrains/lets-plot-kotlin/tree/master/demo/jvm-javafx/src/main/kotlin/minimalDemo)

### Fixed

- `show()` should actually show a plot [[#51](https://github.com/JetBrains/lets-plot-kotlin/issues/51)]
- Facet grid truncated in jupyter [[#28](https://github.com/JetBrains/lets-plot-kotlin/issues/28)].

The majority of fixes in the core Lets-Plot vv 2.0.0, 2.0.1 are also applicable to this release.

See the Lets-Plot [CHANGELOG.md](https://github.com/JetBrains/lets-plot/blob/master/CHANGELOG.md).



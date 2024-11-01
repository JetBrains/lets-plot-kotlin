## [4.9.0] - 2024-11-dd

This release is 100% compatible with Lets-Plot [v 4.5.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.5.1).

### Added

- `ggtb()`: enable **zoom/pan** interactivity on plot [[#38](https://github.com/JetBrains/lets-plot-kotlin/issues/38)],[[#983](https://github.com/JetBrains/lets-plot/issues/983)],[[#1019](https://github.com/JetBrains/lets-plot/issues/1019)]

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/interact_pan_zoom.ipynb).

- Interactive **links** in tooltips/labels/texts [[#1091](https://github.com/JetBrains/lets-plot/issues/1091)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/lp_verse.ipynb).

- Formatting:
  - **LaTeX** support: superscript, subscript ([[#861](https://github.com/JetBrains/lets-plot/issues/861)]) and Greek letters ([[#960](https://github.com/JetBrains/lets-plot/issues/960)]).

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/latex_support.ipynb).

  - Scientific notation: **compact form**. Enable the compact form using the  `exponentFormat` parameter in `theme()` [[#1071](https://github.com/JetBrains/lets-plot/issues/1071)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/superscript_exponent.ipynb).

- In `theme()`:
  - parameters `legendMargin, legendSpacing, legendSpacingX, legendSpacingY, legendBoxSpacing`,
  
    and functions `legendBoxHorizontal/Vertical(), legendBoxJustificationLeft/Right/Top/Bottom/Center()` [[#1180](https://github.com/JetBrains/lets-plot/issues/1180)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/theme_legend_margins.ipynb).

  - parameters `legendKey, legendKeySize/Width/Height/Spacing/SpacingX/SpacingY`[[#1181](https://github.com/JetBrains/lets-plot/issues/1181)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/theme_legend_key.ipynb).

  - parameters `stripBackgroundX/Y, stripTextX/Y` [[#1195](https://github.com/JetBrains/lets-plot/issues/1195)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/theme_facet_strip_xy.ipynb).

- Custom `linetype` patterns [[#1198](https://github.com/JetBrains/lets-plot/issues/1198)]:
  - a list specifying the pattern of dashes and gaps used to draw the line: `listOf(dash, gap, dash, gap, ...)`;
  - a list with a specified offset: `listOf(offset, listOf(dash, gap, dash, gap, ...))`;
  - a string of an even number (up to eight) of hexadecimal digits specifying the lengths in consecutive positions in the string.

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/linetype_custom.ipynb).

- Geometries:
  - `geomBlank()` [[#831](https://github.com/JetBrains/lets-plot/issues/831)].

  - `base` parameter in `waterfallPlot()` [[#1159](https://github.com/JetBrains/lets-plot/issues/1159)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/waterfall_plot_base.ipynb).

  - `checkOverlap` parameter in `geomText()` and `geomLabel()`.

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/check_overlap.ipynb).

  - `marginal` parameter in `qqPlot()`:

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/qq_plot_marginal.ipynb).

  - `inheritAes` parameter in layers [[#1172](https://github.com/JetBrains/lets-plot/issues/1172)].

- `expandLimits()` [[#820](https://github.com/JetBrains/lets-plot/issues/820)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/check_overlap.ipynb).

- Support for 3-character hex color codes
              

### Changed

- Parameters `labwidth` in `facetWrap()`, `xLabwidth, yLabwidth` in `facetGrid()`: the maximum label length is applied after splitting at `\n` separators, if present.

- `themeLight()` is now the default theme in `qqPlot()`.

- [**BREAKING**] For API deprecated in v4.0 the deprecation level raised to "Error".
              

### Fixed

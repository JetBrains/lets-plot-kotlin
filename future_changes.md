## [4.9.0] - 2024-11-dd

This release is 100% compatible with Lets-Plot [v 4.5.1](https://github.com/JetBrains/lets-plot/releases/tag/v4.5.1).

### Added

- ToDo: `ggtb()`: enable **zoom/pan** interactivity on plot [[#983](https://github.com/JetBrains/lets-plot/issues/983)],[[#1019](https://github.com/JetBrains/lets-plot/issues/1019)]

  ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/interact_pan_zoom.ipynb).

- Interactive **links** in tooltips/labels/texts [[#1091](https://github.com/JetBrains/lets-plot/issues/1091)].

  ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/interactive_links.ipynb).

- ToDo: Formatting:
  - **LaTeX** support: superscript, subscript ([[#861](https://github.com/JetBrains/lets-plot/issues/861)]) and Greek letters ([[#960](https://github.com/JetBrains/lets-plot/issues/960)]).

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/dev/notebooks/latex_support.ipynb).

  - Scientific notation: **compact form**. Enable the compact form using the  `exponent_format` parameter in `theme()` [[#1071](https://github.com/JetBrains/lets-plot/issues/1071)].

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/superscript_exponent.ipynb).

- ToDo: In `theme()`:
  - `legend_margin, legend_spacing, legend_spacing_x, legend_spacing_y, legend_box, legend_box_just, legend_box_spacing` parameters [[#1180](https://github.com/JetBrains/lets-plot/issues/1180)].

    ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/theme_legend_margins.ipynb).

  - `legend_key, legend_key_size, legend_key_width, legend_key_height, legend_key_spacing, legend_key_spacing_x, legend_key_spacing_y` parameters  [[#1181](https://github.com/JetBrains/lets-plot/issues/1181)].

    ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/theme_legend_key.ipynb).

  - `strip_background_x, strip_background_y, strip_text_x, strip_text_y` parameters [[#1195](https://github.com/JetBrains/lets-plot/issues/1195)].

    ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/theme_facet_strip_xy.ipynb).

- Custom `linetype` patterns [[#1198](https://github.com/JetBrains/lets-plot/issues/1198)]:
  - a list specifying the pattern of dashes and gaps used to draw the line: `listOf(dash, gap, dash, gap, ...)`;
    - a list with a specified offset: `listOf(offset, listOf(dash, gap, dash, gap, ...))`;
  - a string of an even number (up to eight) of hexadecimal digits specifying the lengths in consecutive positions in the string.

  ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/linetype_custom.ipynb).

- Geometries:
  - `geomBlank()` [[#831](https://github.com/JetBrains/lets-plot/issues/831)].

  - ToDo: `base` parameter in `waterfall_plot()` [[#1159](https://github.com/JetBrains/lets-plot/issues/1159)].

    ToDo: See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/f-24g/waterfall_plot_base.ipynb).

  - `checkOverlap` parameter in `geomText()` and `geomLabel()`.

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/check_overlap.ipynb).

  - `marginal` parameter in `qqPlot()`:

    See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/qq_plot_marginal.ipynb).

  - `inheritAes` parameter in layers [[#1172](https://github.com/JetBrains/lets-plot/issues/1172)].

- `expandLimits()` [[#820](https://github.com/JetBrains/lets-plot/issues/820)].

  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot/blob/master/docs/examples/jupyter-notebooks/f-4.9.0/check_overlap.ipynb).

- Support for 3-character hex color codes
              

### Changed

- ToDo: Parameters `labwidth` in `facetWrap()`, `x_labwidth, y_labwidth` in `facet_grid()`: the maximum label length is applied after splitting at `\n` separators, if present.

- `themeLight()` is now the default theme in `qqPlot()`.

- [**BREAKING**] For API deprecated in v4.0 the deprecation level raised to "Error".
              

### Fixed

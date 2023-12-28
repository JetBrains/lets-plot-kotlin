## [4.5.1] - 2023-mm-dd

### Added

- `scaleXLog2(), scaleYLog2()` [[#922](https://github.com/JetBrains/lets-plot/issues/922)].

- New variables computed by `'count'` and `'count2d'` statistics: `'..sumprop..'`, `'..sumpct..'`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/new_stat_count_vars.ipynb).

- Support using dictionaries for breaks/labels/values customization in `scaleXxx()` functions [[#169](https://github.com/JetBrains/lets-plot/issues/169)], [[#882](https://github.com/JetBrains/lets-plot/issues/882)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/scale_params_with_dict.ipynb).

- The `lablim` parameter for `scaleXxx()` functions [[#939](https://github.com/JetBrains/lets-plot/issues/939), [#946](https://github.com/JetBrains/lets-plot/issues/946)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/scale_lablim.ipynb).

- The `levels` parameter in `asDiscrete()` function [[#931](https://github.com/JetBrains/lets-plot/issues/931)].

  See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot/blob/master/docs/f-23f/factor_levels.ipynb).

- `labelText` parameter in `theme()` for annotation text settings [[#930](https://github.com/JetBrains/lets-plot/issues/930)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/theme_label_text.ipynb).

- Formatting: add scientific superscript option [[#743](https://github.com/JetBrains/lets-plot/issues/743)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/superscript_exponent.ipynb).


### Changed

- [BREAKING] Function `margin()` is deprecated and will be removed in future releases. <br/>
  Please replace all existing usages, i.e. `theme(plotMargin=margin(..))` and `elementText(margin=margin(..))` <br/>
  with a list or with just a number:
  - a number or list of one number - the same margin it applied to **all four sides**;
  - a list of two numbers - the first margin applies to the **top and bottom**, the second - to the **left and right**;
  - a list of three numbers -  the first margin applies to the **top**, the second - to the **right and left**,
    the third - to the **bottom**;
  - a list of four numbers - the margins are applied to the **top, right, bottom and left** in that order.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/margins.ipynb).


### Fixed

- Jitter reproducibility in geomJitter, positionJitter, positionJitterDodge [[#911](https://github.com/JetBrains/lets-plot/issues/911)].
- Bug with Tooltips in Swing/Batik [[#225](https://github.com/JetBrains/lets-plot-kotlin/issues/225)].
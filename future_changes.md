## [4.5.1] - 2023-mm-dd

### Added

- New variables computed by `'count'` and `'count2d'` statistics: `'..sumprop..'`, `'..sumpct..'`.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/new_stat_count_vars.ipynb).

- The `lablim` parameter for `scaleXxx()` functions [[#939](https://github.com/JetBrains/lets-plot/issues/939), [#946](https://github.com/JetBrains/lets-plot/issues/946)].

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/scale_lablim.ipynb).


### Changed

- The `plotMargin` parameter in `theme()` and the `margin` parameter in `elementText()` accept a number or a list of numbers:
  - a number or list of one number - the same margin it applied to **all four sides**;
  - a list of two numbers - the first margin applies to the **top and bottom**, the second - to the **left and right**;
  - a list of three numbers -  the first margin applies to the **top**, the second - to the **right and left**,
    the third - to the **bottom**;
  - a list of four numbers - the margins are applied to the **top, right, bottom and left** in that order.

  See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.5.1/margins.ipynb).


### Fixed

- Jitter reproducibility in geomJitter, positionJitter, positionJitterDodge [[#911](https://github.com/JetBrains/lets-plot/issues/911)].
- `scaleXLog2(), scaleYLog2()` as a shortcut for `trans='log2'` [[#922](https://github.com/JetBrains/lets-plot/issues/922)].
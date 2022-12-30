## [4.2.0] - 2022-12-dd

### Added

- `residualPlot()`.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/residual_plot.ipynb).

- `geomAreaRidges()`.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/ridgeline_plot.ipynb).

- `geomPie()`.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/geom_pie.ipynb).

- Annotations for pie chart:
  
    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/annotations_for_pie.ipynb).

- New variables computed by 'count' and 'count2d' statistics: '..sum..', '..prop..', '..proppct..'.
  
    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/stat_count_2d.ipynb).

- `geomImshow()`:

  See: [image_101](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_101.ipynb),
  [image_fisher_boat](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_fisher_boat.ipynb),
  [image_grayscale](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_grayscale.ipynb),
  [image_extent](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/image_extent.ipynb)

- New parameters in `geomViolin()`:
  - `tailsCutoff`
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/violin_tails_cutoff.ipynb))
  - `showHalf`
    ([example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/violin_show_half.ipynb)) 

- Static maps:
  - `useCRS` parameter with value "provided" for `geomMap()` and other geoms, working with `SpatialDataset`.

    See: [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.2.0/projection_provided.ipynb).


### Changed

- Upgraded Lets-Plot version to 3.0.0 (was 2.5.1).
- Java/Swing platf.: Apache Batik upgraded to v.1.16 [[#624](https://github.com/JetBrains/lets-plot/issues/624)], [[LPK #140](https://github.com/JetBrains/lets-plot-kotlin/issues/140)].
- The default size is increased for the plot title and decreased for the caption.
- Upgraded Kotlin version to 1.7.21 (was 1.7.20).


### Fixed

- Themes: can't change plot background after applying a "flavor" [[#623](https://github.com/JetBrains/lets-plot/issues/623)].
- Layout: uneven left/right, top/bottom plot margins [[#625](https://github.com/JetBrains/lets-plot/issues/625)].
- A plot building error with empty data on various geoms.
- Precision error in gradient [[#634](https://github.com/JetBrains/lets-plot/issues/634)].

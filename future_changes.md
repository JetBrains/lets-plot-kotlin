## [4.7.3] - 2024-mm-dd

### Added
- Support for `angle` aesthetic in `geomPoint()` [[#736](https://github.com/JetBrains/lets-plot/issues/736)].  
  See [example notebook](https://nbviewer.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/f-4.7.3/geom_point_angle.ipynb).

### Changed

### Fixed
- Undesired vertical scroller when displaying `gggrid` in Jupyter notebook.
- GeoJson structure breaks if the ring start label occurs several times [[#1086](https://github.com/JetBrains/lets-plot/issues/1086)].
- `theme`: left margin doesn't work for the `plot_title` parameter [[#1101](https://github.com/JetBrains/lets-plot/issues/1101)].
- Improve border line type experience [[LPK-220](https://github.com/JetBrains/lets-plot-kotlin/issues/220)].
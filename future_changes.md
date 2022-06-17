## [3.2.1] - 2022-xx-xx

### Added
- Global theme configuring with `LetsPlot.theme` property.
- `kotlinx.datetime` support.
- `arrow` parameter in `geomSegment` - specification for arrow heads, as created by `arrow()` function.
- `scaleColorGradientN()` and `scaleFillGradientN()` functions.
- New parameters in `theme()`:
    - `tooltip` - tooltip rectangle options;
    - `tooltipText, tooltipTitleText` - tooltip text options;
    - `axisTooltipText, axisTooltipTextX, axisTooltipTextY` - axis tooltip text options.
    - `panelBorder` parameter in `theme()`.
- `face` parameter in `elementText()`.
- Quantile-Quantile (Q-Q) plot:
  - geometries:
    - `geomQQ()`
    - `geomQQLine()`
    - `geomQQ2()`
    - `geomQQ2Line()`
  - stats:
    - `statQQ()`
    - `statQQLine()`
    - `statQQ2()`
    - `statQQ2Line()`    
  - the `qqPlot()` function.

### Changed
- `geomImage()` removed as it is not yet production ready.


### Fixed

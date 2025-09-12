## [4.11.2] - 2025-mm-dd

This release is 100% compatible with Lets-Plot [v 4.7.3](https://github.com/JetBrains/lets-plot/releases/tag/v4.7.3),
GeoTools [v 33.2](https://github.com/geotools/geotools/releases/tag/33.2)

### Added

### Changed
- `ggsave()`: Large plot dimensions without units now require explicit unit specification. <br>
  When plot size exceeds 20 without specifying units (e.g., `ggsave(p, w=300, h=400)`), <br>
  we ask to specify units explicitly: <br>
  `ggsave(p, w=300, h=400, unit="px")` or `ggsave(p, w=3, h=4, unit="in")`.

### Fixed
- Multiline support for axis labels in polar coordinates.
- When the plot size in `ggsave()` is specified in pixels, `dpi` now affects <br>
  only the physical size, not the pixel dimensions as before.
- Blocking `SwingUtilities.invokeAndWait()`  call in plot image export (AWT backend)

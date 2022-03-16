## [3.1.2] - 202x-xx-xx

### Added

- Plot subtitle and caption:
  `subtitle` parameter in `ggtitle()` and `labs()`,
  `caption` parameter in `labs()`,
  `plotSubtitle` and `plotCaption` parameters in `theme()`.
    
    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks-dev/title_subtitle_caption.ipynb).

- New in tooltip customization API: the `title()` option defines a tooltip "title" text which will always appear above the rest of the tooltip content.

    See: [example notebook](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks-dev/tooltip_title.ipynb).

### Changed
             
- Deprecated tooltip customization API: function `color()` will be removed in one of the future releases. 

### Fixed

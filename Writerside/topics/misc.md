# Miscellaneous

## Panning and Zooming

Use the [`ggtb`](%api_interact%/ggtb.html) function to enable ``Pan`` and ``Zoom`` interactivity on a chart.

This function adds a toolbar containing three tool-buttons: pan, rubber-band zoom, and center-point zoom.

See: [interact_pan_zoom.ipynb](%nb-interact_pan_zoom%)

<img src="interact_pan_zoom.png" alt="Zoom and Pan interactivity" width="480"/>

## Extended Text Markup

In tooltips/labels/texts and wherever else there is text, you can use:

- Interactive links, e.g. `<a href="https://github.com">GitHub</a>`.

  See: [lp_verse.ipynb](%nb-lp_verse%)

  <img src="lp_verse.png" alt="The observable LP-verse" width="480"/>

- LaTeX formulas with

    - superscript, e.g. `\( a^b \)`;
    - subscript, e.g. `\( x_i \)`;
    - Greek letters, e.g. `\( \Omega \)`, and
    - some special symbols, e.g. `\( a \cdot b \neq c \)`.

  See: [latex_support.ipynb](%nb-latex_support%)

  <img src="latex_support.png" alt="LaTeX Support" width="480"/>

## Manual Legend

In Lets-Plot, as in ggplot2, legends are automatically generated based on the aesthetic mappings in the plot.
Sometimes, however, this automatic generation doesn't provide the precise control needed for complex visualizations.
Parameter `manualKey` and aesthetic parameters of the [`guideLegend()`](%api_scale%/guide-legend.html) function addresses this limitation.

See: [manual_legend.ipynb](%nb-manual_legend%)

<img src="manual_legend.png" alt="Custom Legend with manualKey Option" width="480"/>

## Multiple Color Scales

Use `colorBy`/`fillBy` parameters and `paint_a`/`paint_b`/`paint_c` aesthetics if you need to display two different layers with the same color aesthetic but different color scales.

See: [multiple_color_scales.ipynb](%nb-multiple_color_scales%)

<img src="multiple_color_scales.png" alt="Multiple color scales" width="480"/>

## Scale Functions

To specify a scale for any group of aesthetics, use the special scale functions: [`scaleManual()`](%api_scale%/scale-manual.html), [`scaleContinuous()`](%api_scale%/scale-continuous.html), [`scaleGradient()`](%api_scale%/scale-gradient.html), etc.

See: [scale_functions.ipynb](%nb-scale_functions%)

<img src="scale_functions.png" alt="Scale functions with parameter aesthetic" width="480"/>

## Quantiles

Density-like plots let you show the quantiles by mapping them to a particular colour palette.

See: [quantile_parameters.ipynb](%nb-quantile_parameters%)

<img src="quantile_parameters.png" alt="Showing quantiles on density plots" width="480"/>

## Stackable Position Adjustments

To configure positioning where groups are stacked on top of each other, use the [`positionStack()`](%api_position%/position-stack.html) and [`positionFill()`](%api_position%/position-fill.html) functions.

See: [position_stack.ipynb](%nb-position_stack%)

<img src="position_stack.png" alt="Stackable position adjustments" width="480"/>
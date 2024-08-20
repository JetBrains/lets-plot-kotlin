# Miscellaneous

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
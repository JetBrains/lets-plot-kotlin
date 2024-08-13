# 'bistro' Plots

"Bistro" plots is a collection of "compound plots" allowing users to generate intricate charts without the need for extensive coding.

These plots build upon lets-plot's core functionality, combining multiple geoms, scales, and settings into ready-to-use functions.


## Correlation Plot

[`CorrPlot`](%api_bistro%.corr/-corr-plot/index.html) - builder that takes a dataframe (Kotlin `Map<*, *>`) as the input and builds a correlation plot.

<img src="correlation_plot.png" alt="Correlation plot" width="480"/>

See: [Correlation plot](%nb-correlation_plot%)


## Q-Q Plot

[`qqPlot`](%api_bistro%.qq/qq-plot.html) - function that creates a Q-Q plot (quantile-quantile plot).

<img src="qq_plots.png" alt="Q-Q Plots" width="480"/>

See: [Q-Q plots](%nb-qq_plots%)


## Joint Plot

[`jointPlot`](%api_bistro%.joint/joint-plot.html) - function that creates a joint plot containing bivariate and univariate graphs at the same time.

<img src="joint_plot.png" alt="Joint plot" width="480"/>

See: [Joint plot](%nb-joint_plot%)


## Residual Plot

[`residualPlot`](%api_bistro%.residual/residual-plot.html) - function that produces a residual plot showing the difference between the observed response and the fitted response values.

<img src="residual_plot.png" alt="Residual plot" width="480"/>

See: [Residual plot](%nb-residual_plot%)


## Waterfall Plot

[`waterfallPlot`](%api_bistro%.residual/waterfall-plot.html) - function that produces a waterfall plot showing the cumulative effect of sequentially introduced values.

<img src="waterfall_plot.png" alt="Waterfall plot" width="480"/>

See: [Waterfall plot](%nb-waterfall_plot%)
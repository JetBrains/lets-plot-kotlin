# 'bistro' Plots

Exploratory Data Analysis (EDA) is an art of looking at one or more datasets in an effort to understand the underlying structure of the data contained there.

Below are a few instruments from the 'bistro' package that could help you with your EDA investigations.


## Correlation Plot

[`CorrPlot`](%api_bistro%.corr/-corr-plot/index.html) - builder that takes a dataframe (Kotlin `Map<*, *>`) as the input and builds a correlation plot.

<img src="correlation_plot.png" alt="Correlation plot" width="480">

See: [Correlation plot](%nb-correlation_plot%)


## Q-Q Plot

[`qqPlot`](%api_bistro%.qq/qq-plot.html) - function that creates a Q-Q plot (quantile-quantile plot).

<img src="qq_plots.png" alt="Q-Q Plots" width="480">

See: [Q-Q plots](%nb-qq_plots%)


## Joint Plot

[`jointPlot`](%api_bistro%.joint/joint-plot.html) - function that creates a joint plot containing bivariate and univariate graphs at the same time.

<img src="joint_plot.png" alt="Joint plot" width="480">

See: [Joint plot](%nb-joint_plot%)


## Residual Plot

[`residualPlot`](%api_bistro%.residual/residual-plot.html) - function that produces a residual plot showing the difference between the observed response and the fitted response values.

<img src="residual_plot.png" alt="Residual plot" width="480">

See: [Residual plot](%nb-residual_plot%)
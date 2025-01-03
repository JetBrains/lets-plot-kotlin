# Sampling

Sampling is a special technique of data transformation, which is built into *Lets-Plot* and is applied after `stat` transformation.

Sampling helps to deal with large datasets when unintentional attempt to plot an excessively large number of geometries can lead to UI freezes and even to out-of-memory crashes.

Sampling is also one of the ways of handling over-plotting.

## How It Works

By default, sampling kicks-in automatically when the data volume exceeds a certain threshold.
Sampling type and parameters can also be configured manually per geometry layer using the `sampling` argument of `geomXxx()` function.

Value `samplingNone` will disable any sampling for the given layer.

There are several sampling methods implemented in *Lets-Plot*. The sampling methods can be chained together using the `+` operator.

## Sampling Methods

**random** - selects data points at randomly chosen indices without replacement.

**systematic** - selects data points at evenly distributed indices. Unlike canonical systematic sampling, it starts at index 0 and chooses the step so that the last selected index be as close as possible to the last index in the data.

**pick** - intended mostly for bar chart and, unlike the first two (above) it doesn't pick indices. Instead, it analyses X-values and selects all points which X-value is in the set of first n X-values found in the population.

**group random, group systematic** - similar to point-wise random/systematic sampling (above) but it selects the entire groups instead of individual data-points.

**random stratified** - randomly selects points from each group proportionally to the group size but also ensures that each group is represented by at least specified minimal number of points.

**vertex** - designed for polygon simplification. Vertex sampling knows how to handle rings. There is a choice of two implementation algorithms: **Douglas-Peucker** and **Visvalingam-Whyatt**.

When using group sampling, user specifies target number of groups in the sample which does not guarantee that the total number of points in the sample will be reasonably low.

For this case there is always a 'safety' sampling (**random** N=200_000) ready to kick-in if data volume is still too high.

## Examples

Pick sampling on Bar-chart:
[sampling_pick.ipynb](%nb-sampling_pick%)

Stratified sampling:
[sampling_stratified.ipynb](%nb-sampling_stratified%)
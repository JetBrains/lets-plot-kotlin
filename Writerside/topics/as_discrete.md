# Working with Categorical Variables and the asDiscrete() Function

In data analysis and visualization, discrete data commonly appears as categorical variables. These can be classified as:

- Nominal: unordered categories (e.g., colors, names)
- Ordinal: categories with a meaningful order (e.g., education levels, rating scales)

When visualizing Pandas series in *Lets-Plot*, ordinal data can be represented using [Pandas Categorical](https://pandas.pydata.org/docs/user_guide/categorical.html) type with the ordered parameter set to `True` and a specified category order. *Lets-Plot* will respect this ordering in the resulting visualizations.

Alternatively, *Lets-Plot* provides the [`asDiscrete`](%api_lets_plot%/as-discrete.html) function, which offers similar capabilities for any data type, not limited to Pandas DataFrames. This function allows for flexible manipulation of discrete data, including:

1. Annotation of numeric data as discrete: This allows continuous variables to be treated as categorical for visualization purposes.
2. Specification of discrete variable ordering: The order can be based on the variable’s own values or the values of another variable.
3. Custom ordering through explicit "factor levels": This feature allows for manual specification of category order.

The [`asDiscrete`](%api_lets_plot%/as-discrete.html) function thus allows for precise control over how categories are represented and ordered in plots, regardless of the original data format.


## Usage

```kotlin
asDiscrete(variable, label=null, orderBy=null, order=null, levels=null)
```

where

- `variable` (string) - the name of the data variable (which is mapped to the plot aesthetic);
- `label` (string) - the name of the scale - it will be used as the axis label or as the legend title;
- `orderBy` (string) - the name of the variable by which the ordering will be performed;
- `order` (integer) - the ordering direction - `1` for ascending direction and `-1` for descending (default value).

To enable ordering mode, at least one ordering parameter (`orderBy` or `order`) should be specified.
By the default, it will use descending direction and ordering by eigenvalues.
You cannot specify different order settings for the same variable. However, if these settings don't contradict each other, they will be combined.

The `orderBy` is a numeric variable, which values are used for reordering. It's also possible to use statistical variables.
The reordering uses the average value. The exception is plots with the `stack` position adjustment, where multiple bars occupying the same `x` position are stacked atop one another:
in this case, the sum is calculated to get the order of the stack sizes.


## Examples

```kotlin
val p = letsPlot(mpg)
p + geomPoint { x = "displ"; y = "hwy"; color = "cyl" }
```

<img src="as_discrete_1.png" width="480" alt="as_discrete_1.png"/>

Let's annotate the 'cyl' variable as discrete using the `asDiscrete('cyl')` function.
As a result, the data is divided into groups, a discrete color scale is assigned instead of a continuous one:

```kotlin
p + geomPoint { x = "displ"; y = "hwy"; color = asDiscrete("cyl") }
```

<img src="as_discrete_2.png" width="480" alt="as_discrete_2.png"/>

Set the 'cyl' variable in ascending order of its values:

```kotlin
p + geomPoint { x = "displ"; y = "hwy"; color = asDiscrete("cyl", order = 1) }
```

<img src="as_discrete_3.png" width="480" alt="as_discrete_3.png"/>

Boxplot example:

```kotlin
p + geomBoxplot { x = "class"; y = "hwy" }
```

<img src="as_discrete_4.png" width="480" alt="as_discrete_4.png"/>

Order `x` alphabetically:

```kotlin
p + geomBoxplot { x = asDiscrete("class", order = 1); y = "hwy" }
```

<img src="as_discrete_5.png" width="480" alt="as_discrete_5.png"/>

Order `x` by another variable - in descending order of the median:

```kotlin
p + geomBoxplot { x = asDiscrete("class", orderBy = "..middle.."); y = "hwy" }
```

<img src="as_discrete_6.png" width="480" alt="as_discrete_6.png"/>

Add `color` associated with the same variable.
The ordering is also applied to it, which will be visible in the legend:

```kotlin
p + geomBoxplot { x = asDiscrete("class", order = 1); y = "hwy"; color = "class" }
```

<img src="as_discrete_7.png" width="480" alt="as_discrete_7.png"/>

Two different ordering settings are specified for the `class` variable.
These settings don't contradict each other. This means that they will be combined,
and the variable will be ordered in ascending order `ymax`:

```kotlin
p + geomBoxplot { x = asDiscrete("class", orderBy = "..ymax.."); y = "hwy"; color = asDiscrete("class", order = 1) }
```

<img src="as_discrete_8.png" width="480" alt="as_discrete_8.png"/>

Use the `levels` parameter to specify the exact order for the variable:

```kotlin
val customOrder = listOf("subcompact", "compact", "suv", "minivan", "midsize", "pickup", "2seater")
p + geomBoxplot { x = asDiscrete("class", levels = customOrder); y = "hwy"; color = "class" }
```

<img src="as_discrete_9.png" width="480" alt="as_discrete_9.png"/>

Example of ordering for two variables:

```kotlin
p + geomBar { x = asDiscrete("manufacturer", order = 1); fill = asDiscrete("class", order = 1) }
```

<img src="as_discrete_10.png" width="480" alt="as_discrete_10.png"/>

Reorder `x` by counts to get from highest on the left to lowest on the right:

```kotlin
p + geomBar { x = asDiscrete("manufacturer", orderBy = "..count.."); fill = asDiscrete("class", order = 1) }
```

<img src="as_discrete_11.png" width="480" alt="as_discrete_11.png"/>

Apply sampling to the plot after reordering:

```kotlin
p + geomBar(sampling = samplingPick(4)) { x = asDiscrete("manufacturer", orderBy = "..count.."); fill = asDiscrete("class", order = 1) }
```

<img src="as_discrete_12.png" width="480" alt="as_discrete_12.png"/>


## Example Notebooks

- [Smoothing](%nb-geom_smooth%)
- [Guide to ordering](%nb-ordering_examples%)
- [Factor levels](%nb-factor_levels%)
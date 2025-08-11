package defaultViewer

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.dataframe.io.readCsv
import org.jetbrains.kotlinx.dataframe.size
import org.jetbrains.letsPlot.geom.extras.arrow
import org.jetbrains.letsPlot.geom.geomLabel
import org.jetbrains.letsPlot.geom.geomLabelRepel
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomText
import org.jetbrains.letsPlot.geom.geomTextRepel
import org.jetbrains.letsPlot.gggrid
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.interact.ggtb
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.scale.scaleShapeIdentity
import org.jetbrains.letsPlot.scale.scaleSize
import org.jetbrains.letsPlot.scale.scaleStroke
import org.jetbrains.letsPlot.scale.xlim
import org.jetbrains.letsPlot.scale.ylim
import org.jetbrains.letsPlot.themes.theme
import kotlin.random.Random

// add dependency for this script
// implementation("org.jetbrains.kotlinx:dataframe:1.0.0-Beta2")

fun main() {
    /*
    # Dynamic Text Positioning with Repel Geometries

    - `geom_text_repel()`
    - `geom_label_repel()`

    These functions use a force-based layout algorithm to automatically reposition text labels and resolve overlaps.

    Labels repel each other and their associated data points while staying within the plot boundaries.
    */

    // %%

    val df = DataFrame.readCsv(
        fileOrUrl = "https://gist.githubusercontent.com/seankross/a412dfbd88b3db70b74b/raw/5f23f993cd87c283ce766e7ac6b329ee7cc2e1d1/mtcars.csv",
        delimiter = ',',
        header = listOf(),
        colTypes = mapOf(),
        skipLines = 0,
        readLines = null,
        allowMissingColumns = true,
        parserOptions = null
    )
    println(df.size())
    println(df.head())

    // %%

    val df1 = df.filter { (2.0 < it["wt"] as Double) && ((it["wt"] as Double) < 3.65) }
    val df2 = df
        .filter { (2.0 < it["wt"] as Double) && ((it["wt"] as Double) < 3) }
        .add("shape") { if (it["am"] == 0.0) 16 else 21 }

    // %%

    val plot1 = ggplot(df1.toMap()) { x = "wt"; y = "mpg"; label = "model" } + geomPoint(color = "red")
    val plot2 = ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" } + geomPoint(color = "red")

    // %%

    /*
    ### Comparison of geom_text() and geom_text_repel()
    */

    // %%

    gggrid(
        listOf(
            plot2 + geomText() + ggtitle("geomText()"),
            plot2 + geomTextRepel() + ggtitle("geomTextRepel()")
        )
    ).show()

    // %%

    /*
    ### geom_label_repel()
    All of the parameters discussed below apply equally to both `geom_text_repel()` and `geom_label_repel()`. For simplicity, we will use `geom_text_repel()` in the examples.
    */

    // %%

    gggrid(
        listOf(
            plot2 + geomLabel() + ggtitle("geomText()"),
            plot2 + geomLabelRepel() + ggtitle("geomTextRepel()")
        )
    ).show()

    // %%

    /*
    ### `seed` parameter

    Controls the randomization to produce the same label layout each time the plot is generated.
     */

    // %%

    gggrid(
        listOf(
            plot2 + geomTextRepel() + ggtitle("Without seed"),
            plot2 + geomTextRepel() + ggtitle("Without seed"),
            plot2 + geomTextRepel(seed = 4) + ggtitle("With seed"),
            plot2 + geomTextRepel(seed = 4) + ggtitle("With seed"),
        ), ncol = 2
    ).show()

    // %%

    /*
    In some cases, it may be necessary to find a seed value that produces a more optimal label arrangement. A simple approach is to re-render the plot multiple times until you're satisfied with the result, then use the corresponding seed to reproduce it.
     */

    // %%

    val randomSeed = Random.nextInt()
    print("seed = $randomSeed")
    (plot2 + geomTextRepel(seed = randomSeed) + ggtitle("Seed = $randomSeed")).show()

    // %%

    /*
    ### `max_iter` parameter
    Controls the maximum number of iterations used by the layout algorithm, helping to reduce notebook rendering time. More iterations generally lead to better label placement, but at the cost of increased computation time. For plots with a small number of labels, 200–300 iterations are often sufficient.
    The default value is 2000.
     */

    // %%

    val seed = 123
    gggrid(
        listOf(
            plot2 + geomTextRepel(seed = seed, maxIter = 2) + ggtitle("max_iter=2"),
            plot2 + geomTextRepel(seed = seed, maxIter = 20) + ggtitle("max_iter=20"),
            plot2 + geomTextRepel(seed = seed, maxIter = 200) + ggtitle("max_iter=200"),
        )
    ).show()

    // %%

    /*
    ### `max_time` parameter
    Another way to limit plot rendering time is by using the `max_time` parameter. This primarily serves as a safeguard against excessive computation when a large number of text labels are involved. Time is specified in seconds. The default value is `5` seconds, but you can disable the time limit by setting it to `-1` if needed.
    */

    // %%

    gggrid(
        listOf(
            plot2 + geomTextRepel(seed = seed, maxTime = 0.001) + ggtitle("max_time=0.001"),
            plot2 + geomTextRepel(seed = seed, maxTime = 0.01) + ggtitle("max_time=0.01"),
            plot2 + geomTextRepel(seed = seed, maxTime = -1.0) + ggtitle("max_time=-1"),
        )
    ).show()

    // %%

    /*
    ### `direction` parameter
    Restricts the movement of a text label relative to its anchor point to a specific direction. The default value is `both`.
     */

    // %%

    gggrid(
        listOf(
            plot2 + geomTextRepel(seed = seed, direction = "x") + ggtitle("direction = 'x'"),
            plot2 + geomTextRepel(seed = seed, direction = "y") + ggtitle("direction = 'y'"),
            plot2 + geomTextRepel(seed = seed, direction = "both") + ggtitle("direction = 'both'"),
        )
    ).show()

    // %%

    /*
    As we can see, this option is of limited use for randomly scattered points, but in certain cases it can be extremely helpful:
     */

    // %%

    val plotX = ggplot(df2.toMap()) { x = "wt"; label = "model" } +
            geomPoint(y = 1, color = "red") + xlim(2 to 3) + ylim(1 to 1.3) +
            geomTextRepel(y = 1, nudgeY = 0.05, direction = "x", angle = 90, hjust = 0.0, seed = seed)

    val plotY = ggplot(df2.toMap()) { y = "mpg"; label = "model" } +
            geomPoint(x = 1, color = "red") + xlim(0.9 to 1.3) + ylim(19 to 35) +
            geomTextRepel(x = 1, nudgeX = 0.05, direction = "y", hjust = 0.0, seed = seed)

    gggrid(
        listOf(
            plotX + ggtitle("direction = x"),
            plotY + ggtitle("direction = y"),
        )
    ).show()

    // %%

    /*
    ### `point_padding` and `box_padding` parameters
    These parameters control the amount of spacing around text labels.

    - `point_padding` adds space between the label and all nearby points, but does not affect spacing between labels.

    - `box_padding` adds space between labels, but does not affect spacing between the label and the data point.
     */

    // %%

    gggrid(
        listOf(
            plot2 + geomTextRepel(seed = seed, maxTime = -1.0, pointPadding = 10) + ggtitle("point_padding"),
            plot2 + geomTextRepel(seed = seed, maxTime = -1.0, boxPadding = 10) + ggtitle("box_padding"),
        )
    ).show()

    // %%

    /*
    ### `max_overlaps` parameter
    Specifies the maximum allowed number of overlaps with other labels. Labels that exceed this threshold will be omitted from the plot. The default value is `10`. You can disable overlap filtering entirely by setting this parameter to `-1`.
     */

    // %%

    gggrid(
        listOf(
            plot1 + geomTextRepel(seed = seed, maxTime = -1.0, maxOverlaps = 5) + ggtitle("max_overlaps=5"),
            plot1 + geomTextRepel(seed = seed, maxTime = -1.0, maxOverlaps = -1) + ggtitle("max_overlaps=-1"),
        )
    ).show()

    // %%

    /*
    ### `min_segment_length` parameter
    Sets the minimum length for the line connecting a label to its associated point. Lines shorter than this length will not be drawn. To display all lines, use the default value of `0`. To hide all lines, set the value to something very large.
    `min_segment_length` uses the same units as `point_size`, so be careful when using `min_segment_length` together with `size_unit` (see below).
     */

    // %%

    gggrid(
        listOf(
            plot1 + geomTextRepel(seed = seed, maxTime = -1.0, minSegmentLength = 0) + ggtitle("min_segment_length=0"),
            plot1 + geomTextRepel(
                seed = seed,
                maxTime = -1.0,
                minSegmentLength = 9999
            ) + ggtitle("min_segment_length=9999"),
        )
    ).show()

    // %%

    /*
    ## Point settings
    geom_text_repel() does not draw points itself, but for it to work correctly, the values of parameters and aesthetics that control point size must match those used in the associated geom_point() layer.
     */

    // %%

    (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + geomPoint(sizeUnit = "y") { size = "gear"; stroke = "vs"; shape = "shape" }
            + geomTextRepel(sizeUnit = "y") { pointSize = "gear"; pointStroke = "vs"; shape = "shape" }
            + theme().legendPositionNone()
            + scaleSize(range = 0.5 to 1, guide = "none")
            + scaleStroke(range = 1 to 4, guide = "none")
            + scaleShapeIdentity()
            )
        .show()

    // %%

    /*
    ### `point_size` aesthetic
    Allows you to pass to geom_text_repel() the data used to determine point sizes in a geom_point() layer. This helps accurately detect overlaps between labels and points when point sizes vary.
     */

    // %%

    val plot31 = (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + geomPoint(color = "red") { size = "gear" }
            + theme().legendPositionNone())

    gggrid(
        listOf(
            plot31 + geomTextRepel(seed = seed, maxTime = -1.0) + ggtitle("without point_size"),
            plot31 + geomTextRepel(seed = seed, maxTime = -1.0) { pointSize = "gear" } + ggtitle("with point_size"),
        )).show()

    // %%

    /*
    You can also provide a constant value instead.
     */

    // %%

    val plot32 = (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + geomPoint(size = 10, color = "red")
            + theme().legendPositionNone())

    gggrid(
        listOf(
            plot32 + geomTextRepel(seed = seed, maxTime = -1.0) + ggtitle("without point_size"),
            plot32 + geomTextRepel(seed = seed, maxTime = -1.0, pointSize = 10) + ggtitle("with point_size"),
        )
    ).show()

    // %%

    /*
    ### `point_stroke` and `shape` aesthetics
    Allow you to pass to geom_text_repel() the data used to determine point stroke width and shape in a geom_point() layer. This ensures accurate collision detection between labels and points.
     */

    // %%

    val plot33 = (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label="model" }
            + geomPoint(shape=21, size=10, color="red") { stroke="gear" }
            + theme().legendPositionNone())

    gggrid(listOf(
        plot33 + geomTextRepel(seed=seed, maxTime=-1.0, pointSize=10) + ggtitle("without pointStroke"),
        plot33 + geomTextRepel(shape=21, pointSize=10, seed=seed, maxTime=-1.0) { pointStroke="gear" } + ggtitle("with pointStroke"),
    )).show()

    // %%

    /*
    ### `size_unit` parameter
    The `size_unit` parameter can be used in `geom_point()` to define the unit of measurement for the `size` aesthetic. In this case, it is recommended to also use `size_unit` in `geom_text_repel()` to ensure that point sizes are calculated correctly.
     */

    // %%

    val plot4 = (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + geomPoint(size = 1, sizeUnit = "y", color = "red")
            + theme().legendPositionNone())

    (gggrid(
        listOf(
            plot4 + geomTextRepel(seed = seed, maxTime = -1.0, pointSize = 1) + ggtitle("without size_unit"),
            plot4 + geomTextRepel(
                seed = seed,
                maxTime = -1.0,
                pointSize = 1,
                sizeUnit = "y"
            ) + ggtitle("with size_unit"),
        )
    ) + ggtb()).show()

    // %%

    /*
    ##### `size_unit` applies to all size-related parameters: `point_size`, `min_segment_length`, `point_padding`, and `box_padding`.
    As an example, consider how it affects `min_segment_length`.
    As mentioned earlier, `min_segment_length` uses the same units as `point_size`. Therefore, in the following example, with the same `min_segment_length` value, some lines are not drawn in the second case because their length is less than one unit along the y-axis.
     */

    val plot5 = (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + theme().legendPositionNone())

    (gggrid(
        listOf(
            (plot5 + geomPoint(size = 10, color = "red")
                    + geomTextRepel(seed = seed, maxTime = -1.0, pointSize = 10, minSegmentLength = 1)
                    + ggtitle("without size_unit")),
            (plot5 + geomPoint(size = 1, sizeUnit = "y", color = "red")
                    + geomTextRepel(seed = seed, maxTime = -1.0, pointSize = 1, minSegmentLength = 1, sizeUnit = "y")
                    + ggtitle("with size_unit")),
        )
    ) + ggtb()).show()

    // %%

    /*
    ### `segment_color` aesthetic
    Allows you to specify the color of the line connecting the label to the point. By default, the line color matches the text color and follows the `color` aesthetic. In the example below, the `color` aesthetic is defined globally for all layers, so the colors of the points, text, and lines are the same.
     */

    // %%

    (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model"; color = "wt" }
            + geomPoint()
            + geomTextRepel(seed = seed, maxTime = -1.0)
            )
        .show()

    // %%

    /*
    By using the `color` and `segment_color` aesthetics together, you can assign different colors to the points, labels, and connecting lines.
     */

    // %%

    val plot6 = (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + theme().legendPositionNone())

    gggrid(
        listOf(
            plot6 + geomPoint() { color = "wt" }
                    + geomTextRepel(seed = seed, maxTime = -1.0) { segmentColor = "wt" }
                    + ggtitle("Same color for points and lines"),
            plot6 + geomPoint(color = "red")
                    + geomTextRepel(seed = seed, maxTime = -1.0) { color = "wt" }
                    + ggtitle("Same color for text and line"),
            plot6 + geomPoint(color = "red")
                    + geomTextRepel(color = "green", segmentColor = "blue", seed = seed, maxTime = -1.0)
                    + ggtitle("Different colors"),
        )).show()

    // %%

    /*
    ### `segment_alpha` aesthetic

    Specifies the transparency level of the connecting lines between labels and points. By default, the segment transparency inherits from the text and is governed by the `alpha` aesthetic.
     */

    // %%

    (ggplot(df2.toMap()) { x = "wt"; y = "mpg"; label = "model" }
            + geomPoint(color = "red")
            + geomTextRepel(pointPadding = 20, color = "red", segmentAlpha = 0.1, seed = seed, maxTime = -1.0)
            ).show()

    // %%

    /*
    ### `segment_size` aesthetic
    Specifies the width of the line connecting the label to the point.
     */

    // %%

    (plot2 + geomTextRepel(segmentSize=2, seed=seed, maxTime=-1.0)).show()

    // %%

    /*
    ### `linetype` aesthetic
     */

    (ggplot(df2.toMap()) { y="mpg"; label="model" } + geomPoint(x=1, color="red") + xlim(0.9 to 1.3) + ylim(19 to 35)
            + geomTextRepel(x=1, nudgeX=0.2, direction="y", hjust=0.0, seed=seed) { linetype="disp" })
        .show()

    // %%

    /*
    ### `arrow` parameter
     */

    (ggplot(df2.toMap()) { y="mpg"; label="model" }
            + geomPoint(x=1, color="red")
            + geomTextRepel(
        x = 1,
        nudgeX = 0.2,
        direction = "y",
        hjust = 0.0,
        arrow = arrow(type = "closed", angle = 10, ends = "both"),
        seed = seed
    )
            + xlim(0.9 to 1.3)
            + ylim(19 to 35)
            ).show()
}
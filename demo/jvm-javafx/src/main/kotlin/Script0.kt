import jetbrains.letsPlot.Geom.area
import jetbrains.letsPlot.Geom.point
import jetbrains.letsPlot.Pos.dodge
import jetbrains.letsPlot.Pos.position_dodge
import jetbrains.letsPlot.geom.geom_area
import jetbrains.letsPlot.geom.geom_point
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.stat.stat_density


var df = null
var p = ggplot(data = df) +
        geom_area(size = 3.0) { x = "X" } +
        geom_point(data = df, color = "red") +
        geom_area(data = df) { y = "Y"; fill = "F" } +
        stat_density(geom = area({ y = "Y" }, linetype = 1)) +
        stat_density(geom = point({ y = "Y" }, shape = 1)) +
        geom_area(position = dodge) +
        geom_area(position = position_dodge(width = 10.0))
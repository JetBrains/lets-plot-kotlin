import com.jetbrains.datalore.plot.dsl.Geom.area
import com.jetbrains.datalore.plot.dsl.Geom.point
import com.jetbrains.datalore.plot.dsl.Pos.dodge
import com.jetbrains.datalore.plot.dsl.Pos.position_dodge
import com.jetbrains.datalore.plot.dsl.geom.geom_area
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.ggplot
import com.jetbrains.datalore.plot.dsl.stat.stat_density


var df = null
var p = ggplot(data = df) +
        geom_area({ x = "X" }, size = 3) +
        geom_point(data = df, color = "red") +
        geom_area({ y = "Y"; fill = "F" }, data = df) +
        stat_density(geom = area({ y = "Y" }, linetype = 1)) +
        stat_density(geom = point({ y = "Y" }, shape = 1)) +
        geom_area(position = dodge) +
        geom_area(position = position_dodge(width = 10))
package sandbox

import com.jetbrains.datalore.plot.dsl.Geom.point
import com.jetbrains.datalore.plot.dsl.Pos
import com.jetbrains.datalore.plot.dsl.Pos.dodge
import com.jetbrains.datalore.plot.dsl.Pos.position_dodge
import com.jetbrains.datalore.plot.dsl.geom.geom_area
import com.jetbrains.datalore.plot.dsl.geom.geom_point
import com.jetbrains.datalore.plot.dsl.ggplot
import com.jetbrains.datalore.plot.dsl.stat.stat_density
import com.jetbrains.datalore.plot.layer.PosOptions


var df = null
var p = ggplot(data = df) +
        geom_area({ x = "X" }, size = 3) +
        geom_point(data = df, color = "red") +
        stat_density(geom = point({ y = "Y" }, shape = 1))+
        geom_area(position = dodge) +
        geom_area(position = position_dodge(width=10))
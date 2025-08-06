package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.xlim
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.cos
import kotlin.math.sin

@Suppress("ClassName")
object Issue_xlim_breaks_datetime_LP1348 {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Issue: xlim breaks datetime LP-1348") {

            fun squiggle(x: Double): Double {
                return sin(3 * x) / (x * (cos(x) + 2))
            }

            val N = 50
            val xs = (0 until N).map { i -> 1.0 + (25.0 - 1.0) * i / (N - 1) }
            val ys = xs.map { squiggle(it) }

            // Berlin DST transition: March 30, 2025 at 2:00 AM
            // Cover ±3 days: March 27 - April 2, 2025 (6 days total)

            // Generate equally spaced UNIX timestamps (in seconds)

            val startTimestamp = ZonedDateTime.of(2025, 3, 27, 0, 0, 0, 0, ZoneId.of("UTC")).toEpochSecond()
            val endTimestamp = ZonedDateTime.of(2025, 4, 2, 23, 59, 0, 0, ZoneId.of("UTC")).toEpochSecond()
            val unixTimestamps = (0 until N).map { i ->
                startTimestamp + (endTimestamp - startTimestamp) * i / (N - 1)
            }

            // Convert to ZonedDateTime with Berlin timezone
            val xsDatetime = unixTimestamps.map { timestamp ->
                Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("Europe/Berlin"))
            }

            val data = mapOf(
                "xs" to xsDatetime,
                "ys" to ys
            )

            val p = letsPlot(data = data) { x = "xs"; y = "ys" } +
                    geomLine(
                        color = "#1380A1",
                        size = 2
//                    )
                    ) +
                    xlim(
                        ZonedDateTime.of(2025, 3, 29, 23, 0, 0, 0, ZoneId.of("Europe/Berlin")) to
                                ZonedDateTime.of(2025, 3, 30, 8, 0, 0, 0, ZoneId.of("Europe/Berlin")),
                    )

            p.show()

        }
    }
}
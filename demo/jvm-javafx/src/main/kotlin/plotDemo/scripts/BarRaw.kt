package plotDemo.scripts

import jetbrains.datalore.base.json.JsonSupport
import plotDemo.SwingDemoUtil

object BarRaw {
    @JvmStatic
    fun main(args: Array<String>) {

        val data = "   {" +
                "      'time': ['Lunch','Lunch', 'Dinner', 'Dinner', 'Dinner']" +
                "   }"

        val specRaw = "{" +
                "   'kind': 'plot'," +
                "   'data': " + data +
                "           ," +
                "   'mapping': {" +
                "             'x': 'time'," +
                "             'y': '..count..'," +
                "             'fill': '..count..'" +
                "           }," +
                "   'layers': [" +
                "               {" +
                "                  'geom': 'bar'" +
                "               }" +
                "           ]" +

                "   ," +
                "   'scales': [" +
                "               {" +
                "                  'aesthetic': 'fill'," +
                "                  'discrete': true," +
                "                  'scale_mapper_kind': 'color_hue'" +
                "               }" +
                "           ]" +
                "}"


        val spec = JsonSupport.parseJson(specRaw)
        SwingDemoUtil.display(spec as MutableMap<String, Any>)
    }
}
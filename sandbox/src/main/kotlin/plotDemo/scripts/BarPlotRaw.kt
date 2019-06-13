package plotDemo.scripts

import jetbrains.datalore.base.json.JsonSupport
import plotDemo.DemoScriptBase

class BarPlotRaw : DemoScriptBase() {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            BarPlotRaw().display(plotSpec() as MutableMap<String, Any>)
        }

        private const val DATA = "   {" +
                "      'time': ['Lunch','Lunch', 'Dinner', 'Dinner', 'Dinner']" +
                "   }"


        private fun plotSpec(): Map<String, Any> {
            val spec = "{" +
                    "   'data': " + DATA +
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

            return JsonSupport.parseJson(spec)
        }
    }
}
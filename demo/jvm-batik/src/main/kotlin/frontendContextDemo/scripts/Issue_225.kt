package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import org.jetbrains.letsPlot.asDiscrete
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.letsPlot

@Suppress("ClassName")
object Issue_225 {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Issue #225 (Bug with Tooltips)") {
            val dfMap = mapOf(
                "id" to listOf(0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,14,14,14,14,14,14,14,14,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,17,17,17,17,17,17,17,17,18,18,18,18,18,18,18,18,19,19,19,19,19,19,19,19),
                "stage" to listOf("seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location","seed","soil","fertilizer","water","light","temperature","humidity","location"),
                "value" to listOf<Long>(487758422,756658162,2431799474,3869788791,2795143922,3172871783,3941445656,940608699,524336848,793236588,2468377900,3906367217,2831722348,3209450209,3978024082,977187125,2531594804,2522373814,1604479767,1581629253,3491948434,3928152510,1146352902,2852007167,27107767,1384244343,1351599440,4101236384,3018955323,3396683184,4165257057,3231183623,1343486056,624292804,418827499,192437308,192437308,1170303048,2604178122,2510726952,124327551,1481464127,1448819224,4198456168,3116175107,3493902968,227309083,368168133,1117929819,398736567,193271262,535988702,535988702,338467805,3598650012,1590835046,93097070,1450233646,1417588743,4167225687,3084944626,3462672487,196078602,336937652,3305050822,3958892015,3932934994,1783278915,2020868752,2864657816,1419132726,3066315671,442320425,711220165,2386361477,3824350794,2749705925,3127433786,3896007659,895170702,2324984130,2315763140,3242323148,793101439,793101439,890636303,2720752405,2627301235,87604424,1444741000,1412096097,4161733041,3079451980,3457179841,190585956,331445006,4216329536,3378708768,1125031038,2012530544,3978998456,2676608860,1231083770,3829090675,45038934,1402175510,1369530607,4119167551,3036886490,3414614351,4183188224,3249114790,1482842780,269311296,63845991,396421869,396421869,198900972,2903950002,3483972752,224610898,203500568,972430788,3257745395,4222961579,4078694393,1533413416,1845776732,115202033,1472338609,1439693706,4189330650,3107049589,3484777450,218183565,359042615,371332058,1275792810,1243147907,3992784851,2910503790,3288231651,4056805524,4090870626,2845474954,3499316147,3499316147,1345493865,3255813046,3633540907,3208814611,4238424822,192579859,171469529,3089526529,2417970400,1334148675,1334148675,3286723471,1056365404)
            )
            val plot = letsPlot(dfMap) + geomLine {
                x = "stage"
                y = "value"
                color = asDiscrete("id")
            }
            plot.show()
        }
    }
}
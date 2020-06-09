/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext

object HLineVLine {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("HLine, VLine") {
//            HLineVLineScripts.script0()
//            HLineVLineScripts.script1()
            HLineVLineScripts.script2()
        }
    }
}

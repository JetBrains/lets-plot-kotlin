/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext

object HLineVLine {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("HLine, VLine") {
            HLineVLineScripts.script0()
            HLineVLineScripts.script1()
            HLineVLineScripts.script2()
            HLineVLineScripts.script3()
        }
    }
}

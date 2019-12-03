/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendApi

class SimpleTextFrontendContext : TextFrontendContext {
    override fun displayText(s: String) {
        println(s)
    }
}
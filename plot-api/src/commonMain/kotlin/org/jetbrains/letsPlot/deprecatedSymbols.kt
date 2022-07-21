/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.intern.OptionsMap

//
// In v4.0.0
// theme.kt was moved to sub-package org.jetbrains.letsPlot.themes.
//

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.theme"), level = DeprecationLevel.WARNING
)
typealias theme = org.jetbrains.letsPlot.themes.theme

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.elementBlank()"), level = DeprecationLevel.WARNING
)
fun elementBlank() = org.jetbrains.letsPlot.themes.elementBlank()

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.elementRect(fill, color, size, blank)"), level = DeprecationLevel.WARNING
)
fun elementRect(
    fill: Any? = null,
    color: Any? = null,
    size: Number? = null,
    blank: Boolean = false,
) = org.jetbrains.letsPlot.themes.elementRect(fill, color, size, blank)

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.elementLine(color, size, blank)"), level = DeprecationLevel.WARNING
)
fun elementLine(
    color: Any? = null,
    size: Number? = null,
    blank: Boolean = false,
) = org.jetbrains.letsPlot.themes.elementLine(color, size, blank)

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.elementText(color, face, blank)"), level = DeprecationLevel.WARNING
)
fun elementText(
    color: Any? = null,
    face: Any? = null,
    blank: Boolean = false,
) = org.jetbrains.letsPlot.themes.elementText(color, face, blank)


//
// In v4.0.0
// ThemeSet.kt was moved to sub-package org.jetbrains.letsPlot.themes.
// (Predefined themes).
//

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.themeGrey()"), level = DeprecationLevel.WARNING
)
fun themeGrey() = org.jetbrains.letsPlot.themes.themeGrey()

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.themeLight()"), level = DeprecationLevel.WARNING
)
fun themeLight() = org.jetbrains.letsPlot.themes.themeLight()

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.themeClassic()"), level = DeprecationLevel.WARNING
)
fun themeClassic() = org.jetbrains.letsPlot.themes.themeClassic()

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.themeMinimal()"), level = DeprecationLevel.WARNING
)
fun themeMinimal() = org.jetbrains.letsPlot.themes.themeMinimal()

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.themeMinimal2()"), level = DeprecationLevel.WARNING
)
fun themeMinimal2() = org.jetbrains.letsPlot.themes.themeMinimal2()

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.themes\"",
    ReplaceWith("org.jetbrains.letsPlot.themes.themeNone()"), level = DeprecationLevel.WARNING
)
fun themeNone() = org.jetbrains.letsPlot.themes.themeNone()

//
// In v4.0.0
// Coord.kt was moved to sub-package org.jetbrains.letsPlot.coord.
//

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.coord\"",
    ReplaceWith("org.jetbrains.letsPlot.coord.coordFixed(ratio, xlim, ylim, flip)"), level = DeprecationLevel.WARNING
)
fun coordFixed(
    ratio: Number? = null,
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    flip: Boolean = false
): OptionsMap = org.jetbrains.letsPlot.coord.coordFixed(ratio, xlim, ylim, flip)

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.coord\"",
    ReplaceWith("org.jetbrains.letsPlot.coord.coordCartesian(xlim, ylim, flip)"), level = DeprecationLevel.WARNING
)
fun coordCartesian(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    flip: Boolean = false
): OptionsMap = org.jetbrains.letsPlot.coord.coordCartesian(xlim, ylim, flip)

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.coord\"",
    ReplaceWith("org.jetbrains.letsPlot.coord.coordMap(xlim, ylim, flip)"), level = DeprecationLevel.WARNING
)
fun coordMap(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null,
    flip: Boolean = false
): OptionsMap = org.jetbrains.letsPlot.coord.coordMap(xlim, ylim, flip)

@Deprecated(
    "Moved to package \"org.jetbrains.letsPlot.coord\"",
    ReplaceWith("org.jetbrains.letsPlot.coord.coordFlip(xlim, ylim)"), level = DeprecationLevel.WARNING
)
fun coordFlip(
    xlim: Pair<Number?, Number?>? = null,
    ylim: Pair<Number?, Number?>? = null
): OptionsMap = org.jetbrains.letsPlot.coord.coordFlip(xlim, ylim)

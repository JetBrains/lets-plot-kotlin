/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.util.pngj

/**
 * Exception thrown by bad CRC check
 */
internal class PngjBadCrcException(message: String) : PngjInputException(message)
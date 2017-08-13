/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import lombok.AllArgsConstructor;

/**
 * Sting with "\\s+".
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class FramedString {

    /**
     * The origin string.
     */
    private final String string;

    /**
     * Add "\\s+" to start and end of string.
     *
     * @return Changed string
     */
    public String text() {
        return "\\s+" + string + "\\s+";
    }
}

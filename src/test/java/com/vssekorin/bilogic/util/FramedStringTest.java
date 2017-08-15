/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link FramedString}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class FramedStringTest {

    @Test
    public void text() throws Exception {
        assertEquals(
            new FramedString("text").text(),
            "\\s+text\\s+"
        );
    }
}

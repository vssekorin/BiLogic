/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link Pair}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class PairTest {

    @Test
    public void first() throws Exception {
        val pair = new Pair<Integer>(6, 4);
        assertEquals(pair.first(), Integer.valueOf(6));
    }

    @Test
    public void second() throws Exception {
        val pair = new Pair<Integer>(6, 4);
        assertEquals(pair.second(), Integer.valueOf(4));
    }
}

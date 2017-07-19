/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import lombok.val;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test case for {@link Pair}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class PairTest {

    @Test
    public void first() throws Exception {
        val pair = new Pair<Integer>(3, 6);
        assertEquals(pair.first(), new Integer(3));
    }

    @Test
    public void second() throws Exception {
        val pair = new Pair<Integer>(3, 6);
        assertEquals(pair.second(), new Integer(6));
    }
}

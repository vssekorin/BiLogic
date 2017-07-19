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
 * Test case for {@link VarList}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class VarListTest {

    @Test
    public void asIndexList() throws Exception {
        val indexes = new VarList("var1, var2, var3").asIndexList();
        assertEquals(indexes.size(), 3);
        for (int index : indexes) {
            assertEquals(index, indexes.indexOf(index) + 2);
        }
    }
}

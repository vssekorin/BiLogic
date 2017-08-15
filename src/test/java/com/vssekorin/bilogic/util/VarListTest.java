/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import com.vssekorin.bilogic.method.MethodInfo;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link VarList}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class VarListTest {

    private VarList varList;

    @Before
    public void setUp() throws Exception {
        val info = new MethodInfo(
            "Class",
            "method",
            Arrays.asList("a", "b", "c")
        );
        this.varList = new VarList(info, "a, b");
    }

    @Test
    public void asIndexList() throws Exception {
        assertEquals(this.varList.asIndexList().size(), 2);
    }

    @Test
    public void asIndexStream() throws Exception {
        assertEquals(this.varList.asIndexStream().count(), 2);
    }
}

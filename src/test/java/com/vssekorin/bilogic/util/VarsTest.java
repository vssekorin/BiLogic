/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import com.vssekorin.bilogic.error.UnknownVariableException;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link Vars}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class VarsTest {

    private final Vars vars =
        new Vars(Collections.singletonList("a"));

    @Test
    public void indexCanFindVar() throws Exception {
        assertTrue(this.vars.index("a") >= 0);
    }

    @Test
    public void indexIgnoredVar() throws Exception {
        assertEquals(this.vars.index("_"), Vars.IGNORED);
    }

    @Test(expected = UnknownVariableException.class)
    public void index() throws Exception {
        assertTrue(this.vars.index("b") >= 0);
    }
}

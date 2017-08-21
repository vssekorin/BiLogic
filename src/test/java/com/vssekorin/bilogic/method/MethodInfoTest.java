/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link MethodInfo}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class MethodInfoTest {

    private final MethodInfo info = new MethodInfo(
        "Klass",
        "method",
        "var1", "var2", "var3"
    );

    @Test
    public void className() throws Exception {
        assertEquals(this.info.className(), "Klass");
    }

    @Test
    public void name() throws Exception {
        assertEquals(this.info.name(), "method");
    }

    @Test
    public void numberArgs() throws Exception {
        assertEquals(this.info.numberArgs(), 3);
    }
}

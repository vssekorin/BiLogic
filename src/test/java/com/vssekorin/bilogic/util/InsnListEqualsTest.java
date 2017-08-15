/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import org.junit.Before;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_2;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link InsnListEquals}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class InsnListEqualsTest {

    private final InsnList first = new InsnList();

    private final InsnList second = new InsnList();

    @Before
    public void setUp() throws Exception {
        this.first.add(new InsnNode(ICONST_2));
        this.first.add(new LabelNode());
    }

    @Test
    public void equalsListReturnTrue() throws Exception {
        this.second.add(new InsnNode(ICONST_2));
        this.second.add(new LabelNode());
        assertTrue(new InsnListEquals(this.first, this.second).value());
    }

    @Test
    public void notEqualsListReturnFalse() throws Exception {
        this.second.add(new InsnNode(ICONST_1));
        this.second.add(new LabelNode());
        assertFalse(new InsnListEquals(this.first, this.second).value());
    }

    @Test
    public void differentSizeListReturnFalse() throws Exception {
        this.second.add(new InsnNode(ICONST_2));
        this.second.add(new LabelNode());
        this.second.add(new LabelNode());
        assertFalse(new InsnListEquals(this.first, this.second).value());
    }
}

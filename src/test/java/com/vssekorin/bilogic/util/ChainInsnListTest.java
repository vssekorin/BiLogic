/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link ChainInsnList}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ChainInsnListTest {

    @Test
    public void equalsInsnList() throws Exception {
        val chained = new ChainInsnList()
            .add(new LabelNode())
            .add(new InsnNode(ICONST_1))
            .getInsnList();
        val classic = new InsnList();
        classic.add(new LabelNode());
        classic.add(new InsnNode(ICONST_1));
        assertTrue(new InsnListEquals(chained, classic).value());
    }

    @Test
    public void notEqualsInsnList() throws Exception {
        val chained = new ChainInsnList()
            .add(new LabelNode())
            .add(new InsnNode(ICONST_0))
            .getInsnList();
        val classic = new InsnList();
        classic.add(new LabelNode());
        classic.add(new InsnNode(ICONST_1));
        assertFalse(new InsnListEquals(chained, classic).value());
    }
}

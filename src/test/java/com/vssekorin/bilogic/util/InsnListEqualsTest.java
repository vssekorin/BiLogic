/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.ILOAD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link InsnListEquals}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class InsnListEqualsTest {

    @Test
    public void emptyEquals() throws Exception {
        val first = new InsnList();
        val second = new InsnList();
        assertTrue(new InsnListEquals(first, second).value());
    }

    @Test
    public void oneEquals() throws Exception {
        val first = new InsnList();
        first.add(new InsnNode(ICONST_1));
        val second = new InsnList();
        second.add(new InsnNode(ICONST_1));
        assertTrue(new InsnListEquals(first, second).value());
    }

    @Test
    public void oneNotEqualsOneType() throws Exception {
        val first = new InsnList();
        first.add(new InsnNode(ICONST_1));
        val second = new InsnList();
        second.add(new InsnNode(ICONST_0));
        assertFalse(new InsnListEquals(first, second).value());
    }

    @Test
    public void oneNotEqualsDifType() throws Exception {
        val first = new InsnList();
        first.add(new InsnNode(ICONST_1));
        val second = new InsnList();
        second.add(new LabelNode());
        assertFalse(new InsnListEquals(first, second).value());
    }

    @Test
    public void severalEquals() throws Exception {
        val first = new InsnList();
        first.add(new InsnNode(ICONST_1));
        first.add(new VarInsnNode(ILOAD, 0));
        val second = new InsnList();
        second.add(new InsnNode(ICONST_1));
        second.add(new VarInsnNode(ILOAD, 0));
        assertTrue(new InsnListEquals(first, second).value());
    }

    @Test
    public void severalNotEquals() throws Exception {
        val first = new InsnList();
        first.add(new LabelNode());
        first.add(new InsnNode(ICONST_1));
        val second = new InsnList();
        second.add(new InsnNode(ICONST_0));
        second.add(new InsnNode(ICONST_1));
        assertFalse(new InsnListEquals(first, second).value());
    }
}

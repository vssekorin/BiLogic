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

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_2;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link ChainInsnList}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ChainInsnListTest {

    @Test
    public void addNode() throws Exception {
        val list = new InsnList();
        list.add(new LabelNode());
        list.add(new InsnNode(ICONST_2));
        assertTrue(
            new InsnListEquals(
                list,
                new ChainInsnList()
                    .add(new LabelNode())
                    .add(new InsnNode(ICONST_2))
                    .getInsnList()
            ).value()
        );
    }

    @Test
    public void addInsnList() throws Exception {
        val list = new InsnList();
        list.add(new LabelNode());
        list.add(new InsnNode(ICONST_2));
        val copy = new InsnList();
        copy.add(new LabelNode());
        copy.add(new InsnNode(ICONST_2));
        assertTrue(
            new InsnListEquals(
                list,
                new ChainInsnList().add(copy).getInsnList()
            ).value()
        );
    }
}

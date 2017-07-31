/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.InsnListEquals;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link Result}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ResultTest {

    @Test
    public void asBytecode() throws Exception {
        val first = new LabelNode();
        val second = new LabelNode();
        val code = new Result(first, second).asBytecode();
        assertEquals(code.getInsnList().size(), 5);
        val test = new ChainedInsnList()
            .add(new InsnNode(ICONST_1))
            .add(new JumpInsnNode(GOTO, second))
            .add(first)
            .add(new InsnNode(ICONST_0))
            .add(second);
        assertTrue(new InsnListEquals(code, test).value());
    }
}

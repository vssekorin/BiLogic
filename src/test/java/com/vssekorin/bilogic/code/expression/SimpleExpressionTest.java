/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.util.Vars;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;
import static jdk.internal.org.objectweb.asm.Opcodes.ILOAD;
import static org.junit.Assert.assertEquals;

/**
 * Test case for {@link SimpleExpression}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class SimpleExpressionTest {

    @Test
    public void asBytecodeTrue() throws Exception {
        val aTrue = new SimpleExpression("true").asBytecode().getInsnList();
        val node = new InsnNode(ICONST_1);
        assertEquals(aTrue.size(), 1);
        val element = aTrue.getFirst();
        assertEquals(element.getOpcode(), node.getOpcode());
        assertEquals(element.getType(), node.getType());
    }

    @Test
    public void asBytecodeFalse() throws Exception {
        val aFalse = new SimpleExpression("false").asBytecode().getInsnList();
        val node = new InsnNode(ICONST_0);
        assertEquals(aFalse.size(), 1);
        val element = aFalse.getFirst();
        assertEquals(element.getOpcode(), node.getOpcode());
        assertEquals(element.getType(), node.getType());
    }

    @Test
    public void asBytecodeVar() throws Exception {
        Vars.getInstance().add("name");
        val aVar = new SimpleExpression("name").asBytecode().getInsnList();
        val node = new VarInsnNode(ILOAD, 0);
        assertEquals(aVar.size(), 1);
        val element = aVar.getFirst();
        assertEquals(element.getOpcode(), node.getOpcode());
        assertEquals(element.getType(), node.getType());
    }
}
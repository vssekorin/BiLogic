/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.code.Result;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.InsnListEquals;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.IFEQ;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link And}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
final public class AndTest {

    @Test
    public void asBytecode() throws Exception {
        val ifeq = new LabelNode();
        val end = new LabelNode();
        val code = new And("true and false").asBytecode();
        val list = new ChainInsnList()
            .add(new SimpleExpression("true").asBytecode())
            .add(new JumpInsnNode(IFEQ, ifeq))
            .add(new SimpleExpression("false").asBytecode())
            .add(new JumpInsnNode(IFEQ, ifeq))
            .add(new Result(ifeq, end).asBytecode());
        assertTrue(new InsnListEquals(code, list).value());
    }
}

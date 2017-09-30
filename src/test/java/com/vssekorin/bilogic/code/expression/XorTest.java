/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.InsnListEquals;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.ILOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.IXOR;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link Xor}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class XorTest {

    @Test
    public void asBytecode() throws Exception {
        val info = new MethodInfo(
            "Klass",
            "method",
            "var1", "var2"
        );
        val expression = new SomeExpression(info, "var1 xor var2");
        val code = new ChainInsnList()
            .add(new VarInsnNode(ILOAD, info.vars().index("var1")))
            .add(new VarInsnNode(ILOAD, info.vars().index("var2")))
            .add(new InsnNode(IXOR));
        assertTrue(new InsnListEquals(expression.asBytecode(), code).value());
    }
}

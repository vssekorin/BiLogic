/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.InsnListEquals;
import com.vssekorin.bilogic.util.Labels;
import com.vssekorin.bilogic.util.Pair;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link Else}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ElseTest {

    @Before
    public void setUp() throws Exception {
        Labels.getInstance().add(
            new Pair<>(
                new LabelNode(),
                new LabelNode()
            )
        );
    }

    @Test
    public void asBytecode() throws Exception {
        val code = new Else().asBytecode();
        val list = new ChainedInsnList()
            .add(new JumpInsnNode(GOTO, new LabelNode()))
            .add(new LabelNode());
        assertTrue(new InsnListEquals(code, list).value());
    }
}

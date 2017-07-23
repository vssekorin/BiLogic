/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.Labels;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;

/**
 * End of while.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class EndWhile implements Code {

    @Override
    public InsnList asBytecode() {
        val pair = Labels.getInstance().pop();
        return new ChainedInsnList()
            .add(new JumpInsnNode(GOTO, pair.first()))
            .add(pair.second())
            .getInsnList();
    }
}

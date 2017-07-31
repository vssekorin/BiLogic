/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.Labels;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;

/**
 * Else line.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Else implements Code {

    @Override
    public ChainInsnList asBytecode() {
        val pair = Labels.getInstance().peek();
        return new ChainInsnList()
            .add(new JumpInsnNode(GOTO, pair.second()))
            .add(pair.first());
    }
}

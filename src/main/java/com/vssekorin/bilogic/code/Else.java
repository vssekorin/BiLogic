/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;

/**
 * Else line.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Else implements Code {

    /**
     * The name.
     */
    public static final String NAME = "else";

    /**
     * The information about method.
     */
    private final MethodInfo info;

    @Override
    public ChainInsnList asBytecode() {
        val pair = this.info.labels().peek();
        return new ChainInsnList()
            .add(new JumpInsnNode(GOTO, pair.second()))
            .add(pair.first());
    }
}

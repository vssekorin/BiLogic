/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.BIPUSH;
import static jdk.internal.org.objectweb.asm.Opcodes.SIPUSH;

/**
 * Load the int value onto the stack.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class IPush implements Code {

    /**
     * The value.
     */
    private final int value;

    /**
     * The maximum value for which the BIPUSH can be used.
     */
    private static final int BIPUSH_MAX = 127;

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        if (this.value <= BIPUSH_MAX) {
            code.add(new VarInsnNode(BIPUSH, this.value));
        } else {
            code.add(new VarInsnNode(SIPUSH, this.value));
        }
        return code;
    }
}

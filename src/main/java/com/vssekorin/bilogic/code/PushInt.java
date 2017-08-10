/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Load the int value onto the stack.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class PushInt implements Code {

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
        final ChainInsnList code = new ChainInsnList();
        if (this.value <= BIPUSH_MAX) {
            switch (this.value) {
                case -1: code.add(new InsnNode(ICONST_M1)); break;
                case 0: code.add(new InsnNode(ICONST_0)); break;
                case 1: code.add(new InsnNode(ICONST_1)); break;
                case 2: code.add(new InsnNode(ICONST_2)); break;
                case 3: code.add(new InsnNode(ICONST_3)); break;
                case 4: code.add(new InsnNode(ICONST_4)); break;
                case 5: code.add(new InsnNode(ICONST_5)); break;
                default: code.add(new VarInsnNode(BIPUSH, this.value)); break;
            }
        } else {
            code.add(new VarInsnNode(SIPUSH, this.value));
        }
        return code;
    }
}

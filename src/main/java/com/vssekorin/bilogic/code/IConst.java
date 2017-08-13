/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Load the int value onto the stack.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class IConst implements Code {

    /**
     * The value.
     */
    private final int value;

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        switch (this.value) {
            case -1: code.add(new InsnNode(ICONST_M1)); break;
            case 0: code.add(new InsnNode(ICONST_0)); break;
            case 1: code.add(new InsnNode(ICONST_1)); break;
            case 2: code.add(new InsnNode(ICONST_2)); break;
            case 3: code.add(new InsnNode(ICONST_3)); break;
            case 4: code.add(new InsnNode(ICONST_4)); break;
            case 5: code.add(new InsnNode(ICONST_5)); break;
            default: throw new IllegalStateException();
        }
        return code;
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

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
        val code = new InsnList();
        val pair = Labels.getInstance().pop();
        code.add(new JumpInsnNode(GOTO, pair.first()));
        code.add(pair.second());
        return code;
    }
}

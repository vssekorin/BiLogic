/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainedInsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Choice of variable value.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Result implements Code {

    /**
     * The false case label.
     */
    private final LabelNode intermediate;

    /**
     * The true case label.
     */
    private final LabelNode end;

    @Override
    public ChainedInsnList asBytecode() {
        return new ChainedInsnList()
            .add(new InsnNode(ICONST_1))
            .add(new JumpInsnNode(GOTO, this.end))
            .add(this.intermediate)
            .add(new InsnNode(ICONST_0))
            .add(this.end);
    }
}

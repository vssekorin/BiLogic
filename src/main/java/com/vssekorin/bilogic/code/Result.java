/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_0;
import static jdk.internal.org.objectweb.asm.Opcodes.ICONST_1;

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
    public InsnList asBytecode() {
        val code = new InsnList();
        code.add(new InsnNode(ICONST_1));
        code.add(new JumpInsnNode(GOTO, this.end));
        code.add(this.intermediate);
        code.add(new InsnNode(ICONST_0));
        code.add(this.end);
        return code;
    }
}

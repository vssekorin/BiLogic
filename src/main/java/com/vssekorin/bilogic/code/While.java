/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import com.vssekorin.bilogic.util.Labels;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.IFEQ;

/**
 * While.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class While implements Code {

    /**
     * While-do line.
     */
    private final String line;

    @Override
    public InsnList asBytecode() {
        val code = new InsnList();
        val expression = line
            .replace("while", "")
            .replace("do", "")
            .trim();
        val start = new LabelNode();
        val ifeq = new LabelNode();
        code.add(start);
        code.add(new SomeExpression(expression).asBytecode());
        code.add(new JumpInsnNode(IFEQ, ifeq));
        Labels.getInstance().add(start, ifeq);
        return code;
    }
}
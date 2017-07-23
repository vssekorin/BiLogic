/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.Labels;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.IFEQ;

/**
 * Start of if statement.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class If implements Code {

    /**
     * If-then line.
     */
    private final String line;

    @Override
    public InsnList asBytecode() {
        val expression = line
            .replace("if", "")
            .replace("then", "")
            .trim();
        val ifeq = new LabelNode();
        val end = new LabelNode();
        Labels.getInstance().add(ifeq, end);
        return new ChainedInsnList()
            .add(new SomeExpression(expression).asBytecode())
            .add(new JumpInsnNode(IFEQ, ifeq))
            .getInsnList();
    }
}

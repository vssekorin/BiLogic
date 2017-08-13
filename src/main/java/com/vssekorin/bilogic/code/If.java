/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
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
     * The name.
     */
    public static final String NAME = "if";

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * If-then line.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val expression = this.line
            .substring(If.NAME.length())
            .replaceAll("\\s+then$", "")
            .trim();
        val ifeq = new LabelNode();
        val end = new LabelNode();
        this.info.labels().add(ifeq, end);
        return new ChainInsnList()
            .add(new SomeExpression(this.info, expression).asBytecode())
            .add(new JumpInsnNode(IFEQ, ifeq));
    }
}

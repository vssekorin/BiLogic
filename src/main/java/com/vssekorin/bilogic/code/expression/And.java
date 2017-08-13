/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.code.Result;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.FramedString;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.internal.org.objectweb.asm.Opcodes.IFEQ;

/**
 * Logical conjunction.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class And implements Expression {

    /**
     * The name.
     */
    public static final String NAME = "and";

    /**
     * List of operands.
     */
    private final List<Expression> list;

    /**
     * Ctor.
     *
     * @param info The information
     * @param string Expression text
     */
    public And(final MethodInfo info, final String string) {
        this(
            Arrays.stream(string.split(new FramedString(And.NAME).text()))
                .map(item -> new SomeExpression(info, item))
                .collect(Collectors.toList())
        );
    }

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        val ifeq = new LabelNode();
        val end = new LabelNode();
        for (final Expression exp : this.list) {
            code.add(exp.asBytecode())
                .add(new JumpInsnNode(IFEQ, ifeq));
        }
        code.add(new Result(ifeq, end).asBytecode());
        return code;
    }
}

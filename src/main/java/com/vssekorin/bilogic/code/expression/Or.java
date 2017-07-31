/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.code.Result;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.internal.org.objectweb.asm.Opcodes.IFEQ;
import static jdk.internal.org.objectweb.asm.Opcodes.IFNE;

/**
 * Logical disjunction.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Or implements Expression {

    /**
     * List of expressions.
     */
    private final List<Expression> list;

    /**
     * Ctor.
     *
     * @param string Expression text
     */
    public Or(final String string) {
        this(
            Arrays.stream(string.split("\\s+or\\s+"))
                .map(SomeExpression::new)
                .collect(Collectors.toList())
        );
    }

    /**
     * Ctor.
     *
     * @param first First expression
     * @param second Second expression
     */
    public Or(final Expression first, final Expression second) {
        this(Arrays.asList(first, second));
    }

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        val ifne = new LabelNode();
        val ifeq = new LabelNode();
        val end = new LabelNode();
        val indexLast = this.list.size() - 1;
        this.list.stream()
            .limit(indexLast)
            .forEach(exp -> {
                code.add(exp.asBytecode());
                code.add(new JumpInsnNode(IFNE, ifne));
            });
        return code
            .add(this.list.get(indexLast).asBytecode())
            .add(new JumpInsnNode(IFEQ, ifeq))
            .add(ifne)
            .add(new Result(ifeq, end).asBytecode());
    }
}

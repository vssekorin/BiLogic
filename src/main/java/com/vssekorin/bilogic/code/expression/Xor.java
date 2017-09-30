/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.FramedString;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.internal.org.objectweb.asm.Opcodes.IXOR;

/**
 * exclusive disjunction.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Xor implements Expression {

    /**
     * The name.
     */
    public static final String NAME = "xor";

    /**
     * List of expressions.
     */
    private final List<Expression> list;

    /**
     * Ctor.
     *
     * @param info The information
     * @param string Expression text
     */
    public Xor(final MethodInfo info, final String string) {
        this(
            Arrays.stream(string.split(new FramedString(Xor.NAME).text()))
                .map(item -> new SomeExpression(info, item))
                .collect(Collectors.toList())
        );
    }

    @Override
    public ChainInsnList asBytecode() {
        val code = this.list.get(0).asBytecode();
        this.list.stream()
            .skip(1)
            .map(Expression::asBytecode)
            .map(item -> item.add(new InsnNode(IXOR)))
            .forEach(code::add);
        return code;
    }
}

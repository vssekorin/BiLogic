/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.code.Result;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.IFNE;

/**
 * Negation (logical complement).
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Not implements Expression {

    /**
     * The expression.
     */
    private final Expression expression;

    /**
     * Ctor.
     *
     * @param information The information
     * @param string Expression text
     */
    public Not(final MethodInfo information, final String string) {
        this(
            new SimpleExpression(
                information,
                string.replace("not", "").trim()
            )
        );
    }

    @Override
    public ChainInsnList asBytecode() {
        val ifne = new LabelNode();
        val end = new LabelNode();
        return new ChainInsnList()
            .add(this.expression.asBytecode())
            .add(new JumpInsnNode(IFNE, ifne))
            .add(new Result(ifne, end).asBytecode());
    }
}

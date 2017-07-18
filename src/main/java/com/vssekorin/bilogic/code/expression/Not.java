/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.code.Result;
import jdk.internal.org.objectweb.asm.tree.InsnList;
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
     * @param string Expression text
     */
    public Not(final String string) {
        this(
            new SimpleExpression(string.replace("not", "").trim())
        );
    }

    @Override
    public InsnList asBytecode() {
        val code = new InsnList();
        val ifne = new LabelNode();
        val end = new LabelNode();
        code.add(this.expression.asBytecode());
        code.add(new JumpInsnNode(IFNE, ifne));
        code.add(new Result(ifne, end).asBytecode());
        return code;
    }
}

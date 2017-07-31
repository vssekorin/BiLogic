/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.Vars;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Simple expression.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class SimpleExpression implements Expression {

    /**
     * Expression text.
     */
    private final String string;

    @Override
    public ChainedInsnList asBytecode() {
        val code = new ChainedInsnList();
        switch (this.string) {
            case "true": code.add(new InsnNode(ICONST_1)); break;
            case "false": code.add(new InsnNode(ICONST_0)); break;
            default: code.add(
                new VarInsnNode(ILOAD, Vars.getInstance().index(this.string))
            );
        }
        return code;
    }
}

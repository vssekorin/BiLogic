/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.util.Vars;
import jdk.internal.org.objectweb.asm.tree.InsnList;
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
    public InsnList asBytecode() {
        val code = new InsnList();
        switch (this.string) {
            case "true": code.add(new InsnNode(ICONST_1)); break;
            case "false": code.add(new InsnNode(ICONST_0)); break;
            default:
                val vars = Vars.getInstance();
                vars.add(this.string);
                code.add(new VarInsnNode(ILOAD, vars.index(this.string)));
        }
        return code;
    }
}

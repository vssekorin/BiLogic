/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * Output expression.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class OutExpression implements Code {

    /**
     * The expression.
     */
    private final String line;

    @Override
    public InsnList asBytecode() {
        val code = new InsnList();
        val expression = this.line.substring(1, this.line.length() - 1);
        code.add(new SomeExpression(expression).asBytecode());
        code.add(new MethodInsnNode(
            INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Z)Ljava/lang/StringBuilder;",
            false
        ));
        return code;
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Return.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Ret implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The line.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        return new ChainInsnList()
            .add(new VarInsnNode(ALOAD, this.info.vars().index("ret")))
            .add(new SomeExpression(
                this.info,
                this.line.replace("ret", "").trim()
            ).asBytecode())
            .add(new MethodInsnNode(
                INVOKESTATIC,
                "java/lang/Boolean",
                "valueOf",
                "(Z)Ljava/lang/Boolean;",
                false
            ))
            .add(new MethodInsnNode(
                INVOKEINTERFACE,
                "java/util/List",
                "add",
                "(Ljava/lang/Object;)Z",
                true
            ))
            .add(new InsnNode(POP));
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.TypeInsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * The return value.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class RetValue implements Code {

    /**
     * Index of invoke variable.
     */
    private final int invoke;

    /**
     * Variable list index.
     */
    private final int index;

    @Override
    public ChainInsnList asBytecode() {
        return new ChainInsnList()
            .add(new VarInsnNode(ALOAD, this.invoke))
            .add(new PushInt(this.index).asBytecode())
            .add(new MethodInsnNode(
                INVOKEINTERFACE,
                "java/util/List",
                "get",
                "(I)Ljava/lang/Object;",
                true
            ))
            .add(new TypeInsnNode(CHECKCAST, "java/lang/Boolean"))
            .add(new MethodInsnNode(
                INVOKEVIRTUAL,
                "java/lang/Boolean",
                "booleanValue",
                "()Z",
                false
            ));
    }
}

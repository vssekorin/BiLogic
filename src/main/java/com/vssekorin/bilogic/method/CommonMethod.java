/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.CustomObject;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.List;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Common method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class CommonMethod implements Method {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The method body.
     */
    private final List<String> body;

    @Override
    public MethodNode asMethodNode() {
        MethodNode node = new MethodNode(
            ACC_PUBLIC + ACC_STATIC,
            this.info.name(),
            "(" + String.join(
                "",
                Collections.nCopies(this.info.numberArgs(), "Z")
            ) + ")Ljava/util/List;",
            null,
            null
        );
        InsnList instructions = node.instructions;
        val ret = new CustomObject("java/util/ArrayList");
        this.info.vars().add("ret");
        val code = new ChainInsnList()
            .add(ret.codeNew())
            .add(ret.codeInit("()V"))
            .add(new VarInsnNode(ASTORE, this.info.vars().index("ret")))
            .add(new MethodBody(this.info, this.body).asBytecode())
            .getInsnList();
        instructions.add(code);
        return node;
    }
}

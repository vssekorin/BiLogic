/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import lombok.AllArgsConstructor;

import java.util.List;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_STATIC;

/**
 * Main method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class MainMethod implements Method {

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
            "main",
            "([Ljava/lang/String;)V",
            null,
            null
        );
        InsnList instructions = node.instructions;
        instructions.add(
            new MethodBody(this.info, this.body)
                .asBytecode()
                .getInsnList()
        );
        return node;
    }
}

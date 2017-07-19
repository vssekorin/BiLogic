/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.TypeInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Bytecode object.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class CustomObject {

    /**
     * Class of object.
     */
    private final String className;

    /**
     * Create bytecode for SomeObject::new without initialization.
     *
     * @return Code
     */
    public InsnList codeNew() {
        val code = new InsnList();
        code.add(new TypeInsnNode(NEW, this.className));
        code.add(new InsnNode(DUP));
        return code;
    }

    /**
     * Create bytecode for initialization object.
     *
     * @param types Type
     * @return Code
     */
    public InsnList codeInit(final String types) {
        val code = new InsnList();
        code.add(new MethodInsnNode(
            INVOKESPECIAL,
            this.className,
            "<init>",
            types,
            false
        ));
        return code;
    }

    /**
     * Create bytecode for method.
     *
     * @param method Method name
     * @param types Type
     * @return Code
     */
    public InsnList codeMethod(final String method, final String types) {
        val code = new InsnList();
        code.add(new MethodInsnNode(
            INVOKEVIRTUAL,
            this.className,
            method,
            types,
            false
        ));
        return code;
    }
}

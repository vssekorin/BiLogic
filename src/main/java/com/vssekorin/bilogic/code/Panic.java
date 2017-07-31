/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.CustomObject;
import jdk.internal.org.objectweb.asm.tree.*;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Exception.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @see RuntimeException
 * @since 1.0
 */
@AllArgsConstructor
public final class Panic implements Code {

    /**
     * Line that throw exception.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val exception = new CustomObject("java/lang/RuntimeException");
        val message = this.line.replace("panic", "").trim();
        return new ChainInsnList()
            .add(exception.codeNew())
            .add(new LdcInsnNode(message))
            .add(exception.codeInit("(Ljava/lang/String;)V"))
            .add(new InsnNode(ATHROW));
    }
}

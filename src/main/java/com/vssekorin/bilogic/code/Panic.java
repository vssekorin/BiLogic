/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

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
    public InsnList asBytecode() {
        val code = new InsnList();
        val exception = new CustomObject("java/lang/RuntimeException");
        val message = this.line.replace("panic", "").trim();
        code.add(exception.codeNew());
        code.add(new LdcInsnNode(message));
        code.add(exception.codeInit("(Ljava/lang/String;)V"));
        code.add(new InsnNode(ATHROW));
        return code;
    }
}

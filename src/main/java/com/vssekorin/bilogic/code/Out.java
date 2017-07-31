/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.CustomObject;
import jdk.internal.org.objectweb.asm.tree.*;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Output.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Out implements Code {

    /**
     * The output line.
     */
    private final String line;

    @Override
    public ChainedInsnList asBytecode() {
        val stringBuilder = new CustomObject("java/lang/StringBuilder");
        return new ChainedInsnList()
            .add(new FieldInsnNode(
                GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;"
            ))
            .add(stringBuilder.codeNew())
            .add(stringBuilder.codeInit("()V"))
            .add(this.codeOutput())
            .add(stringBuilder.codeMethod("toString", "()Ljava/lang/String;"))
            .add(new MethodInsnNode(
                INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false
            ));
    }

    /**
     * Create bytecode for output line.
     *
     * @return Bytecode
     */
    private ChainedInsnList codeOutput() {
        val code = new ChainedInsnList();
        val words = this.line
            .replace("out", "")
            .trim()
            .split("(?=\\{)|(?<=})");
        Arrays.stream(words)
            .map(OutElement::new)
            .map(Code::asBytecode)
            .forEach(code::add);
        return code;
    }
}

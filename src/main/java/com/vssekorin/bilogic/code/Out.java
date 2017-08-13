/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
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
     * The name.
     */
    public static final String NAME = "out";

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The output line.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val stringBuilder = new CustomObject("java/lang/StringBuilder");
        return new ChainInsnList()
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
    private ChainInsnList codeOutput() {
        val code = new ChainInsnList();
        val words = this.line
            .substring(Out.NAME.length())
            .trim()
            .split("(?=\\{)|(?<=})");
        Arrays.stream(words)
            .map(item -> new OutElement(this.info, item))
            .map(Code::asBytecode)
            .forEach(code::add);
        return code;
    }
}

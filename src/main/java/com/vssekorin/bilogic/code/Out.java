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
    public InsnList asBytecode() {
        val code = new InsnList();
        code.add(new FieldInsnNode(
            GETSTATIC,
            "java/lang/System",
            "out",
            "Ljava/io/PrintStream;"
        ));
        val stringBuilder = new CustomObject("java/lang/StringBuilder");
        code.add(stringBuilder.codeNew());
        code.add(stringBuilder.codeInit("()V"));
        val words = this.line
            .replace("out", "")
            .trim()
            .split("(?=\\{)|(?<=})");
        Arrays.stream(words)
            .map(OutElement::new)
            .map(Code::asBytecode)
            .forEach(code::add);
        code.add(stringBuilder.codeMethod("toString", "()Ljava/lang/String;"));
        code.add(new MethodInsnNode(
            INVOKEVIRTUAL,
            "java/io/PrintStream",
            "println",
            "(Ljava/lang/String;)V",
            false
        ));
        return code;
    }
}

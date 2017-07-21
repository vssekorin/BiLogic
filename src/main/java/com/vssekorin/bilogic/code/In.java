/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.CustomObject;
import com.vssekorin.bilogic.util.VarList;
import jdk.internal.org.objectweb.asm.tree.*;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Input.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class In implements Code {

    /**
     * The input line.
     */
    private final String line;

    @Override
    public InsnList asBytecode() {
        val code = new InsnList();
        val scanner = new CustomObject("java/util/Scanner");
        code.add(scanner.codeNew());
        code.add(new FieldInsnNode(
            GETSTATIC,
            "java/lang/System",
            "in",
            "Ljava/io/InputStream;"
        ));
        code.add(scanner.codeInit("(Ljava/io/InputStream;)V"));
        code.add(new VarInsnNode(ASTORE, 1));
        val vars = this.line.replace("in", "").trim();
        val varsIndex = new VarList(vars).asIndexList();
        for (int index : varsIndex) {
            code.add(new VarInsnNode(ALOAD, 1));
            code.add(scanner.codeMethod("nextBoolean", "()Z"));
            code.add(new VarInsnNode(ISTORE, index));
        }
        return code;
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.CustomObject;
import com.vssekorin.bilogic.util.VarList;
import com.vssekorin.bilogic.util.Vars;
import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
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
     * The name.
     */
    public static final String NAME = "in";

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The input line.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val scanner = new CustomObject("java/util/Scanner");
        val code = new ChainInsnList()
            .add(scanner.codeNew())
            .add(new FieldInsnNode(
                GETSTATIC,
                "java/lang/System",
                "in",
                "Ljava/io/InputStream;"
            ))
            .add(scanner.codeInit("(Ljava/io/InputStream;)V"))
            .add(new VarInsnNode(ASTORE, this.info.vars().index(In.NAME)));
        val vars = this.line.substring(In.NAME.length()).trim();
        new VarList(this.info, vars)
            .asIndexStream()
            .filter(i -> i != Vars.IGNORED)
            .mapToObj(i -> new VarInsnNode(ISTORE, i))
            .forEach(node -> code
                .add(new VarInsnNode(ALOAD, this.info.vars().index(In.NAME)))
                .add(scanner.codeMethod("nextBoolean", "()Z"))
                .add(node)
            );
        return code;
    }
}

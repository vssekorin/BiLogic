/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.ALOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.ARETURN;
import static jdk.internal.org.objectweb.asm.Opcodes.RETURN;

/**
 * The end of method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class EndDef implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        if (this.info.name().equals("main")) {
            code.add(new InsnNode(RETURN));
        } else {
            code.add(new VarInsnNode(ALOAD, this.info.vars().index("ret")))
                .add(new InsnNode(ARETURN));
        }
        return code;
    }
}

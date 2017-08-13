/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MainMethod;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

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
        if (this.info.name().equals(MainMethod.NAME)) {
            code.add(new InsnNode(RETURN));
        } else {
            code.add(new VarInsnNode(ALOAD, this.info.vars().index(Ret.NAME)))
                .add(new InsnNode(ARETURN));
        }
        return code;
    }
}

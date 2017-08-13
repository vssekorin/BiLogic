package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.BIPUSH;
import static jdk.internal.org.objectweb.asm.Opcodes.SIPUSH;

/**
 * @author VsSekorin
 */
@AllArgsConstructor
public final class IPush implements Code {

    /**
     * The value.
     */
    private final int value;

    /**
     * The maximum value for which the BIPUSH can be used.
     */
    private static final int BIPUSH_MAX = 127;

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        if (this.value <= BIPUSH_MAX) {
            code.add(new VarInsnNode(BIPUSH, this.value));
        } else {
            code.add(new VarInsnNode(SIPUSH, this.value));
        }
        return code;
    }
}

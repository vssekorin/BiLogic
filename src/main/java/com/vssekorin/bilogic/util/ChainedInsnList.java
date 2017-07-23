package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import lombok.Getter;

/**
 * @author VsSekorin
 */
public final class ChainedInsnList {

    /**
     * The origin InsnList.
     */
    @Getter
    private final InsnList insnList = new InsnList();

    /**
     * Add node.
     *
     * @param node The node
     * @return modified this
     */
    public ChainedInsnList add(final AbstractInsnNode node) {
        this.insnList.add(node);
        return this;
    }

    /**
     * Add list of node.
     *
     * @param list The list
     * @return modified this
     */
    public ChainedInsnList add(final InsnList list) {
        this.insnList.add(list);
        return this;
    }
}

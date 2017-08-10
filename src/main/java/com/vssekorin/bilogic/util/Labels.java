/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.LabelNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Labels.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Labels {

    /**
     * Deque of labels pair.
     */
    private final Deque<Pair<LabelNode>> list = new ArrayDeque<>();

    /**
     * Add labels as pair.
     *
     * @param first First label
     * @param second Second label
     */
    public void add(final LabelNode first, final LabelNode second) {
        add(new Pair<>(first, second));
    }

    /**
     * Add pair.
     *
     * @param pair The pair of labels
     */
    public void add(final Pair<LabelNode> pair) {
        this.list.push(pair);
    }

    /**
     * Peek pair.
     *
     * @return The pair of labels
     */
    public Pair<LabelNode> peek() {
        return this.list.peek();
    }

    /**
     * Pop pair.
     *
     * @return The pair of labels
     */
    public Pair<LabelNode> pop() {
        return this.list.pop();
    }
}

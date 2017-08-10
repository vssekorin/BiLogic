/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import jdk.internal.org.objectweb.asm.tree.MethodNode;

/**
 * Method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @see MethodNode
 * @since 1.0
 */
public interface Method {

    /**
     * Create node for method.
     *
     * @return The node
     */
    MethodNode asMethodNode();
}

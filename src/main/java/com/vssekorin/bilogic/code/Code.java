/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import jdk.internal.org.objectweb.asm.tree.InsnList;

/**
 * Code.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Code {

    /**
     * Convert to JVM bytecode.
     *
     * @see InsnList
     * @return Bytecode
     */
    InsnList asBytecode();
}
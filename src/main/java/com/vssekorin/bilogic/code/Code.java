/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainedInsnList;

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
     * @see ChainedInsnList
     * @return Bytecode
     */
    ChainedInsnList asBytecode();
}

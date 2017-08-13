/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;

/**
 * Load the int value onto the stack.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class PushInt implements Code {

    /**
     * The value.
     */
    private final int value;

    /**
     * The maximum value for which the ICONST can be used.
     */
    private static final int ICONST_MAX = 5;

    @Override
    public ChainInsnList asBytecode() {
        final Code code;
        if (this.value <= ICONST_MAX) {
            code = new IConst(this.value);
        } else {
            code = new IPush(this.value);
        }
        return code.asBytecode();
    }
}

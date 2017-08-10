/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;

/**
 * Element of output.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class OutElement implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The element.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        final Code code;
        if (this.line.startsWith("{") && this.line.endsWith("}")) {
            code = new OutExpression(this.info, this.line);
        } else {
            code = new OutString(this.line);
        }
        return code.asBytecode();
    }
}

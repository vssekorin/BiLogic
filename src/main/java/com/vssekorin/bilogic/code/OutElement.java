/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import jdk.internal.org.objectweb.asm.tree.InsnList;
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
     * The element.
     */
    private final String line;

    @Override
    public InsnList asBytecode() {
        final Code code;
        if (this.line.startsWith("{") && this.line.endsWith("}")) {
            code = new OutExpression(this.line);
        } else {
            code = new OutString(this.line);
        }
        return code.asBytecode();
    }
}

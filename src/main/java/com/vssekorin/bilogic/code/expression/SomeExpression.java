/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.code.Code;
import com.vssekorin.bilogic.util.ChainedInsnList;
import lombok.AllArgsConstructor;

/**
 * Abstract expression.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class SomeExpression implements Expression {

    /**
     * Expression text.
     */
    private final String string;

    @Override
    public ChainedInsnList asBytecode() {
        final Code code;
        if (this.string.contains(" ")) {
            code = new CompoundExpression(this.string);
        } else {
            code = new SimpleExpression(this.string);
        }
        return code.asBytecode();
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.error.IncorrectExpression;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import lombok.AllArgsConstructor;

/**
 * Compound expression.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class CompoundExpression implements Expression {

    /**
     * Expression text
     */
    private final String string;

    @Override
    public InsnList asBytecode() {
        final Expression expression;
        if (this.string.contains(" -> ")) {
            expression = new Implication(this.string);
        } else {
            if (this.string.contains(" or ")) {
                expression = new Or(this.string);
            } else {
                if (this.string.contains(" and ")) {
                    expression = new And(this.string);
                } else {
                    if (this.string.contains("not ")) {
                        expression = new Not(this.string);
                    } else {
                        throw new IncorrectExpression(this.string);
                    }
                }
            }
        }
        return expression.asBytecode();
    }
}

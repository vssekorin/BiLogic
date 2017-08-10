/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;

/**
 * Material conditional (implication).
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Implication implements Expression {

    /**
     * The first operand.
     */
    private final Expression first;

    /**
     * The second operand.
     */
    private final Expression second;

    /**
     * Ctor.
     *
     * @param information The information
     * @param string Expression text
     */
    public Implication(final MethodInfo information, final String string) {
        this(
            information,
            string.split("\\s+->\\s+", 2)[0],
            string.split("\\s+->\\s+", 2)[1]
        );
    }

    /**
     * Ctor.
     *
     * @param information The information
     * @param firstExp Expression text of first operand
     * @param secondExp Expression text of second operand
     */
    public Implication(
        final MethodInfo information,
        final String firstExp,
        final String secondExp
    ) {
        this(
            new SomeExpression(information, firstExp),
            new SomeExpression(information, secondExp)
        );
    }

    @Override
    public ChainInsnList asBytecode() {
        return new Or(
            new Not(this.first),
            this.second
        ).asBytecode();
    }
}

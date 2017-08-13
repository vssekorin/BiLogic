/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.FramedString;
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
     * The name.
     */
    public static final String NAME = "->";

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
     * @param info The information
     * @param string Expression text
     */
    public Implication(final MethodInfo info, final String string) {
        this(
            info,
            string.split(new FramedString(Implication.NAME).text(), 2)[0],
            string.split(new FramedString(Implication.NAME).text(), 2)[1]
        );
    }

    /**
     * Ctor.
     *
     * @param info The information
     * @param firstExp Expression text of first operand
     * @param secondExp Expression text of second operand
     */
    public Implication(
        final MethodInfo info, final String firstExp, final String secondExp) {
        this(
            new SomeExpression(info, firstExp),
            new SomeExpression(info, secondExp)
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

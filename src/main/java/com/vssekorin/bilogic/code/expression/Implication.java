/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code.expression;

import jdk.internal.org.objectweb.asm.tree.InsnList;
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
     * @param string Expression text
     */
    public Implication(final String string) {
        this(
            string.split("\\s+->\\s+", 2)[0],
            string.split("\\s+->\\s+", 2)[1]
        );
    }

    /**
     * Ctor.
     *
     * @param first Expression text of first operand
     * @param second Expression text of second operand
     */
    public Implication(final String first, final String second) {
        this(new SomeExpression(first), new SomeExpression(second));
    }

    @Override
    public InsnList asBytecode() {
        return new Or(
                new Not(this.first),
                this.second
            ).asBytecode();
    }
}

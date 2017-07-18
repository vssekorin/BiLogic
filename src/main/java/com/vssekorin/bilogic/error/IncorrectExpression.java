/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.error;

/**
 * Incorrect expression exception.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class IncorrectExpression extends RuntimeException {

    /**
     * Ctor.
     *
     * @param exp Expression
     */
    public IncorrectExpression(final String exp) {
        super("Incorrect expression: " + exp);
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.error;

/**
 * Unknown variable exception.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class UnknownVariableException extends RuntimeException {

    /**
     * Ctor.
     *
     * @param var Variable name
     */
    public UnknownVariableException(final String var) {
        super("Unknown var: " + var);
    }
}

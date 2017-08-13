/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.error;

/**
 * Incorrect line exception.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class IncorrectLineException extends RuntimeException {

    /**
     * Ctor.
     *
     * @param line Code line
     */
    public IncorrectLineException(final String line) {
        super("Incorrect code line: " + line);
    }
}

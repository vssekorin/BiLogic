/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.error;

/**
 * Ret statement in main.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RetInMainException extends RuntimeException {

    /**
     * Ctor.
     *
     * @param line Code line
     */
    public RetInMainException(final String line) {
        super("Don't use ret in main method. Line: " + line);
    }
}

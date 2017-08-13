/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.error;

/**
 * Not relevant operation exception.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RelevantOperationException extends RuntimeException {

    /**
     * Ctor.
     *
     * @param method The method name
     */
    public RelevantOperationException(final String method) {
        super("Not relevant operation in " + method + " method.");
    }
}

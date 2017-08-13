package com.vssekorin.bilogic.error;

/**
 * @author VsSekorin
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

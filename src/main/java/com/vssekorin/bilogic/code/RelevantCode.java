/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.error.RelevantOperationException;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;

/**
 * You can not return a value from the main method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class RelevantCode implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The origin Code.
     */
    private final Code origin;

    /**
     * The method name.
     */
    private final String method;

    @Override
    public ChainInsnList asBytecode() {
        if (this.info.name().equals(this.method)) {
            throw new RelevantOperationException(this.method);
        }
        return this.origin.asBytecode();
    }
}

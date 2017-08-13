/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.error.RetInMainException;
import com.vssekorin.bilogic.method.MainMethod;
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
public final class RelevantRet implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The origin Ret.
     */
    private final Ret origin;

    @Override
    public ChainInsnList asBytecode() {
        if (this.info.name().equals(MainMethod.NAME)) {
            throw new RetInMainException();
        }
        return this.origin.asBytecode();
    }
}

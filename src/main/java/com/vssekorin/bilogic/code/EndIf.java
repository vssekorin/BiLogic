/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;
import lombok.val;

/**
 * End of if statement.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class EndIf implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    @Override
    public ChainInsnList asBytecode() {
        val pair = this.info.labels().pop();
        return new ChainInsnList().add(pair.second());
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.Labels;
import lombok.val;

/**
 * End of if statement.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class EndIf implements Code {

    @Override
    public ChainInsnList asBytecode() {
        val pair = Labels.getInstance().pop();
        return new ChainInsnList().add(pair.second());
    }
}

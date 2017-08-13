/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 BiLogic vssekorin.com
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;

/**
 * Assignment.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Assignment implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The line with assignment.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        final Code code;
        if (this.line.contains(" " + Is.NAME + " ")) {
            code = new Is(this.info, this.line);
        } else {
            code = new Invoke(this.info, this.line);
        }
        return code.asBytecode();
    }
}

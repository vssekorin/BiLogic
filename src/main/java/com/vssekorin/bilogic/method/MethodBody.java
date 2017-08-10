/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import com.vssekorin.bilogic.code.BLCodeLine;
import com.vssekorin.bilogic.code.Code;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.List;

/**
 * The body of method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class MethodBody implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The method body.
     */
    private final List<String> body;

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        this.body.stream()
            .map(item -> new BLCodeLine(this.info, item))
            .map(Code::asBytecode)
            .forEach(code::add);
        return code;
    }
}

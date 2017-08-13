/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.error.IncorrectLineException;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.method.SomeMethod;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;
import lombok.val;

/**
 * End line.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class End implements Code {

    /**
     * The name.
     */
    public static final String NAME = "end";

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * Line contains End.NAME.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val word = this.line.substring(End.NAME.length()).trim();
        final Code code;
        switch (word) {
            case If.NAME: code = new EndIf(this.info); break;
            case While.NAME: code = new EndWhile(this.info); break;
            case SomeMethod.NAME: code = new EndDef(this.info); break;
            default: throw new IncorrectLineException(this.line);
        }
        return code.asBytecode();
    }
}

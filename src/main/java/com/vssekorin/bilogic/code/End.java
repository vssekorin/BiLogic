/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.error.IncorrectLine;
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
     * Line contains "end".
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val word = this.line.replace("end", "").trim();
        final Code code;
        switch (word) {
            case "if": code = new EndIf(); break;
            case "while": code = new EndWhile(); break;
            default: throw new IncorrectLine(line);
        }
        return code.asBytecode();
    }
}

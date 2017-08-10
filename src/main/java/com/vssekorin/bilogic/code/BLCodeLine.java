/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.error.IncorrectLine;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;

/**
 * BiLogic code line.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class BLCodeLine implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * Code line.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val words = Arrays.asList(this.line.split("\\s+"));
        final Code code;
        if (words.contains("is") || words.contains("invoke")) {
            code = new Assignment(this.info, this.line);
        } else {
            switch (words.get(0)) {
                case "in": code = new In(this.info, this.line); break;
                case "out": code = new Out(this.info, this.line); break;
                case "panic": code = new Panic(line); break;
                case "if": code = new If(this.info, this.line); break;
                case "else": code = new Else(this.info); break;
                case "while": code = new While(this.info, this.line); break;
                case "end": code = new End(this.info, this.line); break;
                case "ret": code = new Ret(this.info, this.line); break;
                default: throw new IncorrectLine(line);
            }
        }
        return code.asBytecode();
    }
}

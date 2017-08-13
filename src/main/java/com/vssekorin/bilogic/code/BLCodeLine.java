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
        if (words.contains(Is.NAME) || words.contains(Invoke.NAME)) {
            code = new Assignment(this.info, this.line);
        } else {
            switch (words.get(0)) {
                case In.NAME: code = new In(this.info, this.line); break;
                case Out.NAME: code = new Out(this.info, this.line); break;
                case Panic.NAME: code = new Panic(this.line); break;
                case If.NAME: code = new If(this.info, this.line); break;
                case Else.NAME: code = new Else(this.info); break;
                case While.NAME: code = new While(this.info, this.line); break;
                case End.NAME: code = new End(this.info, this.line); break;
                case Ret.NAME:
                    code = new RelevantRet(
                        this.info,
                        new Ret(this.info, this.line)
                    );
                    break;
                default: throw new IncorrectLine(line);
            }
        }
        return code.asBytecode();
    }
}

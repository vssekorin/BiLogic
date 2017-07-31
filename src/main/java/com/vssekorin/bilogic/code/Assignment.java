/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 BiLogic vssekorin.com
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import com.vssekorin.bilogic.util.ChainedInsnList;
import com.vssekorin.bilogic.util.VarList;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.ISTORE;

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
     * The line with assignment.
     */
    private final String line;

    @Override
    public ChainedInsnList asBytecode() {
        val code = new ChainedInsnList();
        val words = line.split("\\s+is\\s+");
        val varsIndex = new VarList(words[0]).asIndexList();
        val expression = new SomeExpression(words[1]);
        for (int index : varsIndex) {
            code.add(expression.asBytecode());
            code.add(new VarInsnNode(ISTORE, index));
        }
        return code;
    }
}

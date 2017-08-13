/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.SomeExpression;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.FramedString;
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
public final class Is implements Code {

    /**
     * The name.
     */
    public static final String NAME = "is";

    /**
     * The information about method.
     */
    private final MethodInfo info;

    /**
     * The line.
     */
    private final String line;

    @Override
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        val words = this.line.split(new FramedString(Is.NAME).text());
        val varsIndex = new VarList(this.info, words[0]).asIndexList();
        val expression = new SomeExpression(this.info, words[1]);
        for (final int index : varsIndex) {
            code.add(expression.asBytecode());
            code.add(new VarInsnNode(ISTORE, index));
        }
        return code;
    }
}

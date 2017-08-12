/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.code.expression.Expression;
import com.vssekorin.bilogic.code.expression.SimpleExpression;
import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import com.vssekorin.bilogic.util.VarList;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Invoke method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class Invoke implements Code {

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
        val words = this.line.split("\\s+invoke\\s+");
        val varsIndex = new VarList(this.info, words[0]).asIndexList();
        val invoke = Arrays.asList(words[1].split("\\s+"));
        val method = invoke.get(0);
        val invokeIndex = this.info.vars().index("invoke");
        invoke.subList(1, invoke.size()).stream()
            .map(item -> new SimpleExpression(this.info, item))
            .map(Expression::asBytecode)
            .forEach(code::add);
        code.add(new MethodInsnNode(
            INVOKESTATIC,
            this.info.className(),
            method,
            "(" + String.join(
                "",
                Collections.nCopies(invoke.size() - 1, "Z")
            ) + ")Ljava/util/List;",
            false))
            .add(new VarInsnNode(ASTORE, invokeIndex));
        for (int i = 0; i < varsIndex.size(); i++) {
            val index = varsIndex.get(i);
            if (index != -1) {
                code.add(new RetValue(invokeIndex, i).asBytecode())
                    .add(new VarInsnNode(ISTORE, index));
            }
        }
        return code;
    }
}

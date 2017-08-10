/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import jdk.internal.org.objectweb.asm.tree.MethodNode;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.List;

/**
 * Some method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class SomeMethod implements Method {

    /**
     * The name of class.
     */
    private final String className;

    /**
     * The name of method.
     */
    private final String name;

    /**
     * The method body.
     */
    private final List<String> body;

    @Override
    public MethodNode asMethodNode() {
        final Method method;
        val header = this.name.split("\\s+");
        val info = new MethodInfo(
            this.className,
            header[0],
            Arrays.copyOfRange(header, 1, header.length)
        );
        info.vars().add("in");
        info.vars().add("invoke");
        if (info.name().equals("main")) {
            method = new MainMethod(info, body);
        } else {
            method = new CommonMethod(info, body);
        }
        return method.asMethodNode();
    }
}

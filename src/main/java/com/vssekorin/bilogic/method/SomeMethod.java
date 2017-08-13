/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import com.vssekorin.bilogic.code.In;
import com.vssekorin.bilogic.code.Invoke;
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
     * The name.
     */
    public static final String NAME = "def";

    /**
     * The name of class.
     */
    private final String klass;

    /**
     * The name of method.
     */
    private final String method;

    /**
     * The method body.
     */
    private final List<String> body;

    @Override
    public MethodNode asMethodNode() {
        final Method node;
        val header = this.method.split("\\s+");
        val info = new MethodInfo(
            this.klass,
            header[0],
            Arrays.copyOfRange(header, 1, header.length)
        );
        info.vars().add(In.NAME);
        info.vars().add(Invoke.NAME);
        if (info.name().equals(MainMethod.NAME)) {
            node = new MainMethod(info, this.body);
        } else {
            node = new CommonMethod(info, this.body);
        }
        return node.asMethodNode();
    }
}

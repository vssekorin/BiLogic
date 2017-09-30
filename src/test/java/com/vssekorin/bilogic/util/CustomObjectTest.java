/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.TypeInsnNode;
import lombok.val;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link CustomObject}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class CustomObjectTest {

    private final static String name = "java/util/ArrayList";

    private final CustomObject object =
        new CustomObject(name);

    @Test
    public void codeNew() throws Exception {
        assertTrue(
            new InsnListEquals(
                this.object.codeNew(),
                new ChainInsnList()
                    .add(new TypeInsnNode(NEW, name))
                    .add(new InsnNode(DUP))
            ).value()
        );
    }

    @Test
    public void codeInit() throws Exception {
        val types = "()V";
        assertTrue(
            new InsnListEquals(
                this.object.codeInit(types),
                new ChainInsnList()
                    .add(new MethodInsnNode(
                        INVOKESPECIAL,
                        name,
                        "<init>",
                        types,
                        false
                    ))
            ).value()
        );
    }

    @Test
    public void codeMethod() throws Exception {
        val method = "method";
        val types = "()V";
        assertTrue(
            new InsnListEquals(
                this.object.codeMethod(method, types),
                new ChainInsnList()
                    .add(new MethodInsnNode(
                        INVOKEVIRTUAL,
                        name,
                        method,
                        types,
                        false
                    ))
            ).value()
        );
    }
}

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.InsnListEquals;
import com.vssekorin.bilogic.util.Labels;
import com.vssekorin.bilogic.util.Pair;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.LabelNode;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test case for {@link EndIf}.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class EndIfTest {

    @Before
    public void setUp() throws Exception {
        Labels.getInstance().add(
            new Pair<>(
                new LabelNode(),
                new LabelNode()
            )
        );
    }

    @Test
    public void asBytecode() throws Exception {
        val code = new EndIf().asBytecode();
        val list = new InsnList();
        list.add(new LabelNode());
        assertTrue(new InsnListEquals(code, list).value());
    }
}

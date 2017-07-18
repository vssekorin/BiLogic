/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List of variables.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class VarList {

    /**
     * Text of list.
     */
    private final String string;

    /**
     * Convert to list of variables index.
     *
     * @return List of indexes
     */
    public List<Integer> asIndexList() {
        val vars = Vars.getInstance();
        return Arrays.stream(string.split(",\\s+"))
            .peek(vars::add)
            .map(vars::index)
            .collect(Collectors.toList());
    }
}

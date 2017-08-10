/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import com.vssekorin.bilogic.method.MethodInfo;
import lombok.AllArgsConstructor;

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
     * The information about method.
     */
    private final MethodInfo info;

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
        return Arrays.stream(string.split(",\\s+"))
            .peek(this.info.vars()::add)
            .map(this.info.vars()::index)
            .collect(Collectors.toList());
    }
}

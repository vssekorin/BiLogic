/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * The pair.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of elements.
 * @since 1.0
 */
@AllArgsConstructor
public final class Pair<T> {

    /**
     * The first element.
     */
    @Getter @Accessors(fluent = true)
    private final T first;

    /**
     * The second element.
     */
    @Getter @Accessors(fluent = true)
    private final T second;
}

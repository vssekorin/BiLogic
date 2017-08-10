/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.method;

import com.vssekorin.bilogic.util.Labels;
import com.vssekorin.bilogic.util.Vars;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * The information about method.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class MethodInfo {

    /**
     * The name of class.
     */
    @Getter @Accessors(fluent = true)
    private final String className;

    /**
     * The name of method.
     */
    @Getter @Accessors(fluent = true)
    private final String name;

    /**
     * The number of method arguments.
     */
    @Getter @Accessors(fluent = true)
    private final int numberArgs;

    /**
     * The variables that method use.
     */
    @Getter @Accessors(fluent = true)
    private final Vars vars;

    /**
     * The labels for method.
     */
    @Getter @Accessors(fluent = true)
    private final Labels labels;

    /**
     * Ctor.
     *
     * @param klass Class name
     * @param method Method name
     * @param args Arguments
     */
    public MethodInfo(
        final String klass,
        final String method,
        final List<String> args
    ) {
        this.className = klass;
        this.name = method;
        this.numberArgs = args.size();
        this.vars = new Vars(args);
        this.labels = new Labels();
    }

    /**
     * Ctor.
     *
     * @param klass Class name
     * @param method Method name
     * @param args Arguments
     */
    public MethodInfo(
        final String klass,
        final String method,
        final String... args
    ) {
        this(klass, method, Arrays.asList(args));
    }
}

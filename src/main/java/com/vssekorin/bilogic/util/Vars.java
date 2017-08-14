/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import com.vssekorin.bilogic.error.UnknownVariableException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Variables.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Vars {

    /**
     * Ignore variable.
     */
    private static final String IGNORED_VAR = "_";

    /**
     * Index of ignore variable.
     */
    public static final int IGNORED = -1;

    /**
     * List of variables name.
     */
    private final List<String> list;

    /**
     * Ctor.
     */
    public Vars() {
        this.list = new ArrayList<>();
    }

    /**
     * Ctor.
     *
     * @param collection Variables
     */
    public Vars(final Collection<String> collection) {
        this();
        this.list.addAll(collection);
    }

    /**
     * Add variable.
     *
     * @param var Variable name
     */
    public void add(final String var) {
        if (!var.equals(IGNORED_VAR) && !this.list.contains(var)) {
            this.list.add(var);
        }
    }

    /**
     * Index of variable.
     *
     * @param var Variable name
     * @return Index
     */
    public int index(final String var) {
        final int result;
        if (var.equals(IGNORED_VAR)) {
            result = IGNORED;
        } else if (this.list.contains(var)) {
            result = this.list.indexOf(var);
        } else {
            throw new UnknownVariableException(var);
        }
        return result;
    }
}

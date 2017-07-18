/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import com.vssekorin.bilogic.error.UnknownVariable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton for variables.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Vars {

    /**
     * Instance.
     */
    private static Vars ourInstance = new Vars();

    /**
     * Getter.
     *
     * @return Instance
     */
    public static Vars getInstance() {
        return ourInstance;
    }

    /**
     * List of variables name.
     */
    private final List<String> list = new ArrayList<>();

    /**
     * Add variable.
     *
     * @param var Variable name
     */
    public void add(final String var) {
        if (!this.contains(var)) {
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
        if (!this.contains(var)) {
            throw new UnknownVariable(var);
        }
        return this.list.indexOf(var) + 2;
    }

    /**
     * Check list contains variable.
     *
     * @param var Variable name
     * @return boolean
     */
    public boolean contains(final String var) {
        return this.list.contains(var);
    }
}

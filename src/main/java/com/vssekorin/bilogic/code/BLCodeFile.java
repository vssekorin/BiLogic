/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.util.ChainInsnList;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * BiLogic code file.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class BLCodeFile implements Code {

    /**
     * The extension of files with BiLogic code.
     */
    public static final String EXTENSION = ".bilog";

    /**
     * The path of file.
     */
    private final Path path;

    @Override
    @SneakyThrows
    public ChainInsnList asBytecode() {
        val code = new ChainInsnList();
        Files.lines(this.path)
            .map(String::trim)
            .filter(str -> !str.isEmpty())
            .filter(str -> !str.startsWith("--"))
            .map(BLCodeLine::new)
            .map(Code::asBytecode)
            .forEach(code::add);
        return code;
    }
}

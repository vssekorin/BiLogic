/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic;

import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.util.Optional;

/**
 * BiLogic file.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class BLFile {

    /**
     * The extension of files with BiLogic code.
     */
    public static final String EXTENSION = ".bilog";

    /**
     * The path of file.
     */
    private final Path path;

    /**
     * Name of file.
     *
     * @return The name
     */
    public String name() {
        return Optional.ofNullable(path)
            .map(Path::getFileName)
            .map(Path::toString)
            .map(item -> item.replace(BLFile.EXTENSION, ""))
            .orElseThrow(IllegalArgumentException::new);
    }
}

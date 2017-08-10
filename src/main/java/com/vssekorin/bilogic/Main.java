/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main class.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Main {

    /**
     * Entry point.
     *
     * @param args Command line arguments
     */
    @SneakyThrows
    public static void main(final String[] args) {
        Files.walk(Paths.get(args[0]))
            .parallel()
            .filter(Files::isRegularFile)
            .filter(item -> item.toString().endsWith(BLCodeFile.EXTENSION))
            .map(path ->
                new JavaClassFile(
                    path,
                    new BLCodeFile(path).asMetodList()
                )
            )
            .forEach(JavaClass::save);
    }
}

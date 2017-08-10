/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic;

import com.vssekorin.bilogic.method.Method;
import com.vssekorin.bilogic.method.SomeMethod;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * BiLogic code file.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class BLCodeFile {

    /**
     * The extension of files with BiLogic code.
     */
    public static final String EXTENSION = ".bilog";

    /**
     * The path of file.
     */
    private final Path path;

    /**
     * Create list of methods.
     *
     * @return The list
     */
    public List<MethodNode> asMetodList() {
        val result = new ArrayList<MethodNode>();
        val methods = new HashMap<String, List<String>>();
        String header = "";
        for (final String line : this.pureCode()) {
            if (line.startsWith("def ")) {
                header = line.substring("def".length()).trim();
                methods.put(header, new ArrayList<>());
            } else {
                val body = methods.get(header);
                body.add(line);
                methods.put(header, body);
            }
        }
        methods.entrySet().stream()
            .map(item -> new SomeMethod(
                BLCodeFile.className(this.path),
                item.getKey(),
                item.getValue()
            ))
            .map(Method::asMethodNode)
            .forEach(result::add);
        return result;
    }

    /**
     * Code without comments and empty string.
     *
     * @return The code
     */
    @SneakyThrows
    private List<String> pureCode() {
        return Files.lines(this.path)
            .map(String::trim)
            .filter(str -> !str.isEmpty())
            .filter(str -> !str.startsWith("--"))
            .collect(toList());
    }

    /**
     * Class name of files.
     *
     * @param path The path of file
     * @return The name
     */
    public static String className(final Path path) {
        return Optional.ofNullable(path)
            .map(Path::getFileName)
            .map(Path::toString)
            .map(item -> item.replace(BLCodeFile.EXTENSION, ""))
            .orElseThrow(IllegalArgumentException::new);
    }
}

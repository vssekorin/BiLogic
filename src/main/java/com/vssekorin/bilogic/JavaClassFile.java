/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.InsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Java class file.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class JavaClassFile implements JavaClass {

    /**
     * The path of file.
     */
    private final Path path;

    /**
     * Bytecode of method main.
     */
    private final InsnList methodMain;

    @Override @SneakyThrows
    public void save() {
        ClassNode classNode = new ClassNode();
        this.header(classNode).methods.add(this.main());
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        Files.write(Paths.get(this.newPath()), writer.toByteArray());
    }

    /**
     * Create header.
     *
     * @param node ClassNode
     * @return ClassNode
     */
    private ClassNode header(final ClassNode node) {
        node.access = ACC_PUBLIC + ACC_SUPER;
        node.name = this.name();
        node.superName = "java/lang/Object";
        node.version = V1_8;
        return node;
    }

    /**
     * Create method public static void main(String[] args).
     *
     * @return Method
     */
    private MethodNode main() {
        MethodNode node = new MethodNode(
            ACC_PUBLIC + ACC_STATIC,
            "main",
            "([Ljava/lang/String;)V",
            null,
            null
        );
        InsnList instructions = node.instructions;
        instructions.add(this.methodMain);
        instructions.add(new InsnNode(RETURN));
        return node;
    }

    /**
     * Filename without extension.
     *
     * @return Filename
     */
    private String name() {
        return this.path
            .getFileName()
            .toString()
            .replace(".bilog", "");
    }

    /**
     * New path for file.
     *
     * @return Path
     */
    private String newPath() {
        return this.path
            .toAbsolutePath()
            .toString()
            .replace(".bilog", ".class");
    }
}

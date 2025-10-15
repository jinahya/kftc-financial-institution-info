package com.github.jinahya.kftc.financial.institution.info;

/*-
 * #%L
 * kftc-financial-institution-info
 * %%
 * Copyright (C) 2024 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({"java:S101"})
public final class _IoTestUtils {

    static void writeObject(final File file, final Object obj) throws IOException {
        try (var fos = new FileOutputStream(file);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    static void writeObject(final Path path, final Object obj) throws IOException {
        writeObject(path.toFile(), obj);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    static <T> T readObject(final InputStream stream) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(stream, "stream is null");
        try (var oos = new ObjectInputStream(stream)) {
            return (T) oos.readObject();
        }
    }

    static <T> T readObject(final File file) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(file, "file is null");
        try (var fos = new FileInputStream(file)) {
            return readObject(fos);
        }
    }

    static <T> T readObject(final Path path) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(path, "path is null");
        return readObject(path.toFile());
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Path resourceDirectory() throws IOException {
        final var directory = Stream.concat(
                        Stream.of("src", "main", "resources"),
                        Arrays.stream(_IoTestUtils.class.getPackage().getName().split("\\."))
                )
                .reduce(Path.of("."), Path::resolve, (p1, p2) -> p1)
                .toAbsolutePath()
                .normalize();
        Files.createDirectories(directory);
        return directory;
    }

    static Path resourceFile(final String name) throws IOException {
        return resourceDirectory().resolve(name);
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Path testResourceDirectory() throws IOException {
        final var directory = Stream.concat(
                        Stream.of("src", "test", "resources"),
                        Arrays.stream(_IoTestUtils.class.getPackage().getName().split("\\."))
                )
                .reduce(Path.of("."), Path::resolve, (p1, p2) -> p1)
                .toAbsolutePath()
                .normalize();
        Files.createDirectories(directory);
        return directory;
    }

    static Path testResourceFile(final String name) throws IOException {
        return testResourceDirectory().resolve(name);
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Path buildOutputDirectory() throws IOException {
        final var directory = Path.of("target")
                .toAbsolutePath()
                .normalize();
        Files.createDirectories(directory);
        return directory;
    }

    public static Path buildOutputFile(final String name) throws IOException {
        return buildOutputDirectory().resolve(name);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _IoTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

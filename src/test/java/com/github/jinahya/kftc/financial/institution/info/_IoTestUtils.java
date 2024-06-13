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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({"java:S101"})
public final class _IoTestUtils {

    static void write(final File file, final Object obj) throws IOException {
        try (var fos = new FileOutputStream(file);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    static void write(final Path path, final Object obj) throws IOException {
        write(path.toFile(), obj);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    static <T> T read(final InputStream stream) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(stream, "stream is null");
        try (var oos = new ObjectInputStream(stream)) {
            return (T) oos.readObject();
        }
    }

    static <T> T read(final File file) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(file, "file is null");
        try (var fos = new FileInputStream(file)) {
            return read(fos);
        }
    }

    static <T> T read(final Path path) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(path, "path is null");
        return read(path.toFile());
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Path resourceDirectory() throws IOException {
        final var directory = Stream.concat(
                        Stream.of("src", "main", "resources"),
                        Arrays.stream(KftcFinancialInstitutionTestUtils.class.getPackage().getName().split("\\."))
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
                        Arrays.stream(KftcFinancialInstitutionTestUtils.class.getPackage().getName().split("\\."))
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

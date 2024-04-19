package com.github.jinahya.kftc.financial.institution.codes;

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
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@SuppressWarnings({"java:S101"})
final class _IoUtils {

    static void write(final File file, final Object obj) throws IOException {
        try (var fos = new FileOutputStream(file);
             var gzipos = new GZIPOutputStream(fos);
             var oos = new ObjectOutputStream(gzipos)) {
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
        try (var gzipos = new GZIPInputStream(stream);
             var oos = new ObjectInputStream(gzipos)) {
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
    private _IoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

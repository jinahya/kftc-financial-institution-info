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
import java.nio.file.Path;
import java.util.Objects;

@SuppressWarnings({"java:S101"})
final class _IoUtils {

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    static <T> T readObject(final InputStream stream) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(stream, "stream is null");
        try (var oos = new ObjectInputStream(stream)) {
            return (T) oos.readObject();
        }
    }

    static <T> T readObject(final File file) throws IOException, ClassNotFoundException {
        if (!Objects.requireNonNull(file, "file is null").isFile()) {
            throw new IllegalArgumentException("not a normal file: " + file);
        }
        try (var fos = new FileInputStream(file)) {
            return readObject(fos);
        }
    }

    // TODO: remove; unused
    static <T> T readObject(final Path path) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(path, "path is null");
        return readObject(path.toFile());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _IoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

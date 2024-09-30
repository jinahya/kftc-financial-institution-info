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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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

    // -----------------------------------------------------------------------------------------------------------------
    private _IoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

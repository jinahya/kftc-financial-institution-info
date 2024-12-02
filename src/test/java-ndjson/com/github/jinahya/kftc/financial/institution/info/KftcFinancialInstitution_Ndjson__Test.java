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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

abstract class KftcFinancialInstitution_Ndjson__Test {

    // https://cowtowncoder.medium.com/line-delimited-json-with-jackson-69c9e4cb6c00
    static void writeValues(final Iterable<?> values, final File file) throws IOException {
        try (var writer = new ObjectMapper().writer()
                .withRootValueSeparator("\n") // Important! Default value separator is single space
                .writeValues(file)) {
            values.forEach(v -> {
                try {
                    writer.write(v);
                } catch (final IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            });
        }
    }
}

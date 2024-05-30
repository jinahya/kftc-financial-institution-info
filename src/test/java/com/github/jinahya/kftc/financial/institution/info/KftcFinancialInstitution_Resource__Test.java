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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
public abstract class KftcFinancialInstitution_Resource__Test {

    static Path resourceDirectory() throws IOException {
        final var directory = Stream.concat(
                        Stream.of("src", "main", "resources"),
                        Arrays.stream(KftcFinancialInstitution_Resource__Test.class.getPackage().getName().split("\\."))
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
                        Arrays.stream(KftcFinancialInstitution_Resource__Test.class.getPackage().getName().split("\\."))
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
}

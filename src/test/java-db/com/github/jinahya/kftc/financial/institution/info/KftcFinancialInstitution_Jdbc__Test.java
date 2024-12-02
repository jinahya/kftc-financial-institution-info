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

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
abstract class KftcFinancialInstitution_Jdbc__Test {

    static final String NAME = "kftc-financial-institution-info.sqlite3";

    static final Path PATH = Paths.get("db").resolve(NAME);

    static String canonicalPath() throws IOException {
        return PATH.toAbsolutePath().toFile().getCanonicalPath();
    }

    // -----------------------------------------------------------------------------------------------------------------
    static <R> R applyConnection(final Function<? super Connection, ? extends R> function) throws Exception {
        final var path = canonicalPath();
        final var url = "jdbc:sqlite:" + path;
        log.debug("url: {}", url);
        Class.forName("org.sqlite.JDBC");
        try (var connection = DriverManager.getConnection(url)) {
            return function.apply(connection);
        }
    }

    static void acceptConnection(final Consumer<? super Connection> consumer) throws Exception {
        applyConnection(c -> {
            consumer.accept(c);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    static void vacuum() throws Exception {
        acceptConnection(c -> {
            try {
                {
                    final int autoVacuum = c.createStatement().executeUpdate("PRAGMA auto_vacuum = FULL");
                    log.debug("auto_vacuum: {}", autoVacuum);
                }
                {
                    final int vacuum = c.createStatement().executeUpdate("VACUUM");
                    log.debug("vacuum: {}", vacuum);
                }
            } catch (final SQLException sqle) {
                throw new RuntimeException("failed to vacuum", sqle);
            }
        });
    }
}

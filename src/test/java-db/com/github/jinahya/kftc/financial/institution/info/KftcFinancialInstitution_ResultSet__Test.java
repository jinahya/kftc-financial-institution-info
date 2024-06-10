package com.github.jinahya.kftc.financial.institution.info;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
abstract class KftcFinancialInstitution_ResultSet__Test {

    private static final String NAME = "kftc-financial-institution-info.sqlite3";

    static Path path() {
        final Path path = Paths.get("db", NAME).toAbsolutePath();
        assertThat(path).isRegularFile();
        return path;
    }

    static File file() {
        final var file = path().toFile();
        assertThat(file).isFile();
        return file;
    }

    static String url() {
        final var file = file();
        try {
            return "jdbc:sqlite:" + file.toURI().toURL().toExternalForm();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException("failed to get URL from " + file, murle);
        }
    }

    static Connection connect() throws SQLException {
        return DriverManager.getConnection(url());
    }

    static <R> R applyConnection(final Function<? super Connection, ? extends R> function) throws SQLException {
        Objects.requireNonNull(function, "function is null");
        try (var connection = connect()) {
            return function.apply(connection);
        }
    }
}

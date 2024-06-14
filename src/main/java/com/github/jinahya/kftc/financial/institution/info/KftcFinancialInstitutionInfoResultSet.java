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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A class for accessing database for instances of {@link KftcFinancialInstitutionInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @implSpec Instances of this class are unmodifiable and thread-safe.
 * @see KftcFinancialInstitutionInfo
 */
public final class KftcFinancialInstitutionInfoResultSet {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "financial_institution";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CODE = "code";

    public static final String COLUMN_NAME_NAME = "name";

    public static final String COLUMN_NAME_REPRESENTATIVE = "representative";

    public static final String COLUMN_NAME_CATEGORY = "category";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String SQL_SELECT_ALL = "SELECT *"
            + " FROM " + TABLE_NAME
            + " ORDER BY " + COLUMN_NAME_CODE;

    private static final String SQL_SELECT_ONE =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + COLUMN_NAME_CODE + " = ?";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String SQL_SELECT_ALL_BY_CATEGORY_ANE_NAME =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + COLUMN_NAME_CATEGORY + " = ? AND " + COLUMN_NAME_NAME + " = ?";

    private static final String SQL_SELECT_ALL_BY_CATEGORY_ANE_NAME_LIKE =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + COLUMN_NAME_CATEGORY + " = ? AND " + COLUMN_NAME_NAME + " LIKE ?";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String SQL_SELECT_ALL_BY_NAME =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + COLUMN_NAME_NAME + " = ?";

    // https://github.com/felixfbecker/node-sql-template-strings/issues/163#issuecomment-1087007560
    private static final String SQL_SELECT_ALL_BY_NAME_LIKE =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + COLUMN_NAME_NAME + " LIKE ?";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    private static <R> R fetch(final ResultSet results,
                               final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        final var instance = new KftcFinancialInstitutionInfo();
        instance.setCode(results.getString(COLUMN_NAME_CODE));
        instance.setName(results.getString(COLUMN_NAME_NAME));
        instance.setRepresentative(results.getBoolean(COLUMN_NAME_REPRESENTATIVE));
        instance.setCategory(KftcFinancialInstitutionCategory.valueOf(results.getString(COLUMN_NAME_CATEGORY)));
        return mapper.apply(instance);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> List<R> selectAll(final Connection connection,
                                        final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        Objects.requireNonNull(connection, "connection is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (var statement = connection.createStatement();
             var resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
            final var instances = new ArrayList<R>();
            while (resultSet.next()) {
                instances.add(fetch(resultSet, mapper));
            }
            return instances;
        }
    }

    public static List<KftcFinancialInstitutionInfo> selectAll(final Connection connection)
            throws SQLException {
        return selectAll(
                connection,
                UnaryOperator.identity()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> Optional<R> selectOneByCode(
            final Connection connection, final String code,
            final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        Objects.requireNonNull(connection, "connection is null");
        Objects.requireNonNull(code, "code is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (var statement = connection.prepareStatement(SQL_SELECT_ONE)) {
            statement.setString(1, code);
            try (var resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(
                        fetch(resultSet, mapper)
                );
            }
        }
    }

    public static Optional<KftcFinancialInstitutionInfo> selectOneByCode(final Connection connection, final String code)
            throws SQLException {
        return selectOneByCode(
                connection,
                code,
                UnaryOperator.identity()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> List<R> selectAllByCategoryAndName(
            final Connection connection, final KftcFinancialInstitutionCategory category, final String name,
            final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        Objects.requireNonNull(connection, "connection is null");
        Objects.requireNonNull(category, "category is null");
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (var statement = connection.prepareStatement(SQL_SELECT_ALL_BY_CATEGORY_ANE_NAME)) {
            int index = 0;
            statement.setString(++index, category.name());
            statement.setString(++index, name);
            try (var resultSet = statement.executeQuery()) {
                final var list = new ArrayList<R>();
                while (resultSet.next()) {
                    list.add(fetch(resultSet, mapper));
                }
                return list;
            }
        }
    }

    public static List<KftcFinancialInstitutionInfo> selectAllByCategoryAndName(
            final Connection connection, final KftcFinancialInstitutionCategory category, final String name)
            throws SQLException {
        return selectAllByCategoryAndName(
                connection,
                category,
                name,
                UnaryOperator.identity()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> List<R> selectAllByCategoryAndNameLike(
            final Connection connection, final KftcFinancialInstitutionCategory category, final String pattern,
            final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        Objects.requireNonNull(connection, "connection is null");
        Objects.requireNonNull(category, "category is null");
        Objects.requireNonNull(pattern, "pattern is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (var statement = connection.prepareStatement(SQL_SELECT_ALL_BY_CATEGORY_ANE_NAME_LIKE)) {
            var index = 0;
            statement.setString(++index, category.name());
            statement.setString(++index, pattern);
            try (var resultSet = statement.executeQuery()) {
                final var list = new ArrayList<R>();
                while (resultSet.next()) {
                    list.add(fetch(resultSet, mapper));
                }
                return list;
            }
        }
    }

    public static List<KftcFinancialInstitutionInfo> selectAllByCategoryAndNameLike(
            final Connection connection,
            final KftcFinancialInstitutionCategory category,
            final String pattern)
            throws SQLException {
        return selectAllByCategoryAndNameLike(
                connection,
                category,
                pattern,
                UnaryOperator.identity()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> List<R> selectAllByName(
            final Connection connection, final String name,
            final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        Objects.requireNonNull(connection, "connection is null");
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (var statement = connection.prepareStatement(SQL_SELECT_ALL_BY_NAME)) {
            statement.setString(1, name);
            try (var resultSet = statement.executeQuery()) {
                final var list = new ArrayList<R>();
                while (resultSet.next()) {
                    list.add(fetch(resultSet, mapper));
                }
                return list;
            }
        }
    }

    public static List<KftcFinancialInstitutionInfo> selectAllByName(final Connection connection,
                                                                     final String name)
            throws SQLException {
        return selectAllByName(
                connection,
                name,
                UnaryOperator.identity()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> List<R> selectAllByNameLike(
            final Connection connection, final String pattern,
            final Function<? super KftcFinancialInstitutionInfo, ? extends R> mapper)
            throws SQLException {
        Objects.requireNonNull(connection, "connection is null");
        Objects.requireNonNull(pattern, "pattern is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (var statement = connection.prepareStatement(SQL_SELECT_ALL_BY_NAME_LIKE)) {
            statement.setString(1, pattern);
            try (var resultSet = statement.executeQuery()) {
                final var list = new ArrayList<R>();
                while (resultSet.next()) {
                    list.add(fetch(resultSet, mapper));
                }
                return list;
            }
        }
    }

    public static List<KftcFinancialInstitutionInfo> selectAllByNameLike(final Connection connection,
                                                                         final String pattern)
            throws SQLException {
        return selectAllByNameLike(
                connection,
                pattern,
                UnaryOperator.identity()
        );
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private KftcFinancialInstitutionInfoResultSet() {
        throw new AssertionError("instantiation is not allowed");
    }
}

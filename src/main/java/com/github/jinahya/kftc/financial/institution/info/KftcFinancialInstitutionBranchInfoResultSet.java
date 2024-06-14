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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A class for accessing database for instances of {@link KftcFinancialInstitutionInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @implSpec Instances of this class are unmodifiable and thread-safe.
 * @see KftcFinancialInstitutionInfo
 */
public final class KftcFinancialInstitutionBranchInfoResultSet {

    public static final String TABLE_NAME = "financial_institution_branch";

    public static final String COLUMN_NAME_BRANCH_CODE = "branch_code";

    public static final String COLUMN_NAME_BRANCH_NAME = "branch_name";

    public static final String COLUMN_NAME_FINANCIAL_INSTITUTION_NAME = "financial_institution_name";

    public static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";

    public static final String COLUMN_NAME_FAX_NUMBER = "fax_number";

    public static final String COLUMN_NAME_POSTAL_CODE = "postal_code";

    public static final String COLUMN_NAME_ADDRESS = "address";

    public static final String COLUMN_NAME_STATUS = "status";

    public static final String COLUMN_NAME_MANAGING_BRANCH_CODE = "managing_branch_code";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Returns a new instance whose fields are set with fetched data from the current row of specified result set, and
     * returns a new instance.
     *
     * @param resultSet    the result set.
     * @param columnMapper a function for mapping column labels and column names.
     * @param valueMapper  a function for mapping result.
     * @param <R>          result type parameter
     * @return a new instance.
     * @throws SQLException if a database error occurs.
     */
    public static <R> R fetchInstance(
            final ResultSet resultSet,
            final UnaryOperator<String> columnMapper,
            final Function<? super KftcFinancialInstitutionBranchInfo, ? extends R> valueMapper)
            throws SQLException {
        Objects.requireNonNull(resultSet, "resultSet is null");
        Objects.requireNonNull(columnMapper, "columnMapper is null");
        Objects.requireNonNull(valueMapper, "valueMapper is null");
        final var instance = new KftcFinancialInstitutionBranchInfo();
        instance.setBranchCode(resultSet.getString(columnMapper.apply(COLUMN_NAME_BRANCH_CODE)));
        instance.setFinancialInstitutionName(
                resultSet.getString(columnMapper.apply(COLUMN_NAME_FINANCIAL_INSTITUTION_NAME)))
        ;
        instance.setBranchName(resultSet.getString(columnMapper.apply(COLUMN_NAME_BRANCH_NAME)));
        instance.setPhoneNumber(resultSet.getString(columnMapper.apply(COLUMN_NAME_PHONE_NUMBER)));
        instance.setFaxNumber(resultSet.getString(columnMapper.apply(COLUMN_NAME_FAX_NUMBER)));
        instance.setPostalCode(resultSet.getString(columnMapper.apply(COLUMN_NAME_POSTAL_CODE)));
        instance.setAddress(resultSet.getString(columnMapper.apply(COLUMN_NAME_ADDRESS)));
        instance.setStatus(resultSet.getString(columnMapper.apply(COLUMN_NAME_STATUS)));
        instance.setManagingBranchCode(resultSet.getString(columnMapper.apply(COLUMN_NAME_MANAGING_BRANCH_CODE)));
        return valueMapper.apply(instance);
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Returns a list of all instances fetched from specified result set.
     *
     * @param resultSet    the result set.
     * @param columnMapper a function for mapping column labels.
     * @param valueMapper  a function for mapping value.
     * @return a list of all instances.
     * @throws SQLException if a database error occurs.
     */
    public static <R> List<R> fetchAllInstances(
            final ResultSet resultSet,
            final UnaryOperator<String> columnMapper,
            final Function<? super KftcFinancialInstitutionBranchInfo, ? extends R> valueMapper)
            throws SQLException {
        Objects.requireNonNull(resultSet, "resultSet is null");
        final var instances = new ArrayList<R>();
        while (resultSet.next()) {
            instances.add(fetchInstance(resultSet, columnMapper, valueMapper));
        }
        return instances;
    }

    /**
     * Returns a list of all instances fetched from specified result set.
     *
     * @param resultSet the result set.
     * @return a list of all instances.
     * @throws SQLException if a database error occurs.
     */
    public static List<KftcFinancialInstitutionBranchInfo> fetchAllInstances(final ResultSet resultSet)
            throws SQLException {
        return fetchAllInstances(
                resultSet,
                UnaryOperator.identity(),
                UnaryOperator.identity()
        );
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private KftcFinancialInstitutionBranchInfoResultSet() {
        throw new AssertionError("instantiation is not allowed");
    }
}

package com.github.jinahya.kftc.financial.institution.info;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
class KftcFinancialInstitution_ResultSet_BranchInfoSet_Test
        extends KftcFinancialInstitution_ResultSet__Test {

    @Test
    void __() throws SQLException {
        try (var connection = connect();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(
                     """
                             SELECT *
                             FROM %1$s
                             ORDER BY %2$s""".formatted(
                             KftcFinancialInstitutionBranchInfoResultSet.TABLE_NAME,
                             KftcFinancialInstitutionBranchInfoResultSet.COLUMN_NAME_BRANCH_CODE))) {
            final var instances = KftcFinancialInstitutionBranchInfoResultSet.getAllInstances(resultSet);
            instances.forEach(v -> {
//                log.debug("instance: {}", v);
            });
        }
    }

    @Test
    void __2() throws SQLException {
        final var instances = applyConnection(c -> {
            try (var statement = c.createStatement();
                 var resultSet = statement.executeQuery(
                         """
                                 SELECT *
                                 FROM %1$s
                                 ORDER BY %2$s""".formatted(
                                 KftcFinancialInstitutionBranchInfoResultSet.TABLE_NAME,
                                 KftcFinancialInstitutionBranchInfoResultSet.COLUMN_NAME_BRANCH_CODE))) {
                return KftcFinancialInstitutionBranchInfoResultSet.getAllInstances(resultSet);
            } catch (final SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        });
        instances.forEach(v -> {
//                log.debug("instance: {}", v);
        });
    }
}

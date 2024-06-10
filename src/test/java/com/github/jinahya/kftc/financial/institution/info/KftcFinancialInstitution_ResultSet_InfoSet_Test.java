package com.github.jinahya.kftc.financial.institution.info;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
class KftcFinancialInstitution_ResultSet_InfoSet_Test
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
                             KftcFinancialInstitutionInfoResultSet.TABLE_NAME,
                             KftcFinancialInstitutionInfoResultSet.COLUMN_NAME_CODE))) {
            final var instances = KftcFinancialInstitutionInfoResultSet.getAllInstances(resultSet);
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
                                 KftcFinancialInstitutionInfoResultSet.TABLE_NAME,
                                 KftcFinancialInstitutionInfoResultSet.COLUMN_NAME_CODE))) {
                return KftcFinancialInstitutionInfoResultSet.getAllInstances(resultSet);
            } catch (final SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        });
        instances.forEach(v -> {
//                log.debug("instance: {}", v);
        });
    }
}

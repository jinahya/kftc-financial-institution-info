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
import org.junit.jupiter.api.Test;

import java.sql.JDBCType;
import java.sql.SQLException;

@Slf4j
class KftcFinancialInstitution_Jdbc_InfoSet_Test
        extends KftcFinancialInstitution_Jdbc__Test {

    @Test
    void __() throws Exception {
        final var sql = """
                SELECT * FROM %s
                ORDER BY category ASC"""
                .formatted(
                        KftcFinancialInstitution_Resource_Jdbc_InfoSet_Test.TABLE_NAME
                );
        KftcFinancialInstitution_Resource_Jdbc__Test.acceptConnection(c -> {
            try (var statement = c.createStatement()) {
                try (var results = statement.executeQuery(sql)) {
                    final var metadata = results.getMetaData();
                    final var count = metadata.getColumnCount();
                    for (int i = 1; i <= count; i++) {
                        final var label = metadata.getColumnLabel(i);
                        final var type = metadata.getColumnType(i);
                        log.debug("label: {}, type: {}", label, JDBCType.valueOf(type));
                    }
                    while (results.next()) {
                        final var code = results.getString("code");
                        final var name = results.getString("name");
                        final var representative = results.getInt("representative");
                        final var category = KftcFinancialInstitutionCategory.valueOf(results.getString("category"));
                        log.debug("{}, {}, {}, {}", code, name, representative, category);
                    }
                }
            } catch (final SQLException sqle) {
                throw new RuntimeException(sqle);
            }
        });
    }
}

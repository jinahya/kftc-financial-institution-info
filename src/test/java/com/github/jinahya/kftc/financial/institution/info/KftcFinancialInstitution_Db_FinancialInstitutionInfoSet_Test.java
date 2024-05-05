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

import java.sql.SQLException;

@Slf4j
class KftcFinancialInstitution_Db_FinancialInstitutionInfoSet_Test {

    private static final String TABLE_NAME = "financial_institution";

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() throws Exception {
        KftcFinancialInstitution_Db__Test.acceptConnection(c -> {
            try (var statement = c.createStatement()) {
                final int deleted = statement.executeUpdate("DELETE FROM " + TABLE_NAME);
                log.debug("deleted: {}", deleted);
            } catch (final SQLException sqle) {
                throw new RuntimeException("failed to delete from " + TABLE_NAME, sqle);
            }
            try (var statement = c.prepareStatement(
                    """
                            INSERT INTO %1$s (
                              code,
                              name,
                              representative,
                              category
                            ) VALUES(?, ?, ?, ?)""".formatted(TABLE_NAME))) {
                for (var info :
                        KftcFinancialInstitutionInfoSet.newInstance().getList()) {
                    statement.clearParameters();
                    int index = 0;
                    statement.setString(++index, info.getCode());
                    statement.setString(++index, info.getName());
                    statement.setBoolean(++index, info.isRepresentative());
                    statement.setString(++index, info.getCategory().name());
                    final var inserted = statement.executeUpdate();
                    assert inserted == 1;
                }
            } catch (final SQLException sqle) {
                throw new RuntimeException("failed to insert", sqle);
            }
        });
    }
}

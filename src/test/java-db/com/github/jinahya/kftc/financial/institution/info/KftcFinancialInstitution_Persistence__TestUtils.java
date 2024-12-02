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

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.resource.jdbc.spi.JdbcSessionOwner;

import java.sql.SQLException;

@Slf4j
final class KftcFinancialInstitution_Persistence__TestUtils {

    // -----------------------------------------------------------------------------------------------------------------
    static int vacuum1(final EntityManager em) throws SQLException {
        final var session = em.unwrap(Session.class);
        assert session instanceof JdbcSessionOwner;
        final var access = ((JdbcSessionOwner) session).getJdbcConnectionAccess();
        final var connection = access.obtainConnection();
        try (var statement = connection.prepareStatement("VACUUM")) {
            return statement.executeUpdate();
        }
    }

    static void vacuum2(final EntityManager em) {
        em.unwrap(Session.class).doWork(c -> {
            try (var statement = c.prepareStatement("VACUUM")) {
                try {
                    final var result = statement.executeUpdate();
//                    assert result == 0 : "vacuum.result: " + result;
                } catch (final SQLException sqle) {
                    throw new RuntimeException(sqle);
                }
            }
        });
    }

    private KftcFinancialInstitution_Persistence__TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

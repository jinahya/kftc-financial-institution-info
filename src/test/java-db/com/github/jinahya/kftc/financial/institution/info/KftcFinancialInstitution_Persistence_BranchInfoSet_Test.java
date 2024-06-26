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

//@Tag("db")
@Slf4j
class KftcFinancialInstitution_Persistence_BranchInfoSet_Test
        extends KftcFinancialInstitution_Persistence__Test {

    @Test
    void __() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        applyEntityManager(em -> {
            final var transaction = em.getTransaction();
            try {
                transaction.begin();
//                instance.getList().forEach(em::persist);
                transaction.commit();
            } catch (final Exception e) {
                log.error("failed to update", e);
                transaction.rollback();
            }
            return null;
        });
    }
}

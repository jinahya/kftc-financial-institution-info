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

import java.io.IOException;
import java.util.Comparator;

@Slf4j
class KftcFinancialInstitution_Resource_Ndjson_BranchInfoSet_Test
        extends KftcFinancialInstitution_Resource_Ndjson__Test {

    @Test
    void __() throws IOException {
        final var set = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var list = set.getList().stream()
                .sorted(Comparator.comparing(KftcFinancialInstitutionBranchInfo::getBranchCode))
                .toList();
        final var path = _IoTestUtils.buildOutputFile("codefilex.ndjson");
        writeValues(list, path.toFile());
    }
}
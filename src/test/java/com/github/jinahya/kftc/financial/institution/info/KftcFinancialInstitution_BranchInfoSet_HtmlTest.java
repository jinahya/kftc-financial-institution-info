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

import htmlflow.HtmlFlow;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
class KftcFinancialInstitution_BranchInfoSet_HtmlTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() throws IOException {
        final var instance = KftcFinancialInstitutionBranchInfoSet.getInstance1();
        final var path = _IoTestUtils.buildOutputFile("branch_info_set.html");
        try (var stream = new FileOutputStream(path.toFile());
             var writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8)) {
            HtmlFlow.doc(writer)
                    .html()

                    .head()
                    .title().text("KFTC Financial Institution Branch Info").__()
                    .__()

                    .body()
                    .__()

                    .__() // </html>
            ;
        }
    }
}

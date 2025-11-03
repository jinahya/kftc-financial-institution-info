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
import org.xmlet.htmlapifaster.EnumRelType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.stream.IntStream;

@Slf4j
class KftcFinancialInstitution_InfoSet_HtmlTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.getInstance1();
        final var path = _IoTestUtils.buildOutputFile("info_set.html");
        try (var stream = new FileOutputStream(path.toFile());
             var writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8)) {
            final var flow = HtmlFlow.doc(writer)
                    .html()
                    .attrLang("ko")
                    .head()
                    .title().text("KFTC Financial Institution Branch Info").__()

                    .link()
                    .attrRel(EnumRelType.STYLESHEET)
                    .attrHref("https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css")
                    .__() // </link>

                    .meta().attrName("viewport").attrContent("width=device-width, initial-scale=1").__()

                    .__() // </head>

                    .body()

//                    .table().attrClass("pure-table pure-table-striped pure-u-1-1")
                    .table().attrClass("pure-table")
                    .thead()
                    .tr()
                    .td().text("#").__()
                    .td().text("Category").__()
                    .td().text("Code").__()
                    .td().text("Name").__()
                    .td().text("Representative").__()
                    .__() // </tr>
                    .__() // </thead>
                    .tbody();

            final var list = instance.list().stream()
                    .sorted(Comparator.<KftcFinancialInstitutionInfo, String>comparing(e -> e.getCategory().name())
                                    .thenComparing(KftcFinancialInstitutionInfo::getCode))
                    .toList();
            IntStream.rangeClosed(1, list.size()).forEach(i -> {
                final var e = list.get(i - 1);
                flow.tr()
                        .td().text(Integer.toString(i)).__()
                        .td().text(e.getCategory().name()).__()
                        .td().text(e.getCode()).__()
                        .td().text(e.getName()).__()
                        .td().text(e.isRepresentative()).__()
                        .__() // </tr>
                ;
            });

            flow.__()
                    .__() // </tbody>
                    .__() // </table>
                    .__() // </html>
            ;
        }
    }
}

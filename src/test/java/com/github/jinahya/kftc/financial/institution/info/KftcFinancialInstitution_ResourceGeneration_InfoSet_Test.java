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

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import jakarta.json.bind.JsonbBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitution_ResourceGeneration_InfoSet_Test
        extends KftcFinancialInstitution_ResourceGeneration__Test {

    private static final Pattern pattern = Pattern.compile(
//            "(\\d{3})\\s([\\p{L}\\(\\)\\s]+)?\\s?(\\d{3})?\\s?([\\p{L}\\(\\)\\s]+)?");
            "(\\d{3})\\s([\\p{L}()\\s]+)?\\s?(\\d{3})?\\s?([\\p{L}()\\s]+)?");

    private static final Map<String, Integer> MAP = Map.of(
            "은행", 80,
            "금융투자회사", 48,
            "캐피탈사", 14,
            "카드사", 15,
            "보험사", 35,
            "기타", 5,
            "총기관코드수", 197,
            "대표코드수", 150
    );

    @Test
    void __() throws Exception {
        final var file = new File(getClass().getResource("/bankinfo.hwp.pdf").toURI());
        assert file.isFile();
        final boolean[] representatives = new boolean[]{false};
        final KftcFinancialInstitutionCategory[] categories = new KftcFinancialInstitutionCategory[]{null};
        final Map<String, KftcFinancialInstitutionInfo> map = new TreeMap<>();
        final var reader = new PdfReader(file.toURI().toURL());
        try {
            final var numberOfPages = reader.getNumberOfPages();
            for (var i = 1; i <= numberOfPages; i++) {
                final var textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                try (var lines = textFromPage.lines()) {
                    lines.forEach(l -> {
                        try {
                            final var category = KftcFinancialInstitutionCategory.valueOfDelimiter(l);
                            representatives[0] = false;
                            categories[0] = category;
                        } catch (final IllegalArgumentException iae) {
                        }
                        if (Objects.equals(_TestConstants.DELIMITER_REPR, l)) {
                            log.debug("................... 대표코드!!!");
                            representatives[0] = true;
                        }
                        final var matcher = pattern.matcher(l);
                        if (matcher.matches()) {
                            log.debug("{} / {} / {} / {}", matcher.group(1), matcher.group(2), matcher.group(3),
                                      matcher.group(4));
                            if (matcher.group(1) != null && matcher.group(2) != null) {
                                final var code = matcher.group(1);
                                assertThat(code).isNotBlank();
                                final var name = matcher.group(2);
                                assertThat(name).isNotBlank();
                                log.debug("\t\tcode: {}, name: {}", code, name);
                                final var value = new KftcFinancialInstitutionInfo();
                                value.setCategory(categories[0]);
                                value.setCode(code.strip());
                                value.setName(name.strip());
                                value.setRepresentative(representatives[0]);
                                map.compute(code, (k, v) -> {
                                    if (v != null) {
                                        assertThat(v.isRepresentative())
                                                .as("representative of previous %1$s", v)
                                                .isTrue();
                                        v.setCategory(value.getCategory());
                                        return v;
                                    } else {
                                        return value;
                                    }
                                });
                            }
//                        assert prev1 == null : "prev1 for " + key1;
                            if (matcher.group(3) != null && matcher.group(4) != null) {
                                final var code = matcher.group(3);
                                assertThat(code).isNotBlank();
                                final var name = matcher.group(4);
                                assertThat(name).isNotBlank();
                                log.debug("\t\tcode: {}, name: {}", code, name);
                                final var value = new KftcFinancialInstitutionInfo();
                                value.setCategory(categories[0]);
                                value.setCode(code.strip());
                                value.setName(name.strip());
                                value.setRepresentative(representatives[0]);
                                map.compute(code, (k, v) -> {
                                    if (v != null) {
                                        assertThat(v.isRepresentative())
                                                .as("representative of previous %1$s", v)
                                                .isTrue();
                                        v.setCategory(value.getCategory());
                                        return v;
                                    } else {
                                        return value;
                                    }
                                });
                            }
//                        assert prev2 == null : "prev2 for " + key1;
                        }
                    });
                }
            }
        } finally {
            reader.close();
        }
        assertThat(map.keySet()).doesNotContainNull();
        assertThat(map.values()).allSatisfy(v -> {
            assertThat(v.getCode()).isNotBlank();
            assertThat(v.getName()).isNotBlank();
        });
        assertThat(
                map.values().stream()
                        .filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.BANK)
                        .count()
        ).isEqualTo(Long.valueOf(MAP.get("은행")));
        assertThat(
                map.values().stream()
                        .filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.FIIN)
                        .count()
        ).isEqualTo(Long.valueOf(MAP.get("금융투자회사")));
        assertThat(
                map.values().stream()
                        .filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.CAPI)
                        .count()
        ).isEqualTo(Long.valueOf(MAP.get("캐피탈사")));
        assertThat(
                map.values().stream()
                        .filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.CARD)
                        .count()
        )
                .as("count of %1$s", KftcFinancialInstitutionCategory.CARD)
                .isEqualTo(Long.valueOf(MAP.get("카드사")));
        assertThat(
                map.values().stream()
                        .filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.INSU)
                        .count()
        ).isEqualTo(Long.valueOf(MAP.get("보험사")));
        assertThat(
                map.values().stream()
                        .filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.OTHE)
                        .count()
        ).isEqualTo(Long.valueOf(MAP.get("기타")));
        assertThat(
                map.values().stream()
                        .filter(KftcFinancialInstitutionInfo::isRepresentative)
                        .count()
        ).isEqualTo(Long.valueOf(MAP.get("대표코드수")));
        assertThat(map).hasSize(MAP.get("총기관코드수"));
        // -------------------------------------------------------------------------------------------------------------
        final var array = map.values().stream()
                .sorted(Comparator.comparing(KftcFinancialInstitutionInfo::getCode))
                .toArray();
        // -------------------------------------------------------------------------------------------------------------
        {
            final var path = _IoTestUtils.resourceFile(KftcFinancialInstitutionInfoSet.RESOURCE_NAME);
            _IoTestUtils.writeObject(path, array);
        }
        // -------------------------------------------------------------------------------------------------------------
        {
            final var path = _IoTestUtils.buildOutputFile("bankinfo.json");
            try (var stream = new FileOutputStream(path.toFile());
                 var writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
                 final var jsonb = JsonbBuilder.create()) {
                jsonb.toJson(array, writer);
            }
        }
    }
}

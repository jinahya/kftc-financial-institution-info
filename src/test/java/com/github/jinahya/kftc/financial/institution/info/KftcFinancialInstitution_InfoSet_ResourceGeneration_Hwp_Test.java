package com.github.jinahya.kftc.financial.institution.info;

/*-
 * #%L
 * kftc-financial-institution-info
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import jakarta.json.bind.JsonbBuilder;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.control.Control;
import kr.dogfoot.hwplib.object.bodytext.control.ControlTable;
import kr.dogfoot.hwplib.object.bodytext.control.table.Cell;
import kr.dogfoot.hwplib.object.bodytext.control.table.Row;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.bodytext.paragraph.ParagraphList;
import kr.dogfoot.hwplib.reader.HWPReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Generates {@value KftcFinancialInstitutionInfoSet#RESOURCE_NAME} directly from {@code bankinfo.hwp} using
 * <a href="https://github.com/neolord0/hwplib">hwplib</a>, with no intermediate {@code bankinfo.hwp.pdf} print step.
 *
 * <p>The parsed data is cross-checked against the document's own "구분 / 개수" summary table, so the check is not a
 * hard-coded snapshot: it adjusts to KFTC data updates automatically while still catching a mis-parse (truncated
 * read, layout change, broken download), where the rows would no longer add up to the figures the document declares.
 *
 * @see KftcFinancialInstitution_InfoSet_ResourceGeneration_Test the original PDF-based generator
 */
@Slf4j
class KftcFinancialInstitution_InfoSet_ResourceGeneration_Hwp_Test
        extends _KftcFinancialInstitution_ResourceGenerationTest {

    private static final Pattern CODE = Pattern.compile("\\d{3}");

    private static final Pattern NUMBER = Pattern.compile("\\d+");

    // labels in the document's own summary table ("구분 / 개수"), whitespace-stripped, mapped to a category
    private static final Map<String, KftcFinancialInstitutionCategory> SUMMARY = Map.of(
            "은행", KftcFinancialInstitutionCategory.BANK,
            "금융투자회사", KftcFinancialInstitutionCategory.FIIN,
            "캐피탈사", KftcFinancialInstitutionCategory.CAPI,
            "카드사", KftcFinancialInstitutionCategory.CARD,
            "보험사", KftcFinancialInstitutionCategory.INSU,
            "기타", KftcFinancialInstitutionCategory.OTHE
    );

    private static final String SUMMARY_TOTAL = "총기관코드수";

    private static final String SUMMARY_REPRESENTATIVE = "대표코드수";

    private static String paragraphText(final Paragraph paragraph) throws Exception {
        if (paragraph.getText() == null) {
            return "";
        }
        final var string = paragraph.getText().getNormalString(0);
        return string == null ? "" : string;
    }

    private static String cellText(final Cell cell) throws Exception {
        final var builder = new StringBuilder();
        final ParagraphList paragraphs = cell.getParagraphList();
        if (paragraphs != null) {
            for (final var paragraph : paragraphs.getParagraphs()) {
                builder.append(paragraphText(paragraph));
            }
        }
        return builder.toString().replace("\n", " ").strip();
    }

    static Map<String, KftcFinancialInstitutionInfo> parse() throws Exception {
        final var file = new File(
                KftcFinancialInstitution_InfoSet_ResourceGeneration_Hwp_Test.class.getResource("/bankinfo.hwp").toURI()
        );
        assert file.isFile();
        final var hwp = HWPReader.fromFile(file.getPath());

        final var categories = new KftcFinancialInstitutionCategory[]{null};
        final var representatives = new boolean[]{false};
        final var map = new TreeMap<String, KftcFinancialInstitutionInfo>();

        // expected counts read straight from the document's "구분 / 개수" summary table
        final var expectedByCategory = new EnumMap<KftcFinancialInstitutionCategory, Integer>(
                KftcFinancialInstitutionCategory.class);
        final var expectedTotal = new Integer[]{null};
        final var expectedRepresentative = new Integer[]{null};

        for (final Section section : hwp.getBodyText().getSectionList()) {
            for (final var paragraph : section.getParagraphs()) {
                final var heading = paragraphText(paragraph).strip();
                // section headings carry the category / representative context
                try {
                    categories[0] = KftcFinancialInstitutionCategory.valueOfDelimiter(heading);
                    representatives[0] = false;
                } catch (final IllegalArgumentException iae) {
                    // not a category heading
                }
                if (_TestConstants.DELIMITER_REPR.equals(heading)) {
                    representatives[0] = true;
                }
                if (paragraph.getControlList() == null) {
                    continue;
                }
                for (final Control control : paragraph.getControlList()) {
                    if (!(control instanceof ControlTable table)) {
                        continue;
                    }
                    for (final Row row : table.getRowList()) {
                        final List<Cell> cells = row.getCellList();
                        if (cells.size() >= 2) {
                            // summary rows look like [<label>][<count>]
                            readSummary(cells, expectedByCategory, expectedTotal, expectedRepresentative);
                        }
                        // each data row holds two [code][name] pairs side by side
                        for (var i = 0; i + 1 < cells.size(); i += 2) {
                            put(map, categories[0], representatives[0],
                                cellText(cells.get(i)), cellText(cells.get(i + 1)));
                        }
                    }
                }
            }
        }

        // the document's summary table must have been found, in full
        assertThat(expectedByCategory.keySet())
                .as("categories declared in the summary table")
                .containsExactlyInAnyOrder(KftcFinancialInstitutionCategory.values());
        assertThat(expectedTotal[0]).as("총 기관코드 수 from the summary table").isNotNull();
        assertThat(expectedRepresentative[0]).as("대표코드 수 from the summary table").isNotNull();

        // the parsed rows must add up to the figures the document declares about itself
        for (final var category : KftcFinancialInstitutionCategory.values()) {
            assertThat(map.values().stream().filter(v -> v.getCategory() == category).count())
                    .as("count of %1$s vs the summary table", category)
                    .isEqualTo(Long.valueOf(expectedByCategory.get(category)));
        }
        assertThat(map.values().stream().filter(KftcFinancialInstitutionInfo::isRepresentative).count())
                .as("대표코드 수 vs the summary table")
                .isEqualTo(Long.valueOf(expectedRepresentative[0]));
        assertThat(map)
                .as("총 기관코드 수 vs the summary table")
                .hasSize(expectedTotal[0]);
        return map;
    }

    private static void readSummary(final List<Cell> cells,
                                    final Map<KftcFinancialInstitutionCategory, Integer> expectedByCategory,
                                    final Integer[] expectedTotal, final Integer[] expectedRepresentative)
            throws Exception {
        final var label = cellText(cells.get(0)).replaceAll("\\s", "");
        final var count = cellText(cells.get(1)).replaceAll("\\s", "");
        if (!NUMBER.matcher(count).matches()) {
            return;
        }
        final var value = Integer.valueOf(count);
        final var category = SUMMARY.get(label);
        if (category != null) {
            expectedByCategory.put(category, value);
        } else if (SUMMARY_TOTAL.equals(label)) {
            expectedTotal[0] = value;
        } else if (SUMMARY_REPRESENTATIVE.equals(label)) {
            expectedRepresentative[0] = value;
        }
    }

    private static void put(final Map<String, KftcFinancialInstitutionInfo> map,
                            final KftcFinancialInstitutionCategory category, final boolean representative,
                            final String code, final String name) {
        if (code == null || name == null || !CODE.matcher(code).matches() || name.isBlank()) {
            return;
        }
        final var value = new KftcFinancialInstitutionInfo();
        value.setCategory(category);
        value.setCode(code.strip());
        value.setName(name.strip());
        value.setRepresentative(representative);
        map.compute(code, (k, v) -> {
            if (v == null) {
                return value;
            }
            // a code can appear in the 대표코드 table and again in its category table; keep both facts
            if (value.getCategory() != null) {
                v.setCategory(value.getCategory());
            }
            if (value.isRepresentative()) {
                v.setRepresentative(true);
            }
            return v;
        });
    }

    @Test
    void __() throws Exception {
        final var map = parse();
        final var array = map.values().stream()
                .sorted(Comparator.comparing(KftcFinancialInstitutionInfo::getCode))
                .toArray();
        {
            final var path = _IoTestUtils.resourceFile(KftcFinancialInstitutionInfoSet.RESOURCE_NAME);
            _IoTestUtils.writeObject(path, array);
        }
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

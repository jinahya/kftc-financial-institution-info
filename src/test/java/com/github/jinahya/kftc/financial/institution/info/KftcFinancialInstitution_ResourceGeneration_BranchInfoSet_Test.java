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

import jakarta.json.bind.JsonbBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class KftcFinancialInstitution_ResourceGeneration_BranchInfoSet_Test
        extends KftcFinancialInstitution_ResourceGeneration__Test {

    private static final int INDEX_BRANCH_CODE = 0;

    private static final int INDEX_FINANCIAL_INSTITUTION_NAME = 1;

    private static final int INDEX_BRANCH_NAME = 2;

    private static final int INDEX_PHONE_NUMBER = 3;

    private static final int INDEX_FAX_NUMBER = 4;

    private static final int INDEX_POSTAL_CODE = 5;

    private static final int INDEX_ADDRESS = 6;

    private static final int INDEX_STATUS = 7;

    private static final int INDEX_MANAGING_BRANCH_CODE = 8;

    @Test
    void __() throws Exception {
        final Map<String, KftcFinancialInstitutionBranchInfo> map;
        {
            final var name = "/codefilex.text";
            final var resource = getClass().getResource(name);
            assertThat(resource)
                    .as("resource for '%1$s'", name)
                    .isNotNull();
            final var path = Paths.get(resource.toURI());
            final var charset = Charset.forName("EUC-KR");
            try (var lines = Files.lines(path, charset)) {
                log.debug("lines: {}", lines.count());
            }
            try (var lines = Files.lines(path, charset)) {
                map = lines.map(l -> {
                            final var tokens = l.split("\\|");
                            assertThat(tokens).hasSize(9);
                            final var info = new KftcFinancialInstitutionBranchInfo();
                            info.setBranchCode(tokens[INDEX_BRANCH_CODE]);
                            info.setFinancialInstitutionName(tokens[INDEX_FINANCIAL_INSTITUTION_NAME]);
                            info.setBranchName(tokens[INDEX_BRANCH_NAME]);
                            info.setPhoneNumber(tokens[INDEX_PHONE_NUMBER]);
                            info.setFaxNumber(tokens[INDEX_FAX_NUMBER]);
                            info.setPostalCode(tokens[INDEX_POSTAL_CODE]);
                            info.setAddress(tokens[INDEX_ADDRESS]);
                            info.setStatus(tokens[INDEX_STATUS]);
                            info.setManagingBranchCode(tokens[INDEX_MANAGING_BRANCH_CODE]);
                            assertThatCode(() -> {
                                final var status =
                                        KftcFinancialInstitutionBranchInfo.Status.valueOfRawValue(info.getStatus());
                            }).doesNotThrowAnyException();
                            assertThat(info.getManagingBranchCode()).satisfiesAnyOf(
                                    a -> assertThat(a).isNull(),
                                    a -> assertThat(a).isNotEmpty()
                            );
                            return info;
                        })
                        .collect(Collectors.toMap(KftcFinancialInstitutionBranchInfo::getBranchCode,
                                                  Function.identity()));
                log.debug("names: {}",
                          map.values().stream()
                                  .map(KftcFinancialInstitutionBranchInfo::getFinancialInstitutionName)
                                  .distinct()
                                  .toList()
                );
                log.debug("statuses: {}",
                          map.values().stream()
                                  .map(KftcFinancialInstitutionBranchInfo::getStatus)
                                  .distinct()
                                  .toList()
                );
                log.debug("branchCode.length.statistics: {}",
                          map.values().stream()
                                  .map(KftcFinancialInstitutionBranchInfo::getBranchCode)
                                  .mapToLong(String::length)
                                  .distinct()
                                  .summaryStatistics()
                );
            }
        }
        // -------------------------------------------------------------------------------------------------------------
        final var array = map.values().stream()
                .sorted(Comparator.comparing(KftcFinancialInstitutionBranchInfo::getBranchCode))
                .toArray();
        // -------------------------------------------------------------------------------------------------------------
        {
            final var path = _IoTestUtils.resourceFile(KftcFinancialInstitutionBranchInfoSet.RESOURCE_NAME);
            _IoTestUtils.writeObject(path, array);
        }
        {
            final var path = _IoTestUtils.buildOutputFile("codefilex.json");
            try (var stream = new FileOutputStream(path.toFile());
                 var writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
                 final var jsonb = JsonbBuilder.create()) {
                jsonb.toJson(array, writer);
            }
        }
    }

    @Test
    void xlsx__() throws Exception {
        final var name = "/codefilex.text.xlsx";
        final var resource = getClass().getResource(name);
        assertThat(resource)
                .as("resource for '%1$s'", name)
                .isNotNull();
        try (final var stream = resource.openStream();
             final var workbook = new XSSFWorkbook(stream)) {
            final var formatter = new DataFormatter();
            for (final var row : workbook.getSheetAt(0)) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                final var info = new KftcFinancialInstitutionBranchInfo();
                info.setBranchCode(formatter.formatCellValue(row.getCell(INDEX_BRANCH_CODE)));
                info.setFinancialInstitutionName(
                        formatter.formatCellValue(row.getCell(INDEX_FINANCIAL_INSTITUTION_NAME))
                );
                info.setBranchName(formatter.formatCellValue(row.getCell(INDEX_BRANCH_NAME)));
                info.setPhoneNumber(formatter.formatCellValue(row.getCell(INDEX_PHONE_NUMBER)));
                info.setFaxNumber(formatter.formatCellValue(row.getCell(INDEX_FAX_NUMBER)));
                info.setPostalCode(formatter.formatCellValue(row.getCell(INDEX_POSTAL_CODE)));
                info.setAddress(formatter.formatCellValue(row.getCell(INDEX_ADDRESS)));
                info.setStatus(formatter.formatCellValue(row.getCell(INDEX_STATUS)));
                info.setManagingBranchCode(formatter.formatCellValue(row.getCell(INDEX_MANAGING_BRANCH_CODE)));
            }
        }
    }
}

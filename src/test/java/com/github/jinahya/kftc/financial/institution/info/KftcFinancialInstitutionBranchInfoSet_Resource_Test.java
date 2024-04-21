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
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
class KftcFinancialInstitutionBranchInfoSet_Resource_Test {

    @Test
    void __() throws IOException, URISyntaxException {
        final Map<String, KftcFinancialInstitutionBranchInfo> map;
        {
            final var resource = getClass().getResource("/codefilex.text");
            assertThat(resource).isNotNull();
            final var path = Paths.get(resource.toURI());
            final var charset = Charset.forName("EUC-KR");
            try (var lines = Files.lines(path, charset)) {
                log.debug("lines: {}", lines.count());
            }
            try (var lines = Files.lines(path, charset)) {
                map = lines.map(l -> {
//                log.debug("line: {}", l);
                            final var tokens = l.split("\\|");
                            assertThat(tokens).hasSize(9);
                            for (final var token : tokens) {
//                    log.debug("\ttoken: {}", token);
                            }
                            final var info = new KftcFinancialInstitutionBranchInfo();
                            info.setBranchCode(tokens[0]);
                            info.setFinancialInstitutionName(tokens[1]);
                            info.setBranchName(tokens[2]);
                            info.setPhoneNumber(tokens[3]);
                            info.setFaxNumber(tokens[4]);
                            info.setPostalCode(tokens[5]);
                            info.setAddress(tokens[6]);
                            info.setStatus(tokens[7]);
                            info.setManagingBranchCode(tokens[8]);
//                            log.debug("info: {}", info);
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
                          map.values().stream().map(KftcFinancialInstitutionBranchInfo::getFinancialInstitutionName)
                                  .distinct().toList());
                log.debug("statuses: {}",
                          map.values().stream().map(KftcFinancialInstitutionBranchInfo::getStatus).distinct().toList());
                log.debug("branchCode.length.statistics: {}",
                          map.values().stream()
                                  .map(KftcFinancialInstitutionBranchInfo::getBranchCode)
                                  .mapToLong(String::length)
                                  .distinct()
                                  .summaryStatistics()
                );
            }
        }
        final var directory = Stream.concat(
                        Stream.of("src", "main", "resources"),
                        Arrays.stream(getClass().getPackage().getName().split("\\."))
                )
                .reduce(Path.of("."), Path::resolve, (p1, p2) -> p1)
                .toAbsolutePath()
                .normalize();
        Files.createDirectories(directory);
        final var path = directory.resolve(KftcFinancialInstitutionBranchInfoSet.RESOURCE_NAME);
        final var list = map.values().stream()
                .sorted(Comparator.comparing(KftcFinancialInstitutionBranchInfo::getBranchCode))
                .toList();
        final var infoSet = new KftcFinancialInstitutionBranchInfoSet(list);
        _IoUtils.write(path, infoSet);
    }
}

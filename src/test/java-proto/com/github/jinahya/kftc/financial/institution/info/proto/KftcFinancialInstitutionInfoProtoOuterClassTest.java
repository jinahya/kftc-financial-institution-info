package com.github.jinahya.kftc.financial.institution.info.proto;

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

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionInfoSet;
import com.github.jinahya.kftc.financial.institution.info._IoTestUtils;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProtoRepeated;
import com.google.protobuf.TextFormat;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionInfoProtoOuterClassTest {

    @Test
    void __binpb() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final String name = "bankinfo.binpb";
        // write
        {
            final var mapped = instance.list().stream()
                    .map(KftcFinancialInstitutionInfoProtoOuterClassUtils::from)
                    .toList();
            final var built = KftcFinancialInstitutionInfoProtoRepeated.newBuilder()
                    .addAllObjects(mapped)
                    .build();
            log.debug("built.serializedSize: {}", built.getSerializedSize());
            {
                final var path = _IoTestUtils.buildOutputFile(name);
                try (var stream = new FileOutputStream(path.toFile())) {
                    built.writeTo(stream);
                    stream.flush();
                }
            }
        }
        // read
        {
            final var path = _IoTestUtils.buildOutputFile(name);
            try (var stream = new FileInputStream(path.toFile())) {
                final var parsed = KftcFinancialInstitutionInfoProtoRepeated.parseFrom(stream);
                final var list = parsed.getObjectsList().stream().map(v -> {
                    try {
                        return KftcFinancialInstitutionInfoProtoOuterClassUtils.from(v);
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
                assertThat(list).isEqualTo(instance.list());
            }
        }
    }

    @Test
    void __txtpb() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final String name = "bankinfo.txtpb";
        // write
        {
            final var mapped = instance.list().stream()
                    .map(KftcFinancialInstitutionInfoProtoOuterClassUtils::from)
                    .toList();
            final var built = KftcFinancialInstitutionInfoProtoRepeated.newBuilder()
                    .addAllObjects(mapped)
                    .build();
            log.debug("built.serializedSize: {}", built.getSerializedSize());
            {
                final var path = _IoTestUtils.buildOutputFile(name);
                try (var writer = new OutputStreamWriter(new FileOutputStream(path.toFile()))) {
                    TextFormat.printer().print(built, writer);
                    writer.flush();
                }
            }
        }
        // read
        {
            final var path = _IoTestUtils.buildOutputFile(name);
            final var string = Files.readString(path);
            final var parsed = TextFormat.parse(string, KftcFinancialInstitutionInfoProtoRepeated.class);
            final var list = parsed.getObjectsList().stream().map(v -> {
                try {
                    return KftcFinancialInstitutionInfoProtoOuterClassUtils.from(v);
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }).toList();
            assertThat(list).isEqualTo(instance.list());
        }
    }

    @Test
    void __jsonpb() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final String name = "bankinfo.jsonpb";
        // write
        {
            final var mapped = instance.list().stream()
                    .map(KftcFinancialInstitutionInfoProtoOuterClassUtils::from)
                    .toList();
            final var built = KftcFinancialInstitutionInfoProtoRepeated.newBuilder()
                    .addAllObjects(mapped)
                    .build();
            log.debug("built.serializedSize: {}", built.getSerializedSize());
            {
                final var path = _IoTestUtils.buildOutputFile(name);
                try (var writer = new OutputStreamWriter(new FileOutputStream(path.toFile()))) {
                    JsonFormat.printer().appendTo(built, writer);
                    writer.flush();
                }
            }
        }
        // read
        {
            final var path = _IoTestUtils.buildOutputFile(name);
            final var json = Files.readString(path);
            final var builder = KftcFinancialInstitutionInfoProtoRepeated.newBuilder();
            JsonFormat.parser().merge(json, builder);
            final var built = builder.build();
            final var list = built.getObjectsList().stream().map(v -> {
                try {
                    return KftcFinancialInstitutionInfoProtoOuterClassUtils.from(v);
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }).toList();
            assertThat(list).isEqualTo(instance.list());
        }
    }
}

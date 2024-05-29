package com.github.jinahya.kftc.financial.institution.info.proto;

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionInfoSet;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProtoRepeated;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitution_Resource__Test.buildOutputFile;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionInfoProtoOuterClassTest {

    @Test
    void __() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final String name = "bankinfo.binpb";
        // write
        {
            final var mapped = instance.getList().stream()
                    .map(KftcFinancialInstitutionInfoProtoOuterClassUtils::from)
                    .toList();
            final var built = KftcFinancialInstitutionInfoProtoRepeated.newBuilder()
                    .addAllObjects(mapped)
                    .build();
            log.debug("built.serializedSize: {}", built.getSerializedSize());
            {
                final var path = buildOutputFile(name);
                try (var stream = new FileOutputStream(path.toFile())) {
                    built.writeTo(stream);
                    stream.flush();
                }
            }
        }
        // read
        {
            final var path = buildOutputFile(name);
            try (var stream = new FileInputStream(path.toFile())) {
                final var parsed = KftcFinancialInstitutionInfoProtoRepeated.parseFrom(stream);
                final var list = parsed.getObjectsList().stream().map(v -> {
                    try {
                        return KftcFinancialInstitutionInfoProtoOuterClassUtils.from(v);
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
                assertThat(list).isEqualTo(instance.getList());
            }
        }
    }
}

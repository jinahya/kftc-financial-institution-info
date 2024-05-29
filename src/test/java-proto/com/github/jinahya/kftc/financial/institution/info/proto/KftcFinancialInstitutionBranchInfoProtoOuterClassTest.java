package com.github.jinahya.kftc.financial.institution.info.proto;

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionBranchInfoSet;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionBranchInfoProtoOuterClass.KftcFinancialInstitutionBranchInfoProtoRepeated;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitution_Resource__Test.buildOutputFile;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionBranchInfoProtoOuterClassTest {

    @Test
    void __() throws IOException {
        final var name = "codefilex.binpb";
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var mapped = instance.getList().stream()
                .map(KftcFinancialInstitutionBranchInfoProtoOuterClassUtils::from)
                .toList();
        final var built = KftcFinancialInstitutionBranchInfoProtoRepeated.newBuilder()
                .addAllObjects(mapped).build();
        // write
        {
            final var path = buildOutputFile(name);
            try (var stream = new FileOutputStream(path.toFile())) {
                built.writeTo(stream);
                stream.flush();
            }
        }
        // parse/assert
        {
            final var path = buildOutputFile(name);
            try (var stream = new FileInputStream(path.toFile())) {
                final var parsed = KftcFinancialInstitutionBranchInfoProtoRepeated.parseFrom(stream);
                final var list = parsed.getObjectsList().stream().map(v -> {
                    try {
                        return KftcFinancialInstitutionBranchInfoProtoOuterClassUtils.from(v);
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
                assertThat(list).isEqualTo(instance.getList());
            }
        }
    }
}

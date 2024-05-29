package com.github.jinahya.kftc.financial.institution.info.proto;

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionCategory;
import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionInfo;
import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionInfoSet;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProto;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProtoRepeated;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitution_Resource__Test.buildOutputFile;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionInfoProtoOuterClassTest {

    private KftcFinancialInstitutionInfoProto from(final KftcFinancialInstitutionInfo source) {
        Objects.requireNonNull(source, "source is null");
        return KftcFinancialInstitutionInfoProto.newBuilder()
                .setCategory(source.getCategory().name())
                .setCode(source.getCode())
                .setName(source.getName())
                .setRepresentative(source.isRepresentative())
                .build();
    }

    private KftcFinancialInstitutionInfo from(final KftcFinancialInstitutionInfoProto source)
            throws Exception {
        final var constructor = KftcFinancialInstitutionInfo.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        final var instance = constructor.newInstance();
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setCategory", KftcFinancialInstitutionCategory.class);
            method.setAccessible(true);
            method.invoke(instance, KftcFinancialInstitutionCategory.valueOf(source.getCategory()));
        }
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setCode", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getCode());
        }
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setName", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getName());
        }
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setRepresentative", boolean.class);
            method.setAccessible(true);
            method.invoke(instance, source.getRepresentative());
        }
        return instance;
    }

    @Test
    void __() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final String name = "bankinfo.binpb";
        // write
        {
            final var mapped = instance.getList().stream()
                    .map(this::from)
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
                        return from(v);
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
                assertThat(list).isEqualTo(instance.getList());
            }
        }
    }
}

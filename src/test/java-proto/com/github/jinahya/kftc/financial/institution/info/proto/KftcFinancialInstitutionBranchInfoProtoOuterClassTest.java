package com.github.jinahya.kftc.financial.institution.info.proto;

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionBranchInfo;
import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionBranchInfoSet;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionBranchInfoProtoOuterClass.KftcFinancialInstitutionBranchInfoProto;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionBranchInfoProtoOuterClass.KftcFinancialInstitutionBranchInfoProtoRepeated;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitution_Resource__Test.buildOutputFile;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionBranchInfoProtoOuterClassTest {

    private static KftcFinancialInstitutionBranchInfoProto from(final KftcFinancialInstitutionBranchInfo source) {
        Objects.requireNonNull(source, "source is null");
        final var builder = KftcFinancialInstitutionBranchInfoProto.newBuilder()
                .setBranchCode(source.getBranchCode())
                .setFinancialInstitutionName(source.getFinancialInstitutionName())
                .setBranchName(source.getBranchName())
                .setStatus(source.getStatus());
        Optional.ofNullable(source.getPhoneNumber()).ifPresent(builder::setPhoneNumber);
        Optional.ofNullable(source.getFaxNumber()).ifPresent(builder::setFaxNumber);
        Optional.ofNullable(source.getPostalCode()).ifPresent(builder::setPostalCode);
        Optional.ofNullable(source.getAddress()).ifPresent(builder::setAddress);
        Optional.ofNullable(source.getManagingBranchCode()).ifPresent(builder::setManagingBranchCode);
        return builder.build();
    }

    private static KftcFinancialInstitutionBranchInfo from(final KftcFinancialInstitutionBranchInfoProto source)
            throws Exception {
        Objects.requireNonNull(source, "source is null");
        final var constructor = KftcFinancialInstitutionBranchInfo.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        final var instance = constructor.newInstance();
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setBranchCode", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getBranchCode());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setFinancialInstitutionName", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getFinancialInstitutionName());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setBranchName", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getBranchName());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setPhoneNumber", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getPhoneNumber());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setFaxNumber", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getFaxNumber());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setPostalCode", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getPostalCode());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setAddress", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getAddress());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setStatus", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getStatus());
        }
        {
            final var method = KftcFinancialInstitutionBranchInfo.class.
                    getDeclaredMethod("setManagingBranchCode", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getManagingBranchCode());
        }
        return instance;
    }

    @Test
    void __() throws IOException {
        final var name = "codefilex.binpb";
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var mapped = instance.getList().stream()
                .map(KftcFinancialInstitutionBranchInfoProtoOuterClassTest::from)
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

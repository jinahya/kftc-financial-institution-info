package com.github.jinahya.kftc.financial.institution.info.proto;

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionBranchInfo;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionBranchInfoProtoOuterClass.KftcFinancialInstitutionBranchInfoProto;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
final class KftcFinancialInstitutionBranchInfoProtoOuterClassUtils {

    static KftcFinancialInstitutionBranchInfoProto from(final KftcFinancialInstitutionBranchInfo source) {
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

    static KftcFinancialInstitutionBranchInfo from(final KftcFinancialInstitutionBranchInfoProto source)
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

    private KftcFinancialInstitutionBranchInfoProtoOuterClassUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

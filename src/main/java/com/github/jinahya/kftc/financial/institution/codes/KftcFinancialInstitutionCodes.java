package com.github.jinahya.kftc.financial.institution.codes;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class KftcFinancialInstitutionCodes {

    static final String RESOURCE_NAME = "bankinfo.bin";

    public static Map<String, KftcFinancialInstitutionCode> load() {
        final var resource = KftcFinancialInstitutionCodes.class.getResource(RESOURCE_NAME);
        assert resource != null;
        try {
            try (var lines = Files.lines(Paths.get(resource.toURI()))) {
                return lines.map(KftcFinancialInstitutionCode::parse)
                        .collect(Collectors.toMap(KftcFinancialInstitutionCode::getCode,
                                                  Function.identity()));
            }
        } catch (final Exception e) {
            throw new RuntimeException("failed to load/parse data", e);
        }
    }

    private KftcFinancialInstitutionCodes() {
        throw new AssertionError("instantiation is not allowed");
    }
}

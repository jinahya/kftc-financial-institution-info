package com.github.jinahya.kftc.financial.institution.codes;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class KftcFinancialInstitutionCodes {

    static final String RESOURCE_NAME = "bankinfo.bin";

    /**
     * Loads the resource, applies a stream of codes to specified function, and returns the result.
     *
     * @param function the function to be applied with the stream of loaded codes.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     * @see #getCodeList()
     * @see #getCodeMap()
     */
    static <R> R applyCodeStream(final Function<? super Stream<KftcFinancialInstitutionCode>, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        final var resource = KftcFinancialInstitutionCodes.class.getResource(RESOURCE_NAME);
        if (resource == null) {
            throw new RuntimeException("resource not found: " + RESOURCE_NAME);
        }
        try {
            try (var lines = Files.lines(Paths.get(resource.toURI()))) {
                return function.apply(lines.map(KftcFinancialInstitutionCode::parse));
            }
        } catch (final Exception e) {
            throw new RuntimeException("failed to load/parse resource", e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final class CodeListHolder {

        private static final List<KftcFinancialInstitutionCode> CODE_MAP = applyCodeStream(
                s -> s.collect(Collectors.toUnmodifiableList())
        );

        private CodeListHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Returns an unmodifiable list of codes.
     *
     * @return an unmodifiable list of codes.
     */
    public static List<KftcFinancialInstitutionCode> getCodeList() {
        return CodeListHolder.CODE_MAP;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final class CodeMapHolder {

        private static final Map<String, KftcFinancialInstitutionCode> CODE_MAP = getCodeList().stream().collect(
                Collectors.toUnmodifiableMap(KftcFinancialInstitutionCode::getCode, Function.identity())
        );

        private CodeMapHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Returns an unmodifiable map of code values and codes.
     *
     * @return an unmodifiable map of code values and codes.
     */
    public static Map<String, KftcFinancialInstitutionCode> getCodeMap() {
        return CodeMapHolder.CODE_MAP;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private KftcFinancialInstitutionCodes() {
        throw new AssertionError("instantiation is not allowed");
    }
}

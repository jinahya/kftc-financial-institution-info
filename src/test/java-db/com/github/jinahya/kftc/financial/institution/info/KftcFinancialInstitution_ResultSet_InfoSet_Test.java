package com.github.jinahya.kftc.financial.institution.info;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitution_ResultSet_InfoSet_Test
        extends KftcFinancialInstitution_ResultSet__Test {

    private static Stream<KftcFinancialInstitutionInfo> getInstanceStream() {
        return KftcFinancialInstitutionInfoSet.newInstance()
                .getList()
                .stream();
    }

    private static Stream<String> getCodeStream() {
        return getInstanceStream()
                .map(KftcFinancialInstitutionInfo::getCode);
    }

    private static Stream<KftcFinancialInstitutionCategory> getCategoryStream() {
        return getInstanceStream()
                .map(KftcFinancialInstitutionInfo::getCategory);
    }

    private static Stream<String> getNameStream() {
        return getInstanceStream()
                .map(KftcFinancialInstitutionInfo::getName);
    }

    private static Stream<String> getNamePatternStream() {
        return getNameStream()
                .flatMap(v -> Stream.of(
                        v,
                        '_' + v.substring(1),
                        v.substring(0, v.length() - 1) + '_',
                        '%' + v.substring(1),
                        v.substring(0, v.length() - 1) + '%',
                        '%' + v.substring(1, v.length() - 1) + '%'
                ));
    }

    private static Stream<String> getNamePatternStream(final String name) {
        return Stream.of(
                name,
                '_' + name.substring(1),
                name.substring(0, name.length() - 1) + '_',
                '%' + name.substring(1),
                name.substring(0, name.length() - 1) + '%',
                '%' + name.substring(1, name.length() - 1) + '%'
        );
    }

    private static Stream<Arguments> getCategoryAndNameArgumentsStream() {
        return getCategoryStream()
                .flatMap(c -> getNameStream().map(n -> Arguments.of(c, n)));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void selectAll__() throws SQLException {
        final var all = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectAll(c);
            } catch (final SQLException sqle) {
                throw new RuntimeException("failed to invoke selectAll; connection: " + c, sqle);
            }
        });
        assertThat(all)
                .isNotEmpty()
                .doesNotContainNull()
                .isSortedAccordingTo(Comparator.comparing(KftcFinancialInstitutionInfo::getCode));
    }

    @MethodSource({"getCodeStream"})
    @ParameterizedTest
    void selectOneByCode__(final String code) throws SQLException {
        final var one = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectOneByCode(c, code);
            } catch (final SQLException e) {
                throw new RuntimeException("failed to invoke selectOneByCode; code: " + code, e);
            }
        });
        assertThat(one).hasValueSatisfying(v -> {
            assertThat(v.getCode()).isEqualTo(code);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Disabled("too much cases")
    @MethodSource({"getCategoryAndNameArgumentsStream"})
    @ParameterizedTest
    void selectAllByCategoryAndName__(final KftcFinancialInstitutionCategory category, final String name)
            throws SQLException {
        final var all = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectAllByCategoryAndName(c, category, name);
            } catch (final SQLException e) {
                throw new RuntimeException(
                        "failed to invoke selectAllByCategoryAndName; category: " + category + "; name: " + name,
                        e
                );
            }
        });
        assertThat(all).isNotNull().doesNotContainNull().satisfiesAnyOf(
                a -> assertThat(a).isEmpty(),
                a -> assertThat(a).isNotEmpty().allSatisfy(v -> {
                    assertThat(v.getCategory()).isSameAs(category);
                    assertThat(v.getName()).isEqualTo(name);
                })
        );
    }

    @MethodSource({"getInstanceStream"})
    @ParameterizedTest
    void selectAllByCategoryAndName__(final KftcFinancialInstitutionInfo instance)
            throws SQLException {
        final var all = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectAllByCategoryAndName(
                        c,
                        instance.getCategory(),
                        instance.getName()
                );
            } catch (final SQLException e) {
                throw new RuntimeException("failed to invoke selectAllByCategoryAndName; instance: " + instance, e);
            }
        });
        assertThat(all).isNotNull().doesNotContainNull().allSatisfy(v -> {
            assertThat(v.getCategory()).isSameAs(instance.getCategory());
            assertThat(v.getName()).isEqualTo(instance.getName());
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"getInstanceStream"})
    @ParameterizedTest
    void selectAllByCategoryAndNameLike__(final KftcFinancialInstitutionInfo instance)
            throws SQLException {
        applyConnection(c -> {
            getNamePatternStream(instance.getName()).forEach(p -> {
                try {
                    final var all = KftcFinancialInstitutionInfoResultSet.selectAllByCategoryAndNameLike(
                            c,
                            instance.getCategory(),
                            p
                    );
                    assertThat(all)
                            .isNotNull()
                            .doesNotContainNull()
                            .isNotEmpty()
                            .allSatisfy(v -> {
                                assertThat(v.getCategory()).isSameAs(instance.getCategory());
                            })
                            .contains(instance);
                } catch (final SQLException e) {
                    throw new RuntimeException("failed to invoke selectAllByCategoryAndName; instance: " + instance, e);
                }
            });
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"getNameStream"})
    @ParameterizedTest
    void selectAllByName__(final String name) throws SQLException {
        final var all = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectAllByName(c, name);
            } catch (final SQLException e) {
                throw new RuntimeException("failed to invoke selectAllByName; name: " + name, e);
            }
        });
        assertThat(all).isNotNull().isNotEmpty().doesNotContainNull().allSatisfy(v -> {
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @MethodSource({"getNamePatternStream"})
    @ParameterizedTest
    void selectAllByNameLike__(final String pattern) throws SQLException {
        final var all = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectAllByNameLike(c, pattern);
            } catch (final SQLException e) {
                throw new RuntimeException("failed to invoke selectAllByNameLike; pattern: " + pattern, e);
            }
        });
        assertThat(all).isNotNull().isNotEmpty().doesNotContainNull().allSatisfy(v -> {
        });
    }

    @DisplayName("%국%")
    @Test
    void selectAllByNameLike__국() throws SQLException {
        final var pattern = "%국%";
        final var all = applyConnection(c -> {
            try {
                return KftcFinancialInstitutionInfoResultSet.selectAllByNameLike(c, pattern);
            } catch (final SQLException e) {
                throw new RuntimeException("failed to invoke selectAllByNameLike; pattern: " + pattern, e);
            }
        });
        assertThat(all).isNotNull().isNotEmpty().doesNotContainNull().allSatisfy(v -> {
            assertThat(v.getName()).contains("국");
        });
    }
}

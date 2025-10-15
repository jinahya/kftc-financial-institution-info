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

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class for accessing instances of {@link KftcFinancialInstitutionBranchInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @implSpec Instances of this class are unmodifiable and thread-safe.
 * @see KftcFinancialInstitutionBranchInfo
 */
public final class KftcFinancialInstitutionBranchInfoSet
        implements _InfoSet<KftcFinancialInstitutionBranchInfo> {

    // -----------------------------------------------------------------------------------------------------------------
    static final String RESOURCE_NAME = "codefilex.ser";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Returns a new instance of this class.
     *
     * @return a new instance of this class.
     * @implSpec This method, whenever invoked, loads a resource from the classpath. Callees are recommended to
     *         store the result.
     */
    public static KftcFinancialInstitutionBranchInfoSet newInstance() {
        try (var resource = KftcFinancialInstitutionBranchInfoSet.class.getResourceAsStream(RESOURCE_NAME)) {
            assert resource != null;
            final var loaded = (Object[]) _IoUtils.readObject(resource);
            final var array = Arrays.copyOf(loaded, loaded.length, KftcFinancialInstitutionBranchInfo[].class);
            return new KftcFinancialInstitutionBranchInfoSet(array);
        } catch (final Exception e) {
            throw new RuntimeException("failed to load resource for " + RESOURCE_NAME, e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static volatile SoftReference<KftcFinancialInstitutionBranchInfoSet> INSTANCE;

    // https://stackoverflow.com/questions/79789816/how-can-i-cache-a-single-object
    private static KftcFinancialInstitutionBranchInfoSet getInstance1() {
        var result = INSTANCE == null ? null : INSTANCE.get();
        if (result == null) {
            synchronized (KftcFinancialInstitutionInfoSet.class) {
                result = INSTANCE == null ? null : INSTANCE.get();
                if (result == null) {
                    result = newInstance();
                    INSTANCE = new SoftReference<>(result);
                }
            }
        }
        return result;
    }

    // https://stackoverflow.com/questions/79789816/how-can-i-cache-a-single-object
    private static KftcFinancialInstitutionBranchInfoSet getInstance2() {
        var result = INSTANCE == null ? null : INSTANCE.get();
        if (result == null) {
            synchronized (KftcFinancialInstitutionInfoSet.class) {
                result = newInstance();
                INSTANCE = new SoftReference<>(result);
            }
        }
        return result;
    }

    private static KftcFinancialInstitutionBranchInfoSet getInstance() {
        return getInstance2();
    }

    /**
     * Returns the result of the specified function applied to a <em>cached</em> instance of this class.
     *
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    static <R> R applyInstance(final Function<? super KftcFinancialInstitutionBranchInfoSet, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(getInstance());
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance with the specified array.
     *
     * @param array the array to hold.
     */
    KftcFinancialInstitutionBranchInfoSet(final KftcFinancialInstitutionBranchInfo[] array) {
        super();
        this.list = Arrays.asList(Objects.requireNonNull(array, "array is null"));
        map = this.list.stream()
                .collect(Collectors.toUnmodifiableMap(
                        KftcFinancialInstitutionBranchInfo::getBranchCode,
                        Function.identity())
                );
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -------------------------------------------------------------------------------------------------------- _InfoSet

    // ------------------------------------------------------------------------------------------------------------ list

    /**
     * Returns an <em>unmodifiable</em> list of branch info.
     *
     * @return an <em>unmodifiable</em> list of branch info.
     */
    public List<KftcFinancialInstitutionBranchInfo> getList() {
        return list;
    }

    // ------------------------------------------------------------------------------------------------------------- map

    /**
     * Returns an <em>unmodifiable</em> map of branch codes and branch info.
     *
     * @return an <em>unmodifiable</em> map of branch codes and branch info.
     */
    public Map<String, KftcFinancialInstitutionBranchInfo> getMap() {
        return map;
    }

    /**
     * Returns the branch info whose current value of
     * {@link KftcFinancialInstitutionBranchInfo#getBranchCode() branchCode} property matches specified value.
     *
     * @param branchCode the {@link KftcFinancialInstitutionBranchInfo#getBranchCode() branchCode} property value to
     *                   match.
     * @return an optional of matched branch info; {@link Optional#empty() empty} when none matches.
     */
    @Override
    public Optional<KftcFinancialInstitutionBranchInfo> get(final String branchCode) {
        return Optional.ofNullable(getMap().get(branchCode));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final List<KftcFinancialInstitutionBranchInfo> list;

    private final Map<String, KftcFinancialInstitutionBranchInfo> map;
}

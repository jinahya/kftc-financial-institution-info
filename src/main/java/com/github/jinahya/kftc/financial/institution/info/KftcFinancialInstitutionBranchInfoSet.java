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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class for accessing instances of {@link KftcFinancialInstitutionBranchInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see KftcFinancialInstitutionBranchInfo
 */
public final class KftcFinancialInstitutionBranchInfoSet {

    // -----------------------------------------------------------------------------------------------------------------
    static final String RESOURCE_NAME = "codefilex.ser";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     * @implSpec This method, everytime it's called, loads a resource from the classpath. Callees are recommended to
     * store the result.
     */
    public static KftcFinancialInstitutionBranchInfoSet newInstance() {
        try (var resource = KftcFinancialInstitutionBranchInfoSet.class.getResourceAsStream(RESOURCE_NAME)) {
            assert resource != null;
            final var loaded = (Object[]) _IoUtils.read(resource);
            final var array = Arrays.copyOf(loaded, loaded.length, KftcFinancialInstitutionBranchInfo[].class);
            return new KftcFinancialInstitutionBranchInfoSet(Arrays.asList(array));
        } catch (final Exception e) {
            throw new RuntimeException("failed to load data from the resource", e);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance with specified list.
     *
     * @param list the list to hold.
     */
    KftcFinancialInstitutionBranchInfoSet(final List<KftcFinancialInstitutionBranchInfo> list) {
        super();
        this.list = Objects.requireNonNull(list, "list is null");
        map = this.list.stream()
                .collect(Collectors.toUnmodifiableMap(
                        KftcFinancialInstitutionBranchInfo::getBranchCode,
                        Function.identity())
                );
    }

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
     * Returns the branch info whose {@link KftcFinancialInstitutionBranchInfo#getBranchCode() branchCode} property
     * matches specified value.
     *
     * @param branchCode the {@link KftcFinancialInstitutionBranchInfo#getBranchCode() branchCode} property value to
     *                   match.
     * @return an optional of matched branch info; {@link Optional#empty() empty} when none matches.
     */
    public Optional<KftcFinancialInstitutionBranchInfo> get(final String branchCode) {
        return Optional.ofNullable(getMap().get(branchCode));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final List<KftcFinancialInstitutionBranchInfo> list;

    private final Map<String, KftcFinancialInstitutionBranchInfo> map;
}

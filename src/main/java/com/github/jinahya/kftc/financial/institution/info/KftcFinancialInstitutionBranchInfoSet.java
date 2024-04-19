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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class for accessing instances of {@link KftcFinancialInstitutionBranchInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see KftcFinancialInstitutionBranchInfo
 */
public final class KftcFinancialInstitutionBranchInfoSet
        implements Serializable {

    private static final long serialVersionUID = 3830614459747984712L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String RESOURCE_NAME = "codefilex.ser";

    // -----------------------------------------------------------------------------------------------------------------
    private static final class InstanceHolder {

        private static final KftcFinancialInstitutionBranchInfoSet INSTANCE;

        static {
            try (var resource = KftcFinancialInstitutionBranchInfoSet.class.getResourceAsStream(RESOURCE_NAME)) {
                if (resource == null) {
                    throw new RuntimeException("no resource for " + RESOURCE_NAME);
                }
                INSTANCE = _IoUtils.read(resource);
            } catch (final Exception e) {
                throw new RuntimeException("failed to load resource", e);
            }
        }

        private InstanceHolder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static KftcFinancialInstitutionBranchInfoSet getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    KftcFinancialInstitutionBranchInfoSet(final List<KftcFinancialInstitutionBranchInfo> list) {
        super();
        this.list = Objects.requireNonNull(list, "list is null");
        new HashMap<String, String>();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/3343314/330457
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        assert list != null;
        map();
    }

    private void map() {
        map = list.stream()
                .collect(Collectors.toMap(KftcFinancialInstitutionBranchInfo::getBranchCode, Function.identity()));
    }

    // ------------------------------------------------------------------------------------------------------------ list

    // ------------------------------------------------------------------------------------------------------------- map

    /**
     * Returns an <em>unmodifiable</em> map of branch codes and branch info.
     *
     * @return an <em>unmodifiable</em> map of branch codes and branch info.
     */
    public Map<String, KftcFinancialInstitutionBranchInfo> getMap() {
        if (map == null) {
            map();
        }
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

    private transient Map<String, KftcFinancialInstitutionBranchInfo> map;
}
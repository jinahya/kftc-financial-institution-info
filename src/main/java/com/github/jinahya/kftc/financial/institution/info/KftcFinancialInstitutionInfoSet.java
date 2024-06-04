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
 * A class for accessing instances of {@link KftcFinancialInstitutionInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @implSpec Instances of this class are unmodifiable and thread-safe.
 * @see KftcFinancialInstitutionInfo
 */
public final class KftcFinancialInstitutionInfoSet {

    // -----------------------------------------------------------------------------------------------------------------
    static final String RESOURCE_NAME = "bankinfo.ser";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Returns a new instance of this class.
     *
     * @return a new instance of this class.
     * @implSpec This method, whenever invoked, loads a resource from the classpath. Callees are recommended to store
     * the result.
     */
    public static KftcFinancialInstitutionInfoSet newInstance() {
        try (var resource = KftcFinancialInstitutionInfoSet.class.getResourceAsStream(RESOURCE_NAME)) {
            assert resource != null;
            final var loaded = (Object[]) _IoUtils.read(resource);
            final var array = Arrays.copyOf(loaded, loaded.length, KftcFinancialInstitutionInfo[].class);
            return new KftcFinancialInstitutionInfoSet(Arrays.asList(array));
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
    KftcFinancialInstitutionInfoSet(final List<KftcFinancialInstitutionInfo> list) {
        super();
        this.list = Objects.requireNonNull(list, "list is null");
        map = this.list.stream()
                .collect(Collectors.toUnmodifiableMap(KftcFinancialInstitutionInfo::getCode, Function.identity()));
    }

    // ------------------------------------------------------------------------------------------------------------ list

    /**
     * Returns an <em>unmodifiable</em> list of institution info.
     *
     * @return an <em>unmodifiable</em> list of institution info.
     */
    public List<KftcFinancialInstitutionInfo> getList() {
        return list;
    }

    // ------------------------------------------------------------------------------------------------------------- map

    /**
     * Returns an <em>unmodifiable</em> map of {@link KftcFinancialInstitutionInfo#getCode() codes} and info.
     *
     * @return an <em>unmodifiable</em> map of {@link KftcFinancialInstitutionInfo#getCode() codes} and info.
     */
    public Map<String, KftcFinancialInstitutionInfo> getMap() {
        return map;
    }

    /**
     * Returns the info whose current value of {@link KftcFinancialInstitutionInfo#getCode() code} property matches
     * specified value.
     *
     * @param code the {@link KftcFinancialInstitutionInfo#getCode() code} property value to match.
     * @return an optional of matched value; {@link Optional#empty() empty} when none matches.
     */
    public Optional<KftcFinancialInstitutionInfo> get(final String code) {
        return Optional.ofNullable(getMap().get(code));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * the list.
     */
    private final List<KftcFinancialInstitutionInfo> list;

    /**
     * a map grouped by the {@code code}.
     */
    private final Map<String, KftcFinancialInstitutionInfo> map;
}

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
 * A class for accessing instances of {@link KftcFinancialInstitutionInfo}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see KftcFinancialInstitutionInfo
 */
public final class KftcFinancialInstitutionInfoSet
        implements Serializable {

    private static final long serialVersionUID = 4389137085143896510L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String RESOURCE_NAME = "bankinfo.ser";

    // -----------------------------------------------------------------------------------------------------------------
    private static final class InstanceHolder {

        private static final KftcFinancialInstitutionInfoSet INSTANCE;

        static {
            try (var resource = KftcFinancialInstitutionInfoSet.class.getResourceAsStream(RESOURCE_NAME)) {
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

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    public static KftcFinancialInstitutionInfoSet getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    KftcFinancialInstitutionInfoSet(final List<KftcFinancialInstitutionInfo> list) {
        super();
        this.list = Objects.requireNonNull(list, "list is null");
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
                .collect(Collectors.toMap(KftcFinancialInstitutionInfo::getCode, Function.identity()));
    }

    // ------------------------------------------------------------------------------------------------------------ list

    // ------------------------------------------------------------------------------------------------------------- map

    /**
     * Returns an <em>unmodifiable</em> map of {@link KftcFinancialInstitutionInfo#getCode() codes} and info.
     *
     * @return an <em>unmodifiable</em> map of {@link KftcFinancialInstitutionInfo#getCode() codes} and info.
     */
    public Map<String, KftcFinancialInstitutionInfo> getMap() {
        if (map == null) {
            map();
        }
        return map;
    }

    /**
     * Returns the info whose {@link KftcFinancialInstitutionInfo#getCode() code} property matches specified value.
     *
     * @param code the {@link KftcFinancialInstitutionInfo#getCode() code} property value to match.
     * @return an optional of matched value; {@link Optional#empty() empty} when none matches.
     */
    public Optional<KftcFinancialInstitutionInfo> get(final String code) {
        return Optional.ofNullable(getMap().get(code));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final List<KftcFinancialInstitutionInfo> list;

    private transient Map<String, KftcFinancialInstitutionInfo> map;
}

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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Returns a new instance of this class.
     *
     * @return the instance of this class.
     * @implSpec This method, everytime it's called, loads a resource from the classpath. Callees are recommended to
     * store the result.
     */
    public static KftcFinancialInstitutionInfoSet newInstance() {
        try (var resource = KftcFinancialInstitutionInfoSet.class.getResourceAsStream(RESOURCE_NAME)) {
            if (resource == null) {
                throw new RuntimeException("no resource for " + RESOURCE_NAME);
            }
            return _IoUtils.read(resource);
        } catch (final Exception e) {
            throw new RuntimeException("failed to load", e);
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
        this.list = List.copyOf(Objects.requireNonNull(list, "list is null"));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/3343314/330457
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        assert list != null;
        map();
    }

    private void map() {
        map = getList().stream()
                .collect(Collectors.toMap(KftcFinancialInstitutionInfo::getCode, Function.identity()));
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
    @SuppressWarnings({"serial"})
    private final List<KftcFinancialInstitutionInfo> list;

    private transient Map<String, KftcFinancialInstitutionInfo> map;
}

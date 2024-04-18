package com.github.jinahya.kftc.financial.institution.codes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public Map<String, KftcFinancialInstitutionBranchInfo> getMap() {
        if (map == null) {
            map();
        }
        return map;
    }

    public KftcFinancialInstitutionBranchInfo get(final String branchCode) {
        return getMap().get(branchCode);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final List<KftcFinancialInstitutionBranchInfo> list;

    private transient Map<String, KftcFinancialInstitutionBranchInfo> map;
}

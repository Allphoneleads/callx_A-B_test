
package com.allphoneleads.api.proctor;

import com.google.common.base.Objects;
import com.google.common.base.Defaults;

import com.indeed.proctor.common.Identifiers;
import com.indeed.proctor.common.ProctorResult;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*
 * GENERATED source; do not edit directly
 */
public class RoutingSpecificationContext {
    private final String offersSize;
    private final String offersIdsAsc;

    public RoutingSpecificationContext(final String offersSize, final String offersIdsAsc) {
        this.offersSize = offersSize;
        this.offersIdsAsc = offersIdsAsc;
    }

    // For builder use only
    private RoutingSpecificationContext() {
        this.offersSize = Defaults.defaultValue(String.class);
        this.offersIdsAsc = Defaults.defaultValue(String.class);
    }

    private static final RoutingSpecificationContext DEFAULT = new RoutingSpecificationContext();
    public static RoutingSpecificationContext getDefault() {
        return DEFAULT;
    }

    public String getOffersSize() {
        return offersSize;
    }

    public String getOffersIdsAsc() {
        return offersIdsAsc;
    }

    @Nonnull
    public String toString() {
        return "RoutingSpecificationContext{" +
               "offersSize='" + offersSize + "', " +
               "offersIdsAsc='" + offersIdsAsc + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoutingSpecificationContext)) {
            return false;
        }

        return
            Objects.equal(((RoutingSpecificationContext) obj).offersSize, offersSize) &&
            Objects.equal(((RoutingSpecificationContext) obj).offersIdsAsc, offersIdsAsc);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            this.offersSize, 
            this.offersIdsAsc
        );
    }

    /**
    * This should be used for non-webapp applications that are working
    * with test groups as those applications will not have a request and response,
    * such as boxcar services.
    */
    public ProctorResult getProctorResult(@Nonnull final RoutingSpecificationManager groupsManager,
                                          @Nonnull final Identifiers identifiers) {
        return groupsManager.determineBuckets(identifiers, 
                                              offersSize,
                                              offersIdsAsc);
    }

    /**
    * This should be used for non-webapp applications that are working
    * with test groups as those applications will not have a request and response,
    * such as boxcar services.
    */
    public ProctorResult getProctorResult(@Nonnull final RoutingSpecificationManager groupsManager,
                                          @Nonnull final Identifiers identifiers,
                                          @Nonnull final Map<String, Integer> forcedGroups) {
        return groupsManager.determineBuckets(identifiers, forcedGroups, 
                                              offersSize,
                                              offersIdsAsc);
    }

    public ProctorResult getProctorResult(@Nonnull final RoutingSpecificationManager groupsManager,
                                          @Nonnull final HttpServletRequest request,
                                          @Nonnull final HttpServletResponse response,
                                          @Nonnull final Identifiers identifiers,
                                          final boolean allowForceGroups) {
        return groupsManager.determineBuckets(request, response, identifiers, allowForceGroups, 
                                              offersSize,
                                              offersIdsAsc);

    }

    @Nonnull
    public static Builder newBuilder() { return new Builder(); }

    public static class Builder {
        private String offersSize = Defaults.defaultValue(String.class);
        private String offersIdsAsc = Defaults.defaultValue(String.class);
        private Builder() {}

        @Nonnull
        public Builder setOffersSize(final String value) {
            this.offersSize = value;
            return this;
        }

        @Nonnull
        public Builder setOffersIdsAsc(final String value) {
            this.offersIdsAsc = value;
            return this;
        }

        @Nonnull
        public RoutingSpecificationContext build() {
            return new RoutingSpecificationContext(
                this.offersSize, 
                this.offersIdsAsc
            );
        }
    }
}

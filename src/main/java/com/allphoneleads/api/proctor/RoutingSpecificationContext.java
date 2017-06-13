
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
    private final String offerssize;
    private final String offersidasc;

    public RoutingSpecificationContext(final String offerssize, final String offersidasc) {
        this.offerssize = offerssize;
        this.offersidasc = offersidasc;
    }

    // For builder use only
    private RoutingSpecificationContext() {
        this.offerssize = Defaults.defaultValue(String.class);
        this.offersidasc = Defaults.defaultValue(String.class);
    }

    private static final RoutingSpecificationContext DEFAULT = new RoutingSpecificationContext();
    public static RoutingSpecificationContext getDefault() {
        return DEFAULT;
    }

    public String getOfferssize() {
        return offerssize;
    }

    public String getOffersidasc() {
        return offersidasc;
    }

    @Nonnull
    public String toString() {
        return "RoutingSpecificationContext{" +
               "offerssize='" + offerssize + "', " +
               "offersidasc='" + offersidasc + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoutingSpecificationContext)) {
            return false;
        }

        return
            Objects.equal(((RoutingSpecificationContext) obj).offerssize, offerssize) &&
            Objects.equal(((RoutingSpecificationContext) obj).offersidasc, offersidasc);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            this.offerssize, 
            this.offersidasc
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
                                              offerssize,
                                              offersidasc);
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
                                              offerssize,
                                              offersidasc);
    }

    public ProctorResult getProctorResult(@Nonnull final RoutingSpecificationManager groupsManager,
                                          @Nonnull final HttpServletRequest request,
                                          @Nonnull final HttpServletResponse response,
                                          @Nonnull final Identifiers identifiers,
                                          final boolean allowForceGroups) {
        return groupsManager.determineBuckets(request, response, identifiers, allowForceGroups, 
                                              offerssize,
                                              offersidasc);

    }

    @Nonnull
    public static Builder newBuilder() { return new Builder(); }

    public static class Builder {
        private String offerssize = Defaults.defaultValue(String.class);
        private String offersidasc = Defaults.defaultValue(String.class);
        private Builder() {}

        @Nonnull
        public Builder setOfferssize(final String value) {
            this.offerssize = value;
            return this;
        }

        @Nonnull
        public Builder setOffersidasc(final String value) {
            this.offersidasc = value;
            return this;
        }

        @Nonnull
        public RoutingSpecificationContext build() {
            return new RoutingSpecificationContext(
                this.offerssize, 
                this.offersidasc
            );
        }
    }
}

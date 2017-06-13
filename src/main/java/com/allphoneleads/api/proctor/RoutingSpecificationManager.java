package com.allphoneleads.api.proctor;

import com.indeed.proctor.common.*;
import com.indeed.proctor.common.model.*;
import com.indeed.proctor.consumer.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Supplier;


/*
 * GENERATED source; do not edit directly
 */
public class RoutingSpecificationManager extends AbstractGroupsManager {
    private static final Map<String, String> PROVIDED_CONTEXT;
    static {
        final Map<String, String> providedContext = new LinkedHashMap<String, String>();
        providedContext.put("offerssize", "String");
        providedContext.put("offersidasc", "String");
        PROVIDED_CONTEXT = Collections.unmodifiableMap(providedContext);
    }

    public RoutingSpecificationManager(final Supplier<Proctor> proctorSource) {
        super(proctorSource);
    }

    /**
     * This should be used for non-webapp applications that are working
     * with test groups as those applications will not have a request and response,
     * such as boxcar services.
     * @deprecated Use the one that takes a Map<TestType, String> instead
     */
    public ProctorResult determineBuckets(final TestType testType, final String identifier,
                                    final String offerssize,
                                    final String offersidasc) {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("offerssize", offerssize);
        context.put("offersidasc", offersidasc);
        return super.determineBucketsInternal(testType, identifier, context);
    }

    /**
     * This should be used for non-webapp applications that are working
     * with test groups as those applications will not have a request and response,
     * such as boxcar services.
     */
    public ProctorResult determineBuckets(final Identifiers identifiers,
                                    final String offerssize,
                                    final String offersidasc) {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("offerssize", offerssize);
        context.put("offersidasc", offersidasc);
        return super.determineBucketsInternal(identifiers, context);
    }

    /**
     * This should be used for non-webapp applications that are working
     * with test groups as those applications will not have a request and response,
     * such as boxcar services.
     */
    public ProctorResult determineBuckets(final Identifiers identifiers,
                                            final Map<String, Integer> forcedGroups,
                                    final String offerssize,
                                    final String offersidasc) {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("offerssize", offerssize);
        context.put("offersidasc", offersidasc);
        return super.determineBucketsInternal(identifiers, context, forcedGroups);
    }

    /*
     * @deprecated Use the one that takes a Map<TestType, String> instead
     */
    public ProctorResult determineBuckets(final HttpServletRequest request, final HttpServletResponse response,
                                            final TestType testType, final String identifier, final boolean allowForcedGroups,
                                            final String offerssize,
                                            final String offersidasc) {
        final Identifiers identifiers = new Identifiers(testType, identifier);
        return determineBuckets(request, response, identifiers, allowForcedGroups
                , offerssize
                , offersidasc
                );
    }

    public ProctorResult determineBuckets(final HttpServletRequest request, final HttpServletResponse response,
                                            final Identifiers identifiers, final boolean allowForcedGroups,
                                            final String offerssize,
                                            final String offersidasc) {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("offerssize", offerssize);
        context.put("offersidasc", offersidasc);
        return super.determineBucketsInternal(request, response, identifiers, context, allowForcedGroups);
    }

    private static final Map<String, TestBucket> DEFAULT_BUCKET_VALUES = constructDefaultBucketValuesMap();
    private static Map<String, TestBucket> constructDefaultBucketValuesMap() {
        final Map<String, TestBucket> defaultBucketValues = new HashMap<String, TestBucket>();
            defaultBucketValues.put("autoinsurancebundle", new TestBucket("fallback", -1, "fallback value; something is broken", null));
            defaultBucketValues.put("autoinsurancebundleoffers", new TestBucket("fallback", -1, "fallback value; something is broken", null));
        return Collections.unmodifiableMap(defaultBucketValues);
    }
    @Override
    protected Map<String, TestBucket> getDefaultBucketValues() {
        return DEFAULT_BUCKET_VALUES;
    }

    @Override
    public Map<String, String> getProvidedContext() {
        return PROVIDED_CONTEXT;
    }
}

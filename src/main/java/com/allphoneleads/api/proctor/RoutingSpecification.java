package com.allphoneleads.api.proctor;

import com.indeed.proctor.common.*;
import com.indeed.proctor.common.model.Payload;
import com.indeed.proctor.common.model.TestBucket;
import com.indeed.proctor.consumer.*;
import javax.annotation.Nullable;
import javax.annotation.Nonnull;
import java.lang.Override;
import java.util.Map;

/*
 * GENERATED source; do not edit directly
 * (but you can extend me.  you'll want to override {@link #toString()}, using {@link #buildTestGroupString()} or {@link #appendTestGroups(StringBuilder)} instead)
 */
public class RoutingSpecification extends AbstractGroups {

    public static final RoutingSpecification EMPTY = new RoutingSpecification(ProctorResult.EMPTY);

    public RoutingSpecification(final ProctorResult proctorResult) {
        super(proctorResult);
    }

    public static Bucket<Test>[] getBuckets(final Test test) {
        switch (test) {
            case AUTOINSURANCEBUNDLE:
                return Autoinsurancebundle.values();
            case AUTOINSURANCEBUNDLEOFFERS:
                return Autoinsurancebundleoffers.values();
        }
        return null;
    }

    public enum Test implements com.indeed.proctor.consumer.Test {
        AUTOINSURANCEBUNDLE("autoinsurancebundle", -1),
        AUTOINSURANCEBUNDLEOFFERS("autoinsurancebundleoffers", -1);
        ; // fix compilation if no tests

        private final String name;
        private final int fallbackValue;

        private Test(final String name, final int fallbackValue) {
            this.name = name;
            this.fallbackValue = fallbackValue;
        }

        @Override
        public String getName() {
            return name;
        }
        @Override
        public int getFallbackValue() {
            return fallbackValue;
        }
    }

    public enum Autoinsurancebundle implements Bucket<Test> {
        INACTIVE(-1, "inactive"),
        OLDALGO(0, "oldalgo"),
        NEWLGO(1, "newlgo");

        private final int value;
        private final String name;
        private final String fullName;
        private Autoinsurancebundle(final int value, final String name) {
            this.value = value;
            this.name = name;
            this.fullName = getTest().getName() + "-" + name;
        }

        @Override
        public Test getTest() {
            return Test.AUTOINSURANCEBUNDLE;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getFullName() {
            return fullName;
        }

        public static Autoinsurancebundle getFallback() {
            return Autoinsurancebundle.INACTIVE;
        }
    }

    public enum Autoinsurancebundleoffers implements Bucket<Test> {
        INACTIVE(-1, "inactive"),
        MXSTATEFARMAUTOINSINSURED(20, "mxstatefarmautoinsinsured"),
        CHAUTOINSURANCEBLENDED(23, "chautoinsuranceblended"),
        DLAUTOINSURANCEINSURED(24, "dlautoinsuranceinsured"),
        PRAUTOINSURANCE(87, "prautoinsurance"),
        MEDIAALPHAINSURED(112, "mediaalphainsured"),
        MEDIAALPHAUNINSURED(113, "mediaalphauninsured");

        private final int value;
        private final String name;
        private final String fullName;
        private Autoinsurancebundleoffers(final int value, final String name) {
            this.value = value;
            this.name = name;
            this.fullName = getTest().getName() + "-" + name;
        }

        @Override
        public Test getTest() {
            return Test.AUTOINSURANCEBUNDLEOFFERS;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getFullName() {
            return fullName;
        }

        public static Autoinsurancebundleoffers getFallback() {
            return Autoinsurancebundleoffers.INACTIVE;
        }
    }

    @Nonnull
    public Autoinsurancebundle getAutoinsurancebundle() {
        for (final Autoinsurancebundle bucket : Autoinsurancebundle.values()) {
            final String testName = Test.AUTOINSURANCEBUNDLE.getName();
            if (isBucketActive(testName, bucket.getValue(), -1)) {
                return bucket;
            }
        }

        // Safe to throw NPE here because the code generator ensures that the default value
        //  is a valid bucket in the test.
        throw new NullPointerException("No fallback bucket found for 'autoinsurancebundle'");
    }

    /**
      * @deprecated Use {@link #getAutoinsurancebundleValue()} instead
      */
    public int getAutoinsurancebundleValue(final int defaultValue) {
        return getValue(Test.AUTOINSURANCEBUNDLE.getName(), defaultValue);
    }

    public int getAutoinsurancebundleValue() {
        return getValue(Test.AUTOINSURANCEBUNDLE.getName(), Test.AUTOINSURANCEBUNDLE.getFallbackValue());
    }



    public boolean isAutoinsurancebundleInactive() {
        final String testName = Test.AUTOINSURANCEBUNDLE.getName();
        final int bucketValue = Autoinsurancebundle.INACTIVE.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleOldalgo() {
        final String testName = Test.AUTOINSURANCEBUNDLE.getName();
        final int bucketValue = Autoinsurancebundle.OLDALGO.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleNewlgo() {
        final String testName = Test.AUTOINSURANCEBUNDLE.getName();
        final int bucketValue = Autoinsurancebundle.NEWLGO.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    @Nonnull
    public Autoinsurancebundleoffers getAutoinsurancebundleoffers() {
        for (final Autoinsurancebundleoffers bucket : Autoinsurancebundleoffers.values()) {
            final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
            if (isBucketActive(testName, bucket.getValue(), -1)) {
                return bucket;
            }
        }

        // Safe to throw NPE here because the code generator ensures that the default value
        //  is a valid bucket in the test.
        throw new NullPointerException("No fallback bucket found for 'autoinsurancebundleoffers'");
    }

    /**
      * @deprecated Use {@link #getAutoinsurancebundleoffersValue()} instead
      */
    public int getAutoinsurancebundleoffersValue(final int defaultValue) {
        return getValue(Test.AUTOINSURANCEBUNDLEOFFERS.getName(), defaultValue);
    }

    public int getAutoinsurancebundleoffersValue() {
        return getValue(Test.AUTOINSURANCEBUNDLEOFFERS.getName(), Test.AUTOINSURANCEBUNDLEOFFERS.getFallbackValue());
    }



    public boolean isAutoinsurancebundleoffersInactive() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.INACTIVE.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleoffersMxstatefarmautoinsinsured() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.MXSTATEFARMAUTOINSINSURED.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleoffersChautoinsuranceblended() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.CHAUTOINSURANCEBLENDED.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleoffersDlautoinsuranceinsured() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.DLAUTOINSURANCEINSURED.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleoffersPrautoinsurance() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.PRAUTOINSURANCE.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleoffersMediaalphainsured() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.MEDIAALPHAINSURED.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isAutoinsurancebundleoffersMediaalphauninsured() {
        final String testName = Test.AUTOINSURANCEBUNDLEOFFERS.getName();
        final int bucketValue = Autoinsurancebundleoffers.MEDIAALPHAUNINSURED.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }
}

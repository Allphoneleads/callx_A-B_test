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
            case OLDAUTOINSURANCE:
                return Oldautoinsurance.values();
            case OLDAUTOINSURANCEOFFERS:
                return Oldautoinsuranceoffers.values();
        }
        return null;
    }

    public enum Test implements com.indeed.proctor.consumer.Test {
        OLDAUTOINSURANCE("oldautoinsurance", -1),
        OLDAUTOINSURANCEOFFERS("oldautoinsuranceoffers", -1);
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

    public enum Oldautoinsurance implements Bucket<Test> {
        INACTIVE(-1, "inactive"),
        OLDALGO(0, "oldalgo"),
        NEWLGO(1, "newlgo");

        private final int value;
        private final String name;
        private final String fullName;
        private Oldautoinsurance(final int value, final String name) {
            this.value = value;
            this.name = name;
            this.fullName = getTest().getName() + "-" + name;
        }

        @Override
        public Test getTest() {
            return Test.OLDAUTOINSURANCE;
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

        public static Oldautoinsurance getFallback() {
            return Oldautoinsurance.INACTIVE;
        }
    }

    public enum Oldautoinsuranceoffers implements Bucket<Test> {
        MXSTATEFARMAUTOINS(-1, "mxstatefarmautoins"),
        CALLXAUTOCSR(0, "callxautocsr"),
        CALLXAUTOCSRCOPY(1, "callxautocsrcopy");

        private final int value;
        private final String name;
        private final String fullName;
        private Oldautoinsuranceoffers(final int value, final String name) {
            this.value = value;
            this.name = name;
            this.fullName = getTest().getName() + "-" + name;
        }

        @Override
        public Test getTest() {
            return Test.OLDAUTOINSURANCEOFFERS;
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

        public static Oldautoinsuranceoffers getFallback() {
            return Oldautoinsuranceoffers.MXSTATEFARMAUTOINS;
        }
    }

    @Nonnull
    public Oldautoinsurance getOldautoinsurance() {
        for (final Oldautoinsurance bucket : Oldautoinsurance.values()) {
            final String testName = Test.OLDAUTOINSURANCE.getName();
            if (isBucketActive(testName, bucket.getValue(), -1)) {
                return bucket;
            }
        }

        // Safe to throw NPE here because the code generator ensures that the default value
        //  is a valid bucket in the test.
        throw new NullPointerException("No fallback bucket found for 'oldautoinsurance'");
    }

    /**
      * @deprecated Use {@link #getOldautoinsuranceValue()} instead
      */
    public int getOldautoinsuranceValue(final int defaultValue) {
        return getValue(Test.OLDAUTOINSURANCE.getName(), defaultValue);
    }

    public int getOldautoinsuranceValue() {
        return getValue(Test.OLDAUTOINSURANCE.getName(), Test.OLDAUTOINSURANCE.getFallbackValue());
    }



    public boolean isOldautoinsuranceInactive() {
        final String testName = Test.OLDAUTOINSURANCE.getName();
        final int bucketValue = Oldautoinsurance.INACTIVE.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isOldautoinsuranceOldalgo() {
        final String testName = Test.OLDAUTOINSURANCE.getName();
        final int bucketValue = Oldautoinsurance.OLDALGO.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isOldautoinsuranceNewlgo() {
        final String testName = Test.OLDAUTOINSURANCE.getName();
        final int bucketValue = Oldautoinsurance.NEWLGO.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    @Nonnull
    public Oldautoinsuranceoffers getOldautoinsuranceoffers() {
        for (final Oldautoinsuranceoffers bucket : Oldautoinsuranceoffers.values()) {
            final String testName = Test.OLDAUTOINSURANCEOFFERS.getName();
            if (isBucketActive(testName, bucket.getValue(), -1)) {
                return bucket;
            }
        }

        // Safe to throw NPE here because the code generator ensures that the default value
        //  is a valid bucket in the test.
        throw new NullPointerException("No fallback bucket found for 'oldautoinsuranceoffers'");
    }

    /**
      * @deprecated Use {@link #getOldautoinsuranceoffersValue()} instead
      */
    public int getOldautoinsuranceoffersValue(final int defaultValue) {
        return getValue(Test.OLDAUTOINSURANCEOFFERS.getName(), defaultValue);
    }

    public int getOldautoinsuranceoffersValue() {
        return getValue(Test.OLDAUTOINSURANCEOFFERS.getName(), Test.OLDAUTOINSURANCEOFFERS.getFallbackValue());
    }



    public boolean isOldautoinsuranceoffersMxstatefarmautoins() {
        final String testName = Test.OLDAUTOINSURANCEOFFERS.getName();
        final int bucketValue = Oldautoinsuranceoffers.MXSTATEFARMAUTOINS.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isOldautoinsuranceoffersCallxautocsr() {
        final String testName = Test.OLDAUTOINSURANCEOFFERS.getName();
        final int bucketValue = Oldautoinsuranceoffers.CALLXAUTOCSR.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }

    public boolean isOldautoinsuranceoffersCallxautocsrcopy() {
        final String testName = Test.OLDAUTOINSURANCEOFFERS.getName();
        final int bucketValue = Oldautoinsuranceoffers.CALLXAUTOCSRCOPY.getValue();
        return isBucketActive(testName, bucketValue, -1);
    }
}

package com.allphoneleads.api.proctor.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BucketsDef {
	
	int fallbackvalue;
	
	 Map<String, Integer> buckets = new LinkedHashMap<String, Integer>();

	public Map<String, Integer> getBuckets() {
		return buckets;
	}

	public void setBuckets(Map<String, Integer> buckets) {
		this.buckets = buckets;
	}

	public int getFallbackvalue() {
		return fallbackvalue;
	}

	public void setFallbackvalue(int fallbackvalue) {
		this.fallbackvalue = fallbackvalue;
	}

	
	
}

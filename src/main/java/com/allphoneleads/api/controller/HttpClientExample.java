package com.allphoneleads.api.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientExample {
	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpClientExample http = new HttpClientExample();


		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	
	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "http://chandan:ch1nd1n@52.24.56.190:8080/job/backend-dev-build/build";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
	//	System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
}

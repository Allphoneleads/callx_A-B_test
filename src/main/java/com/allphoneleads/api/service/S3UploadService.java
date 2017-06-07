package com.allphoneleads.api.service;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

@Service
public class S3UploadService {

	@Value("${APL.S3_BUCKET}")
	private String S3_BUCKET;

	@Value("${APL.CDN_URL}")
	private String CDN_URL;
	
	private static final String S3_PREFIX = "s3://";

	private static final Logger logger = LoggerFactory.getLogger(S3UploadService.class);

	public String uploadResource(String fileName, InputStream inputStream, long size, boolean isTemp, String contentType, String bucketName)
			throws IOException, InterruptedException {
		

		String tempPath = isTemp ? "tmp/" : "";
		DefaultAWSCredentialsProviderChain credentialProviderChain = new DefaultAWSCredentialsProviderChain();
		TransferManager tx = new TransferManager(credentialProviderChain.getCredentials());
		//
		final ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);
		objectMetadata.setContentType(contentType);

		PutObjectRequest request = new PutObjectRequest(bucketName, tempPath + fileName, inputStream,
				objectMetadata);

		request.setCannedAcl(CannedAccessControlList.PublicRead);// setAccessControlList(acl);

		Upload myUpload = tx.upload(request);

		myUpload.waitForCompletion();
		logger.debug("" + myUpload.isDone() + myUpload.getDescription()
				+ myUpload.getState().toString());
		tx.shutdownNow();

		/*
		 * AmazonS3Client client = new AmazonS3Client();
		 * //client.setEndpoint(CDN_URL);
		 * client.setRegion(Region.US_West.toAWSRegion());
		 * client.setBucketAcl(S3_BUCKET, CannedAccessControlList.PublicRead);
		 * //SetBucketCrossOriginConfigurationRequest
		 * setBucketCrossOriginConfigurationRequest = new
		 * SetBucketCrossOriginConfigurationRequest(S3_BUCKET,
		 * crossOriginConfiguration) //client.setBucketCrossOriginConfiguration(
		 * setBucketCrossOriginConfigurationRequest);
		 * client.putObject(S3_BUCKET, fileName, inputStream, objectMetadata);
		 */

		return CDN_URL + bucketName+ "/"+ tempPath + fileName;

	}

	public String uploadResource(String fileName, InputStream inputStream, long size)
			throws IOException, InterruptedException {
		return uploadResource(fileName, inputStream, size, false,"audio/mpeg", S3_BUCKET);
	}

}

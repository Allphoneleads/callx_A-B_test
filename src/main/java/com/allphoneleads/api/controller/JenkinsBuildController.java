package com.allphoneleads.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allphoneleads.api.service.DefinitionManager;

@RestController
public class JenkinsBuildController {
	
	@Autowired
	protected DefinitionManager definitionManager;	
	
	@RequestMapping(value = "/jenkins/job/build", method = RequestMethod.GET)
	public int jenkinsBuildJob(@RequestParam(value = "jobid", required = false) String jobID) {
		return definitionManager.jenkinsBuildJob(jobID);
		
	}
	
	
}

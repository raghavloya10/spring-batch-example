package io.rl.spring_batch.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.rl.spring_batch.service.StorageService;

@RestController
@RequestMapping()
public class LoadController {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	private StorageService storageService;
		
	@PostMapping("/upload")
	public BatchStatus upload(@RequestParam("file") MultipartFile file) throws JobExecutionAlreadyRunningException, 
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException 
	{
		
		Map<String, JobParameter> parameterMap = new HashMap<>();
		parameterMap.put("time", new JobParameter(System.currentTimeMillis()));
		this.storageService.uploadFile(file);
		JobParameters jobParameters = new JobParameters();
		JobExecution jobExecution = this.jobLauncher.run(this.job, jobParameters);
		System.out.println("JobExecution: "+jobExecution.getStatus());
		while(jobExecution.isRunning()) {
			System.out.println("Batch is running...");
		}
		return jobExecution.getStatus();		
	}

}

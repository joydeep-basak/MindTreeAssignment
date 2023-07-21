package com.mindtree.assignment.service.impl;

import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CrudServiceImpl {

	private RestTemplate restTemplateForCrudApp;
	
	@Async
	public Future<String> findAllStudentAsync(Map<String, String> contextMap) {
		MDC.setContextMap(contextMap);
		String fooResourceUrl
		  = "http://localhost:8080/students/";
		log.info("Starting call to find All student Async");
		HttpHeaders headers = new HttpHeaders();
		headers.set("calledid", MDC.get("calledid"));  
		 log.info("corellation id :: ", MDC.get("calledid"));
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		 
		ResponseEntity<String> response = restTemplateForCrudApp.exchange(fooResourceUrl, HttpMethod.GET, requestEntity, String.class);
		return new AsyncResult<String>(response.getBody());
	}
	
	public ResponseEntity<String> findAllStudent(Map<String, String> contextMap) {
		MDC.setContextMap(contextMap);
		String fooResourceUrl
		  = "http://localhost:8080/students/";
		log.info("Starting call to find All student ");
		ResponseEntity<String> response
		  = restTemplateForCrudApp.getForEntity(fooResourceUrl , String.class);
		return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
	}
	
	@Async
	public Future<String> findAllBookAsync(Map<String, String> contextMap) {
		MDC.setContextMap(contextMap);
		String fooResourceUrl
		  = "http://localhost:8080/books/";
		log.info("Starting call to find All Book Async ");
		HttpHeaders headers = new HttpHeaders();
		headers.set("calledid", MDC.get("calledid"));  
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		
		ResponseEntity<String> response = restTemplateForCrudApp.exchange(fooResourceUrl, HttpMethod.GET, requestEntity, String.class);
		return new AsyncResult<String>(response.getBody());
	}
}

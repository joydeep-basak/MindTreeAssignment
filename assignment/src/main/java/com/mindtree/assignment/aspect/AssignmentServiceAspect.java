package com.mindtree.assignment.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.mindtree.assignment.response.ApiError;
import com.mindtree.assignment.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class AssignmentServiceAspect {

	@Before("execution(* com.mindtree.assignment.controller.*.*(..))")
	public void before(JoinPoint joinPoint) {
		log.info("Execution started of Class :: {} Method :: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	}

	@After("execution(* com.mindtree.assignment.controller.*.*(..))")
	public void after(JoinPoint joinPoint) {
		log.info("Execution ended of Class :: {} Method :: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	}
	@AfterReturning("execution(* com.mindtree.assignment.controller.*.*(..))")
	public void afterReturning(JoinPoint joinPoint) {
		log.info("Execution ended of Class :: {} Method :: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	}

	@Around(value = "execution(* com.mindtree.assignment.controller.*.*(..))")
	public <T> ResponseEntity<ApiResponse> around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("{} Around with value {}", joinPoint);
		Object obj = joinPoint.proceed();
		ResponseEntity<T> response = (ResponseEntity<T>) obj;
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(response.getBody());
		apiResponse.setError(null);
		ResponseEntity<ApiResponse> newresponse = new ResponseEntity<ApiResponse>(apiResponse, response.getStatusCode());
		return newresponse;
	}
	
	@Around(value = "execution(* com.mindtree.assignment.controlleradvice.*.*(..))")
	public  <T> ResponseEntity<ApiResponse> aroundExceptionAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("{} Around with value {}", joinPoint);
		Object obj = joinPoint.proceed();
		ResponseEntity<T> response = (ResponseEntity<T>) obj;
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(null);
		T responseData = response.getBody();
		apiResponse.setError((ApiError) responseData);
		ResponseEntity<ApiResponse> newresponse = new ResponseEntity<ApiResponse>(apiResponse, response.getStatusCode());
		return newresponse;
	}
}
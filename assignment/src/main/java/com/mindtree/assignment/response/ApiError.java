package com.mindtree.assignment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiError {

	private String errorCode;
	
	private String errorMessage;
}

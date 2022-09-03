package com.mindtree.assignment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

	private T data;
	
	private ApiError error;
}

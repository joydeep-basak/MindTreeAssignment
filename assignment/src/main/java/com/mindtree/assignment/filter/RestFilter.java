package com.mindtree.assignment.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestFilter extends OncePerRequestFilter  {

//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		log.info("Intializing filter");
//	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return super.shouldNotFilter(request);
	}

	@Override
	protected boolean shouldNotFilterAsyncDispatch() {
		return super.shouldNotFilterAsyncDispatch();
	}

	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return super.shouldNotFilterErrorDispatch();
	}

	@Override
	public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		MDC.put("calledid", UUID.randomUUID().toString());
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		log.info("Token Filter pathInfo:" + request.getRequestURI());
		Iterator<String> hIteraator = request.getHeaderNames().asIterator();
		while (hIteraator.hasNext()) {
			String hName = hIteraator.next();
			log.info(String.format("Request Header Name :: [%s]  Request Header Value [%s]", hName, request.getHeader(hName)));
		};
		if (request.getContentLength() != -1) {
			byte[] content = new byte[request.getContentLength()];
			request.getInputStream().read(content);
			log.info("Request Body :: " + new String(content));
		}
		
		response.getHeaderNames().stream().forEach(headerName -> {
			log.info("Response Header Name :: [{}]  ResponseHeader Value [{}]", headerName, response.getHeader(headerName));
		});

		filterChain.doFilter(request, servletResponse); 
	}

	@Override
	public void destroy() {
	}
}
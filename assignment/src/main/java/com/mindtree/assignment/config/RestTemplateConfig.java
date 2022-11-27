package com.mindtree.assignment.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	 @Bean
	    public RestTemplate restTemplateForCrudApp() {
	        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	        connectionManager.setMaxTotal(100);
	        connectionManager.setDefaultMaxPerRoute(20);

	    RequestConfig requestConfig = RequestConfig
	        .custom()
	        .setConnectionRequestTimeout(5000) // timeout to get connection from pool
	        .setSocketTimeout(5000) // standard connection timeout
	        .setConnectTimeout(5000) // standard connection timeout
	        .build();

	    CloseableHttpClient httpClient = HttpClientBuilder.create()
	                                             .setConnectionManager(connectionManager)
	                                             .setDefaultRequestConfig(requestConfig).build();

	    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);


	    return new RestTemplate(requestFactory);
	}
}

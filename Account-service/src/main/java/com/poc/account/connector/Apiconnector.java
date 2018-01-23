package com.poc.account.connector;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.poc.account.model.Employee;

@Component
public class Apiconnector {

	@Autowired
	private LoadBalancerClient loadBalancer;

	public void getEmp() throws RestClientException {
		ServiceInstance serviceInstance = loadBalancer.choose("employee-service");
		System.out.println(serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/getallrecord";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}
	
	public Employee getEmp(int id) {
		ServiceInstance serviceInstance = loadBalancer.choose("employee-service");
		System.out.println(serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/getrecord/"+ id;
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> emp = (Map) restTemplate.getForObject(baseUrl, Object.class);
		System.out.println("emp "+emp);
	    return new Employee(emp.get("empId"), emp.get("name"), emp.get("designation"));
	
	}
	
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}

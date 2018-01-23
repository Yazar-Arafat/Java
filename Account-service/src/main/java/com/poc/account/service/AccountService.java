package com.poc.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poc.account.connector.Apiconnector;
import com.poc.account.model.Account;
import com.poc.account.model.Employee;

@Service
public class AccountService {

	@Autowired
	Apiconnector apiconnector;

	private static final Map<Integer, Account> AccMap = new HashMap<Integer, Account>();
	static {
		AccMap.put(1, new Account("emp1@gmail.com", "M", "TN", null));
		AccMap.put(2, new Account("emp2@gmail.com", "F", "KL", null));
		AccMap.put(3, new Account("emp3@gmail.com", "M", "TN", null));
		AccMap.put(4, new Account("emp4@gmail.com", "M", "KL", null));
		AccMap.put(5, new Account("emp5@gmail.com", "F", "TN", null));
	}

	public List<Account> getAccounts() {
		List<Account> acc = new ArrayList<Account>();
		acc.addAll(AccMap.values());
		return acc;

	}

	@HystrixCommand(fallbackMethod = "getAccountfallback")
	public Account getAccount(int id) {
		if (AccMap.containsKey(id)) {
			return AccMap.get(id);
		} else {
			throw new RuntimeException("no record");
		}
	}

	private Account getAccountfallback(int id) {
		return new Account("fallback-emailId", "fallback-gender", "fallback-loc", null);
	}

	@HystrixCommand(fallbackMethod = "getAccountWithEmpfallback")
	public Account getAccountWithEmp(int id) {
		Account acc = AccMap.get(id);
		Employee emp = apiconnector.getEmp(id);
		acc.setEmployee(emp);
		return acc;
	}

	private Account getAccountWithEmpfallback(int id) {
		Account acc = new Account("test-mailid", "test-gen", "test-loc", null);
		Employee emp = new Employee("test-1", "test-name", "test-dest");
		acc.setEmployee(emp);
		return acc;
	}

}

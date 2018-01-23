package com.poc.account.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.account.model.Account;
import com.poc.account.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/getaccounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getDetails() {
		System.out.println("get all accounts from account-service");
		List<Account> account = accountService.getAccounts();
		return account;
	}

	@RequestMapping(value = "/getaccount/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getAccount(@PathVariable("id") int id) {
		System.out.println("get particular record account  from account-service");
		Account account = accountService.getAccount(id);
		return account;
	}

	@RequestMapping(value = "/getaccwithemp/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account getAccountwithEmp(@PathVariable("id") int id) {
		System.out.println("Going to call Employee service to get data");
		Account account = accountService.getAccountWithEmp(id);
		return account;
	}

}

package com.shopapi.revature.dao;

import java.util.List;

import com.shopapi.revature.model.AccountCollection;

public interface PaymentDAO {
	
	public List<AccountCollection> viewAllPayment();

}

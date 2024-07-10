package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.example.demo.dao.IUdaPaymentsDao;
import com.example.demo.entity.UdaPayment;

@Configuration
public class UdaPaymentsDaoDummy implements IUdaPaymentsDao {

	public UdaPaymentsDaoDummy() {
	}
	
	/**
	 * 全件取得（ダミー）
	 * @return
	 */
	public List<UdaPayment> findAll() {
		List<UdaPayment> list = new ArrayList<>();
		UdaPayment udaPayment1 = new UdaPayment();
		udaPayment1.setId(1);
		udaPayment1.setDate("2024-06");
		udaPayment1.setName("食材");
		udaPayment1.setPrice(1200);
		udaPayment1.setType(1);
		
		UdaPayment udaPayment2 = new UdaPayment();
		udaPayment2.setId(2);
		udaPayment2.setDate("2024-07");
		udaPayment2.setName("旅費");
		udaPayment2.setPrice(10000);
		udaPayment2.setType(2);
		
		list.add(udaPayment1);
		list.add(udaPayment2);
		return list;
	}
}

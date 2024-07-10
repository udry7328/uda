package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.UdaPayment;

public interface IUdaPaymentsDao {
	List<UdaPayment> findAll();
}

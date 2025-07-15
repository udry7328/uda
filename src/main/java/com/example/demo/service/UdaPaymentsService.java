package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UdaPaymentsDao;
import com.example.demo.entity.UdaPayment;
import com.example.demo.util.CalendarUtil;

@Service
public class UdaPaymentsService {
	private UdaPaymentsDao dao;

	// 合計金額格納用
	private int totalPrice;

	public UdaPaymentsService(UdaPaymentsDao dao) {
		this.dao = dao;
	}

	// 全件検索
	public List<UdaPayment> findAll() {

		List<UdaPayment> list = dao.findAll();
		totalPrice = 0;
		for (UdaPayment payment : list) {
			// TODO 検討の余地あり
			totalPrice += payment.getPriceForDisplay();
		}
		return list;
	}

	/**
	 * 新規登録
	 * 
	 * @param payment
	 * @return
	 */
	public UdaPayment insertUdaPayment(UdaPayment payment) {
		dao.insertUdaPayment(payment);
		return payment;
	}

	/**
	 * 年月指定で取得
	 * @param year
	 * @param month
	 * @return
	 */
	public List<UdaPayment> findUdaPayementsByYearAndMonth(String year, String month) {

		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month);

		// 開始日は、前月の給料日
		String startDate = year + "-" + String.valueOf(monthInt - 1) + "-" + calcSararyDay(yearInt, monthInt - 1);
		// 終了日は、当月の給料日前日
		String endDate = year + "-" + month + "-" + (calcSararyDay(yearInt, monthInt) - 1);

		List<UdaPayment> list = dao.findUdaPaymentsByYearAndMonth(startDate, endDate);
		totalPrice = 0;
		for (UdaPayment payment : list) {
			totalPrice += payment.getPriceForDisplay();
		}

		return list;

	}
	
	/**
	 * ID指定で取得
	 * @param id
	 * @return
	 */
	public UdaPayment findUdaPaymentById(int id) {
		return dao.findUdaPaymentById(id);
	}
	
	public int deleteUdaPayment(int id) {
		dao.deleteUdaPayment(id);
		return id;
	}

	// TODO 祝日の考慮が未実装 CalendarUtil.isHoliday
	/**
	 * 給料日を算出する（土日を考慮する）
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int calcSararyDay(int year, int month) {
		for (int i = 25; i > 0; i--) {
			if (!CalendarUtil.isHoliday(year, month, i) && !CalendarUtil.isNationalHoliday(year, month, i)) {
				return i;
			}
		}

		return 0;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}

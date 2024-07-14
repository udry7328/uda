package com.example.demo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UdaPayment;
import com.example.demo.enumeration.EBudgetDetailType;

@Repository
public class UdaPaymentsDao {
	private final JdbcTemplate jdbcTemplate;
	// TODO この辺のSQLの管理は、別途プロパティファイルとかに切り出したい。
	// TODO カラムの増減があったとき、修正が大変。何とかしたい。
	private final String SELECT = "SELECT id, date, budget_type, budget_detail_type, name, price FROM uda_payment ";
	private final String INSERT = "insert into uda_payment (date, budget_type, budget_detail_type, name, price) values (?,?,?,?,?)";
	private final String DELETE = "delete from uda_payment ";

	public UdaPaymentsDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 全件検索処理
	public List<UdaPayment> findAll() {

		String sql = SELECT + "order by date, id";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

		return createUdaPayments(resultList);
	}

	/**
	 * 新規登録
	 * 
	 * @param payment
	 * @return
	 */
	public void insertUdaPayment(UdaPayment payment) {
		// TODO カラムの増減があったとき、修正が大変。何とかしたい。
		jdbcTemplate.update(INSERT, payment.getDate(), payment.getBudgetType(),
				EBudgetDetailType.toType(payment.getBudgetDetailType()).toString(), payment.getName(),
				payment.getPrice());

	}

	public List<UdaPayment> findUdaPaymentsByYearAndMonth(String startDate, String endDate) {

		String sql = SELECT + "where date between ? and ? order by date, id";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, startDate, endDate);

		return createUdaPayments(resultList);
	}

	public UdaPayment findUdaPaymentById(int id) {
		String sql = SELECT + "where id = ? order by date, id";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		return createUdaPayment(result);
	}

	/**
	 * 削除
	 * 
	 * @param id
	 * @return
	 */
	public void deleteUdaPayment(int id) {
		jdbcTemplate.update(DELETE + "where id = ?", id);
	}

	/**
	 * UdaPaymentのリストを作成
	 * 
	 * @param resultList
	 * @return
	 */
	private List<UdaPayment> createUdaPayments(List<Map<String, Object>> resultList) {
		List<UdaPayment> list = new ArrayList<>();

		for (Map<String, Object> result : resultList) {

			list.add(createUdaPayment(result));
		}
		return list;
	}

	/**
	 * UdaPaymentを作成
	 * 
	 * @param result
	 * @return
	 */
	private UdaPayment createUdaPayment(Map<String, Object> result) {
		// TODO カラムの増減があったとき、修正が大変。何とかしたい。
		UdaPayment payment = new UdaPayment();
		payment.setId((int) result.get("id"));
		Date date = (Date) result.get("date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		payment.setDate(strDate);
		payment.setBudgetType((String) result.get("budget_type"));
		String budgetDetailType = (String) result.get("budget_detail_type");
		payment.setBudgetDetailType(EBudgetDetailType.ToDisplay(budgetDetailType));
		payment.setName((String) result.get("name"));
		payment.setPrice((int) result.get("price"));
		return payment;
	}
}

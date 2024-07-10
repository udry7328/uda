package com.example.demo.entity;

public class UdaPayment {
	/**
	 * 識別ID
	 */
	int id;

	/**
	 * 対象年月
	 */
	private String date;

	// TODO ENUMにしても良いかも
	/**
	 * 収支種別
	 */
	private String budgetType;

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	// TODO 種別はENUMにしたい。
	/**
	 * 種別
	 */
	private int type;

	// TODO これ不要かも。種別さえあれば。⇒代わりに、この項目を備考的なものにするか。
	/**
	 * 品名
	 */
	private String name;

	/**
	 * 金額
	 */
	private int price;

	/**
	 * コンストラクタ
	 * 
	 * @param date
	 * @param type
	 * @param name
	 * @param price
	 */
	public UdaPayment(String date, int type, String name, int price) {
		this.date = date;
		this.type = type;
		this.name = name;
		this.price = price;
	}

	public UdaPayment() {

	}

	// TODO getter,setterはどうにかしたい
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		// TODO budgetTypeをEnumにして、もうちょっとうまく実装したいような....。
		// 支出ならマイナス値にする
		return budgetType.equals("支出") ? price - price * 2 : price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

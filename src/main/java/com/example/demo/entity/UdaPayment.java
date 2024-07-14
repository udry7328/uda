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

	/**
	 * 収支明細種別
	 */
	private String budgetDetailType;

	public String getBudgetDetailType() {
		return budgetDetailType;
	}

	public void setBudgetDetailType(String budgetDetailType) {
		this.budgetDetailType = budgetDetailType;
	}

	// TODO 変数名変えたいな。DBも。
	/**
	 * 補足
	 */
	private String name;

	/**
	 * 金額
	 */
	private int price;

	/**
	 * コンストラクタ
	 * @param date
	 * @param budgetDetailType
	 * @param name
	 * @param price
	 */
	public UdaPayment(String date, String budgetDetailType, String name, int price) {
		this.date = date;
		this.budgetDetailType = budgetDetailType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		// TODO 支出の場合、DB値は正の値、画面表示値は負の値 という実装にしたい。
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * 支出ならマイナス
	 */
	public int getPriceForDisplay() {
		// TODO service.findAll 向けにとりあえず作ったメソッドだが、もうちょっとうまく実装したい
		return budgetType.equals("支出") ? price - price * 2 : price;
	}

}

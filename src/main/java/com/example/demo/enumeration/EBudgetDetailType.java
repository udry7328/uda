package com.example.demo.enumeration;

public enum EBudgetDetailType {
	SALARY("給与"),
	BONUS("賞与"),
	FOOD("食費"),
	DAILY_NECESSITIES("日用品"),
	TRANSPORTATION("交通費"),
	HOBBY("趣味"),
	FASSION("衣服/美容"),
	ENTERTAINMENT("交際費"),
	HEALTH("健康/医療"),
	EDUCATION("教育/教養"),
	WATER_SERVICE("水道/光熱費"),
	COMMUNICATION("通信費"),
	LOAN("住宅ローン"),
	CAR("車"),
	TAX("税/社会保障"),
	INSURANCE("保険"),
	ASSET_FORMATION("資産形成"),
	OTHER("その他");

	private String typeForDisplay;

	private EBudgetDetailType(String typeForDisplay) {
		this.typeForDisplay = typeForDisplay;
	}

	/**
	 * 画面表示値→プロパティ名への変換
	 * @param typeForDisplay
	 * @return
	 */
	public static EBudgetDetailType toType(String typeForDisplay) {
		for (EBudgetDetailType value : EBudgetDetailType.values()) {
			if (value.typeForDisplay.equals(typeForDisplay)) {
				return value;
			}
		}
		return null;
	}
	
	/**
	 * プロパティ名→画面表示用の値へ変換（例：SALARY→給与）
	 * @param budgetDetailType
	 * @return
	 */
	public static String ToDisplay(String budgetDetailType) {
		for (EBudgetDetailType value : EBudgetDetailType.values()) {
			if (value.toString().equals(budgetDetailType)) {
				return value.typeForDisplay;
			}
		}
		return null;
	}
}

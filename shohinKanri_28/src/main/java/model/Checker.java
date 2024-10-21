package model;
/*
 * 入力値チェッククラス
 */
public class Checker {

	/* 
	 * エラーメッセージをまとめるクラス 
	 */
	public String allCheck(String productId, String productName, String productGroup, String stSalesPrice,
			String stUnitPrice) {
		String msg = "";

		msg += nullCheck(productId, productName, productGroup);
		msg += priceCheck(stSalesPrice,stUnitPrice);

		//IDの文字数チェック
		if (productId != null && productId.length() > 4) {
			msg += "商品IDは4文字以下で入力してください。<br>";
		}
		return msg;
	}
	
	/* 
	 * null値チェック 
	 */
	public String nullCheck(String productId, String productName, String productGroup) {
		String msg = "";

		//nullの場合msgの更新
		if (productId == null || productId.length() == 0) {
			msg += "商品IDは必須項目です。<br>";
		}
		if (productName == null || productName.length() == 0) {
			msg += "商品名は必須項目です。<br>";
		}
		if (productGroup == null || productGroup.length() == 0) {
			msg += "商品分類は必須項目です。<br>";
		}
		
		return msg;
	}

	/* 
	 * 整数チェック 
	 */
	public String priceCheck(String stSalesPrice,String stUnitPrice) {
		String msg = "";
		
		//整数以外の文字が入った時にmsgを更新
		if (stSalesPrice != null && stSalesPrice.length() != 0) {
			try {
				Integer.parseInt(stSalesPrice);
			} catch (NumberFormatException e) {
				msg += "販売単価には整数を入力してください。<br>";
			}
		}
		if (stUnitPrice != null && stUnitPrice.length() != 0) {
			try {
				Integer.parseInt(stUnitPrice);
			} catch (NumberFormatException e) {
				msg += "仕入単価には整数を入力してください。<br>";
			}
		}
		return msg;
	}

	/*
	 * nullの場合単価に0を返すメソッド
	 */
	public int salesPriceCheck(String stSalesPrice) {
		int salesPrice = 0;

		if (stSalesPrice != null && stSalesPrice.length() != 0) {
			salesPrice = Integer.parseInt(stSalesPrice);
		} 
		return salesPrice;
	}
	public int unitPriceCheck(String stUnitPrice) {
		int unitPrice = 0;

		if (stUnitPrice != null && stUnitPrice.length() != 0) {
			unitPrice = Integer.parseInt(stUnitPrice);
		} 
		
		return unitPrice;
	}
}

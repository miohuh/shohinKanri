package model;

import java.util.List;

/*
 * サーブレットとDAOを繋げるクラス
 */

public class ShohinLogic {

	/*
	 * 商品検索メソッド
	 */
	public List<Shohin> search(String productName,String productGroup){
		ShohinDAO dao = new ShohinDAO();
		List<Shohin> shohinList = dao.searchData(productName,productGroup);
		return shohinList;
	}
	
	/*
	 * 商品削除メソッド
	 */
	public boolean delete(Shohin shohin) {
		ShohinDAO dao = new ShohinDAO();		 
		 return dao.deleteData(shohin);
	}
	
	/*
	 * 商品登録メソッド
	 */
	public boolean input(Shohin shohin) {
		ShohinDAO dao = new ShohinDAO();
		return dao.inputData(shohin);
	}
	
	/*
	 * 商品更新メソッド
	 */
	public boolean update(Shohin shohin) {
		ShohinDAO dao = new ShohinDAO();
		return dao.updateData(shohin);
	}
	
	/*
	 * CSV出力メソッド
	 */
	public String writeData() {

		ShohinDAO dao = new ShohinDAO();
		List<Shohin> shohinList = dao.searchData("","");
		
		StringBuilder sb = new StringBuilder();
		sb.append("商品ID,商品名,商品分類,販売単価,仕入単価");

		for (Shohin s : shohinList) {
			sb.append("\n");
			sb.append(s.getProductId());
			sb.append(",");
			sb.append(s.getProductName());
			sb.append(",");
			sb.append(s.getProductGroup());
			sb.append(",");
			sb.append(s.getSalesPrice());
			sb.append(",");
			sb.append(s.getUnitPrice());
		}
		return sb.toString();
	}
}

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * データベース接続クラス
 */
public class ShohinDAO {

	private final String URL = "jdbc:postgresql://localhost:5432/shop";
	private final String USER = "postgres";
	private final String PASS = "test";

	public ShohinDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 商品追加メソッド
	 */
	public boolean inputData(Shohin shohin) {
		String sql = "INSERT INTO Shohin VALUES (?, ?, ?, ?, ?, CURRENT_DATE);";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, shohin.getProductId());
			st.setString(2, shohin.getProductName());
			st.setString(3, shohin.getProductGroup());
			st.setInt(4, shohin.getSalesPrice());
			st.setInt(5, shohin.getUnitPrice());

			int input = st.executeUpdate();
			
			//1件の登録ができたときtrueを返す
			if(input == 1) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("DB接続時にエラー発生");
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 商品検索メソッド
	 */
	public List<Shohin> searchData(String productName, String productGroup) {
		String sql = "";
		int number;	//分岐する為の変数
		
		//引数をもとにSQLの組み立て
		if (productName.length() == 0 && productGroup.length() == 0) {
			sql = "SELECT * FROM shohin ORDER BY shohin_id;";
			number = 0;
		} else if (productName.length() == 0 && productGroup.length() != 0) {
			sql = "SELECT * FROM shohin WHERE shohin_bunrui = ? ORDER BY shohin_id;";
			number = 1;
		} else if (productName.length() != 0 && productGroup.length() == 0) {
			sql = "SELECT * FROM shohin WHERE shohin_mei LIKE ? ORDER BY shohin_id;";
			number = 2;
		} else {
			sql = "SELECT * FROM shohin ";
			sql += "WHERE shohin_mei LIKE ? AND shohin_bunrui = ? ORDER BY shohin_id;";
			number = 3;
		}
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement st = con.prepareStatement(sql);) {
			
			//numberをもとに引数をSQLへ挿入
			switch(number) {
			case 0:
				break;
			case 1:
				st.setString(1,productGroup);
				break;
			case 2:
				st.setString(1, "%" + productName + "%");
				break;
			case 3:
				st.setString(1, "%" + productName + "%");
				st.setString(2,productGroup);
				break;
			}

			//SQLの実行
			ResultSet rs = st.executeQuery();
			List<Shohin> shohinList = insertList(rs);

			return shohinList;

		} catch (Exception e) {
			System.out.println("DB接続時にエラー発生");
			e.printStackTrace();

			return null;
		}
	}

	/*
	 * 商品削除メソッド
	 */
	public boolean deleteData(Shohin shohin) {
		String sql = "DELETE FROM shohin WHERE shohin_id = ?;";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, shohin.getProductId());
			int del = st.executeUpdate();

			if (del == 1) {
				return true;
			}

			return false;

		} catch (Exception e) {
			System.out.println("DB接続時にエラー発生");
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 検索結果をShohinフィールド→リストへ格納するメソッド
	 */
	public List<Shohin> insertList(ResultSet rs) throws Exception {
		List<Shohin> shohinList = new ArrayList<>();

		while (rs.next()) {
			String id = rs.getString("shohin_id");
			String name = rs.getString("shohin_mei");
			String group = rs.getString("shohin_bunrui");
			int salesPrice = rs.getInt("hanbai_tanka");
			int unitPrice = rs.getInt("shiire_tanka");

			Shohin shohin = new Shohin(id, name, group, salesPrice, unitPrice);
			shohinList.add(shohin);
		}
		return shohinList;
	}
	
	/*
	 * 商品更新メソッド
	 */
	public boolean updateData(Shohin shohin) {
		String sql = "UPDATE shohin ";
		sql +="SET shohin_id = ?, shohin_mei = ?, shohin_bunrui = ?, ";
		sql +="hanbai_tanka = ?, shiire_tanka = ?, torokubi = CURRENT_DATE WHERE shohin_id = ?;";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, shohin.getProductId());
			st.setString(2, shohin.getProductName());
			st.setString(3, shohin.getProductGroup());
			st.setInt(4, shohin.getSalesPrice());
			st.setInt(5, shohin.getUnitPrice());
			st.setString(6, shohin.getProductId());
			
			int del = st.executeUpdate();

			if (del == 1) {
				return true;
			}

			return false;

		} catch (Exception e) {
			System.out.println("DB接続時にエラー発生");
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}

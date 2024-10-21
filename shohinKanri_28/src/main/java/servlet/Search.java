package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Shohin;
import model.ShohinLogic;

/*
 * 検索サーブレット
 */

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String productGroup = request.getParameter("productGroup");
		String productName = request.getParameter("productName");
		
		//ドロップダウンの値を任意の文字列へ変更
		if(productGroup.equals("0")) {
			productGroup = "";
		}else if(productGroup.equals("1")) {
			productGroup = "衣服";
		}else if(productGroup.equals("2")) {
			productGroup = "キッチン用品";
		}else if(productGroup.equals("3")) {
			productGroup = "事務用品";
		}
		
		//検索処理
		ShohinLogic logic = new ShohinLogic();
		List<Shohin> shohinList = logic.search(productName, productGroup);
		
		//検索したリストをスコープへ登録
		HttpSession session = request.getSession();
		session.setAttribute("shohinList", shohinList);
		
		//検索項目を表示する為のスコープ
		session.setAttribute("findShohinName", productName);
		session.setAttribute("findShohinGroup", productGroup);
		
		
		//index.jspへ返す
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request,response);
	}

}

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Checker;
import model.Shohin;
import model.ShohinLogic;

/*
 * 追加サーブレット
 */

@WebServlet("/Input")
public class Input extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/*
	 * 追加画面へ移動
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/inputShohin.jsp");
		dispatcher.forward(request, response);
	}
	
	/*
	 * 追加処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String productGroup = request.getParameter("productGroup");
		String stSalesPrice = request.getParameter("salesPrice");
		String stUnitPrice = request.getParameter("unitPrice");
		
		String msg = "";
		
		//入力値チェック
		Checker checker = new Checker();
		msg += checker.allCheck(productId, productName, productGroup, stSalesPrice, stUnitPrice);
		
		//msgが空の時、追加処理が行われる
		if (msg.isEmpty()) {
			int salesPrice = checker.salesPriceCheck(stSalesPrice);
			int unitPrice = checker.unitPriceCheck(stUnitPrice);
			
			
			Shohin shohin = new Shohin(productId,productName,productGroup,salesPrice,unitPrice);
			ShohinLogic logic = new ShohinLogic();
			
			boolean finalCheck = logic.input(shohin);
			
			if (finalCheck) {
				msg += "登録が完了しました。";
			}else {
				msg += "登録処理時に問題が発生しました。";
			}
		}
		
		request.setAttribute("msg", msg);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/inputShohin.jsp");
		dispatcher.forward(request, response);
	}
}

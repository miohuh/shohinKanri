package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Checker;
import model.Shohin;
import model.ShohinLogic;
/*
 * 更新処理サーブレット
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 更新処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//入力値の取得
		request.setCharacterEncoding("UTF-8");
		String updateId = request.getParameter("updateId");
		String updateName = request.getParameter("updateName");
		String updateGroup = request.getParameter("updateGroup");
		String updateSalesPrice = request.getParameter("updateSalesPrice");
		String updateUnitPrice = request.getParameter("updateUnitPrice");
	
		//入力値チェック
		Checker checker = new Checker();
		String msg = checker.allCheck(updateId,updateName,updateGroup,updateSalesPrice,updateUnitPrice);
		
		if (msg.isEmpty()) {
			//int型へ変換
			int salesPrice = checker.salesPriceCheck(updateSalesPrice);
			int unitPrice = checker.unitPriceCheck(updateUnitPrice);
			
			//更新処理
			Shohin shohin = new Shohin(updateId,updateName,updateGroup,salesPrice,unitPrice);
			ShohinLogic logic = new ShohinLogic();
			boolean finalCheck = logic.update(shohin);
			
			if (finalCheck) {
				request.setAttribute("msg","更新が完了しました");
				//セッションスコープの上書き
				HttpSession session = request.getSession();
				session.setAttribute("shohin",shohin);
			}
			
		}else {
			request.setAttribute("msg", msg);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateShohin.jsp");
		dispatcher.forward(request, response);
		
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * 更新分岐・CSVダウンロード処理・削除処理サーブレット
 */

@WebServlet("/Split")
public class Split extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String submit = request.getParameter("submit");

		//次のページへのパス情報
		String path = "";

		/*
		 * 分岐
		 */
		if (submit.equals("CSV全件出力")) {
			/* CSVダウンロード */
			ShohinLogic logic = new ShohinLogic();
			String data = logic.writeData();
			
			response.setContentType("text/csv");
	        response.setHeader("Content-Disposition", "attachment; filename=\"shohin.csv\"");
	        response.setCharacterEncoding("UTF-8");
	        
	        try (PrintWriter writer = response.getWriter()) {
	            writer.write(data);
	        }catch(Exception e) {
	        	System.out.println("ダウンロード時にエラー");
	        	e.printStackTrace();
	        }
			
			path = "index.jsp";
			
		} else {
			
			//ラジオボタンから選択されたshohinListの要素番号の取り出し
			String shohinIndex = request.getParameter("shohinIndex"); 

			if (shohinIndex != null && shohinIndex.length() != 0) { 
				
				//セッションスコープからリストの取り出し
				HttpSession session = request.getSession();
				List<Shohin> shohinList = (List<Shohin>) session.getAttribute("shohinList");

				int index = Integer.parseInt(shohinIndex); //商品リストの要素番号

				//リストから指定されたShohinインスタンスを取得
				Shohin shohin = shohinList.get(index);
			
				//削除処理
				if (submit.equals("削除")) {
				
					if (shohinIndex != null || shohinList != null) {
						ShohinLogic logic = new ShohinLogic();
						boolean conf = logic.delete(shohin);

						if (conf) {
							request.setAttribute("msg", "削除が完了しました");
						} else {
							request.setAttribute("msg", "削除ができませんでした");
						}
					}
					path = "index.jsp";
				}
				
				//更新
				if (submit.equals("更新")) {
					session.setAttribute("shohin", shohin);
					path = "WEB-INF/jsp/updateShohin.jsp";
				}
				
			}else {
				path = "index.jsp";
				request.setAttribute("msg", "選択してください");
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}

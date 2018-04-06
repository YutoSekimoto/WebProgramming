package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

		 //リクエストパラーメーター格納
		String loginId = request.getParameter("loginid");
        out.println(loginId);

        //リクエストスコープにログインID文字列を保存
        request.setAttribute("loginId", loginId);

        //フォワード(ユーザ一覧ページ)
      	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
      	dispatcher.forward(request, response);
      	return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

		//リクエストパラーメーター格納
		String loginId = request.getParameter("loginid");
		out.println(loginId);

		//データベース設定
		Connection conn = null;

				try {

					// データベースへ接続
		            conn = DBManager.getConnection();

		            //SELECT文の準備
		            String sql = " delete from user where login_id = ? ";
		            //PreparedStatementの準備
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            //バインドパラメータにバインド]
		            pstmt.setString(1,loginId);
		            //SELECT文を実行
		            int result = pstmt.executeUpdate();

		            //SELECT文実行確認用
		            out.println("データベース実行");
		            out.println(result);

			    } catch (SQLException e) {

			    	  e.printStackTrace();
					  return;

				  } finally {

					  // データベース切断
					  if (conn != null) {
						  try {
							  conn.close();
					      } catch (SQLException e) {
					    	  e.printStackTrace();
					          return;
					      }
					  }

				  }

				//サーブレットにリダイレクト(UserListServlet)
				response.sendRedirect("UserListServlet");
				return;

	}

}

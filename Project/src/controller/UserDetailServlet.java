package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import model.User;

/**
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
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

		String loginId = request.getParameter("loginid");
        out.println(loginId);

		//データベース設定
		Connection conn = null;
		try {
			// データベースへ接続
            conn = DBManager.getConnection();

            //SELECT文の準備
            String sql = " select * from user where login_id = ? ";
            //PreparedStatementの準備
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //バインドパラメータにバインド
            pstmt.setString(1,loginId);
            //SELECT文を実行
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

            	int id = rs.getInt("id");
            	String lId = rs.getString("login_id");
            	String name = rs.getString("name");
            	Date bDate = rs.getDate("birth_date");
            	String password = rs.getString("password");
            	String cDate = rs.getString("create_date");
            	String uDate = rs.getString("update_date");

            	//実行確認
            	out.println("実行");
            	out.println(name);

            	//リクエストスコープに保存するインスタンス
            	User user = new User(id , lId , name , bDate , password , cDate , uDate);

            	//リクエストスコープにインスタンスを保存
            	request.setAttribute("user", user);

            }


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

		//フォワード(ユーザ一覧ページ)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
		dispatcher.forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);



	}

}

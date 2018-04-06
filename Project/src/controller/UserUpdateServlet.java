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

import common.Comonn;
import dao.DBManager;
import model.User;

/**
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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

            	//実行確認用
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
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

	    //リクエストパラメーターの文字コードを指定
	    request.setCharacterEncoding("UTF-8");

	    //リクエストパラーメーター格納
	    String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String userName = request.getParameter("userName");
		String birthDate = request.getParameter("birthDate");

		//リクエストパラメーター確認用
		out.println(loginId);
		out.println(password);
		out.println(password2);
		out.println(userName);
		out.println(birthDate);

		//入力項目の有無の確認
	    if(userName == "" || birthDate == "") {

	    	//リクエストにエラーメッセージをセット
			request.setAttribute("eM", "未入力の項目があります");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;


	    }

	    //パスワードとパスワード確認の比較
	    if(!(password.equals(password2))) {

	    	//リクエストにエラーメッセージをセット
			request.setAttribute("eM", "パスワードが異なっています");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;

	    }

		//データベース設定
		Connection conn = null;

		try {

			// データベースへ接続
            conn = DBManager.getConnection();


            if(password == "" && password2 == "") {

            	//UPDATE文の準備
                String sql = " update user set name = ? , birth_date = ? where login_id = ? ";
                //PreparedStatementの準備
                PreparedStatement pstmt = conn.prepareStatement(sql);
                //バインドパラメータにバインド]
                pstmt.setString(1,userName);
                pstmt.setString(2,birthDate);
                pstmt.setString(3,loginId);
                //UPDATE文を実行
                int result = pstmt.executeUpdate();

                //UPDATE文実行確認用
                out.println("データベース実行");
                out.println(result);

                //サーブレットにリダイレクト(UserListServlet)
        		response.sendRedirect("UserListServlet");
        		return;

            }

            //UPDATE文の準備
            String sql = " update user set name = ? , password = ? , birth_date = ? where login_id = ? ";
            //PreparedStatementの準備
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //バインドパラメータにバインド]
            pstmt.setString(1,userName);
            pstmt.setString(2,Comonn.convertPassword(password));
            pstmt.setString(3,birthDate);
            pstmt.setString(4,loginId);
            //UPDATE文を実行
            int result = pstmt.executeUpdate();

            //UPDATE文実行確認用
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

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class UserTourokuServlet
 */
@WebServlet("/UserTourokuServlet")
public class UserTourokuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTourokuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//フォワード(ユーザ新規登録)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
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

		//リクエストパラメータの取得
		String loginId = request.getParameter("loginid");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		//日付パラメーター
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date);

		//リクエストパラメーター確認用
		out.println(loginId);
	    out.println(password);
	    out.println(password2);
	    out.println(name);
	    out.println(birthDate);

	    //入力項目の有無の確認
	    if(loginId == "" || password == "" || password2 == "" || name == "" || birthDate == "") {

	    	//リクエストにエラーメッセージをセット
			request.setAttribute("eM", "未入力の項目があります");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
			dispatcher.forward(request, response);
			return;


	    }

	    //パスワードとパスワード確認の比較
	    if(!(password.equals(password2))) {

	    	//リクエストにエラーメッセージをセット
			request.setAttribute("eM", "パスワードが異なっています");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
			dispatcher.forward(request, response);
			return;

	    }

	    //JavaBeansにログインID、パスワードをセット
	    UserDao userDao = new UserDao();
	  	String strLogin = userDao.upDate(loginId, password,password2,name,birthDate);
	  	if(strLogin == "") {


	  	//リクエストにエラーメッセージをセット
		request.setAttribute("eM", "入力したログインIDは既に使用されています");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
			dispatcher.forward(request, response);
			return;

	  	}

	    /*
		//データベース設定
		Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            //既存のログインIDの確認
            //SELECT文の準備
            String sql1=" select * from user where login_id = ? ";
            //PreparedStatementの準備
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            //バインドパラメータにバインド
            pstmt1.setString(1,loginId);
            //SELECT文を実行
            ResultSet rs = pstmt1.executeQuery();

            while(rs.next()) {

            	//リクエストにエラーメッセージをセット
    			request.setAttribute("eM", "入力したログインIDは既に使用されています");

    			//フォワード(ユーザ新規登録)
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
    			dispatcher.forward(request, response);
    			return;

            }

            //INSERT文の準備
            String sql=" insert into user (login_id,name,birth_date,password,create_date,update_date) values (?,?,?,?,?,?)";
            //PreparedStatementの準備
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //バインドパラメータにバインド
            pstmt.setString(1,loginId);
            pstmt.setString(2,name);
            pstmt.setString(3,birthDate);
            pstmt.setString(4,password);
            pstmt.setString(5,dateStr);
            pstmt.setString(6,dateStr);
            //INSERT文を実行
            int num = pstmt.executeUpdate();


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

*/
        //サーブレットにリダイレクト(UserListServlet)
		response.sendRedirect("UserListServlet");
		return;


	}

}

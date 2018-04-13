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
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

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

		//HttpSessionインスタンスの取得
		HttpSession session = request.getSession();
		//セッションスコープを取得
		User userName = (User) session.getAttribute("userName");

		//セッションスコープの有無の確認
		if(userName == null) {

			//サーブレットにリダイレクト(UserListServlet)
			response.sendRedirect("UserLoginServlet");
			return;

		}

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

		//レスポンス
		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

		//リクエストパラメーターの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの取得
		String loginId = request.getParameter("loginid");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String bDate = request.getParameter("birthDate");

		//日付パラメーター
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date);
		String cDate = dateStr;
		String uDate = dateStr;

		//リクエストパラメーター確認用
		out.println(loginId);
	    out.println(password);
	    out.println(password2);
	    out.println(name);
	    out.println(bDate);

	    //入力項目の有無の確認
	    if(loginId == "" || password == "" || password2 == "" || name == "" || bDate == "") {

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
	  	String eM = userDao.userTouroku(loginId , name , bDate , password , cDate , uDate);

	  	if(eM.equals("失敗")) {

        	    //リクエストにエラーメッセージをセット
			request.setAttribute("eM", "入力したログインIDは既に使用されています");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
			dispatcher.forward(request, response);
			return;

        }

        //サーブレットにリダイレクト(UserListServlet)
		response.sendRedirect("UserListServlet");
		return;


	}

}

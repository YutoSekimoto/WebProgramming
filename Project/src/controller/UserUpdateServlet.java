package controller;

import java.io.IOException;
import java.io.PrintWriter;

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

		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    //リクエストパラーメーター格納
		String loginId = request.getParameter("loginid");
        out.println(loginId);

        UserDao userDao = new UserDao();
        User user = userDao.userDetail(loginId);

        //リクエストスコープをセット
        request.setAttribute("user", user);

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

	    UserDao userDao = new UserDao();
	    String message = userDao.userUpdate(loginId , password , password2 , userName , birthDate);

	    //更新に失敗した場合
	    if(message == null) {

	     	request.setAttribute("eM", "更新に失敗しました");

			//フォワード(ユーザ新規登録)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;

	    }

		//サーブレットにリダイレクト(UserListServlet)
		response.sendRedirect("UserListServlet");
		return;

	}

}

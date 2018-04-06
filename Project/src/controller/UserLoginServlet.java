package controller;

import java.io.IOException;

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
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
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
		//セッションスコープからインスタンスを取得
		User userName = (User) session.getAttribute("userName");
		//セッションがあればユーザ一覧画面へ
		if(userName != null){

			//サーブレットにリダイレクト(UserListServlet)
			response.sendRedirect("UserListServlet");
			return;

		}

		//フォワード(ログインページ)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//リクエストパラメーターの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータを取得
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");

		//JavaBeansにログインID、パスワードをセット
		UserDao userDao = new UserDao();
		User user = userDao.findByLoginInfo(loginID, password);

		//JavaBeansセット確認

		if(user == null) {

			//リクエストにエラーメッセージをセット
			request.setAttribute("eM", "ログインIDまたはパスワードが異なります");

			//フォワード(ログインページ)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;

		}

		//HttpSessionインスタンスの取得
		HttpSession session = request.getSession();
		//セッションスコープにインスタンスを保存
		session.setAttribute("userName", user);

		//サーブレットにリダイレクト(UserListServlet)
		response.sendRedirect("UserListServlet");

	}

}

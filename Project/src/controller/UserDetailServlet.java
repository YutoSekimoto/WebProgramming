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

	    //ゲットパラメーターを取得
		String loginId = request.getParameter("loginid");

		UserDao userDao = new UserDao();
		User user = userDao.userDetail(loginId);

		if(user == null ) {

			//フォワード(ユーザ一覧ページ)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);
			return;

		}

		//リクエストスコープをセット
		request.setAttribute("user", user);

		//フォワード(ユーザ詳細ページ)
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

package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
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

		//管理者ログイン場合
		if(userName.getName().equals("関本")) {

			//データベースでユーザー一覧を取得
			UserDao userDao = new UserDao();
			List<User> userList = userDao.findAll();

			//リクエストスコープにセット
			request.setAttribute("userList", userList);
			request.setAttribute("manager", "管理者ログイン");

			//フォワード(ユーザ一覧ページ)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);

		}else {//管理者ログイン以外の場合

			//データベースでユーザー一覧を取得
			UserDao userDao = new UserDao();
			List<User> userList = userDao.findAll();

			//最初の要素(管理者)を削除
			userList.remove(0);

			//リクエストスコープにセット
			request.setAttribute("userList", userList);
			request.setAttribute("notmanager", "非管理者ログイン");

			//フォワード(ユーザ一覧ページ)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

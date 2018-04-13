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
		UserDao userDao = new UserDao();

		//結果を保存
		String message = userDao.userDelete(loginId);

		if(message != null) {

			//サーブレットにリダイレクト(UserListServlet)
			response.sendRedirect("UserListServlet");
			return;

		}

		//サーブレットにリダイレクト(UserListServlet)
		response.sendRedirect("UserListServlet");
		return;

	}

}

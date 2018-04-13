package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//サーブレットにリダイレクト(UserListServlet)
		response.sendRedirect("UserListServlet");
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

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String bDate1 = request.getParameter("birthDate1");
		String bDate2 = request.getParameter("birthDate2");

		//確認用
		out.println(loginId);
		out.println(password);
		out.println(bDate1);
		out.println(bDate2);

		//未入力の項目があった場合
		if(loginId == "" || password == "" || bDate1 == "" || bDate2 == "") {

			//サーブレットにリダイレクト(UserListServlet)
			response.sendRedirect("UserListServlet");
			return;

		}


		UserDao userDao = new UserDao();
		User user = userDao.userSearch(loginId, password, bDate1 ,bDate2);

		if(user == null) {

			//サーブレットにリダイレクト(UserListServlet)
			response.sendRedirect("UserListServlet");
			return;

		}

		//リクエストスコープをセット
		request.setAttribute("user", user);

		//フォワード(ユーザ詳細ページ)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDetail.jsp");
		dispatcher.forward(request, response);
		return;

	}

}

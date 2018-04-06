package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.Comonn;
import model.User;

public class UserDao {

	/**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, Comonn.convertPassword(password));
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    public String upDate(String loginId , String password , String password2 , String name , String birthDate){

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

    	            	/*
    	            	//リクエストにエラーメッセージをセット
    	    			request.setAttribute("eM", "入力したログインIDは既に使用されています");

    	    			//フォワード(ユーザ新規登録)
    	    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userTouroku.jsp");
    	    			dispatcher.forward(request, response);
    	    			*/
    	    			return "入力したログインIDは既に使用されています";

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
    	            pstmt.setString(5,birthDate);
    	            pstmt.setString(6,birthDate);
    	            //INSERT文を実行
    	            int num = pstmt.executeUpdate();

    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            return null;
    	        } finally {
    	            // データベース切断
    	            if (conn != null) {
    	                try {
    	                    conn.close();
    	                } catch (SQLException e) {
    	                    e.printStackTrace();
    	                    return null;
    	                }
    	            }
    	        }
				return "入力されたログインIDは既に使用されています";

    }

}

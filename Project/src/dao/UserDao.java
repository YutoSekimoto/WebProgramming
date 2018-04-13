package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            pStmt.setString(2, password);
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
            String sql = "SELECT * FROM user where login_id not in ('admin')";

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

    public String userTouroku(String loginId , String name , String bDate , String  password , String cDate , String uDate ) {

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

            	String eM = "失敗";
    			return eM;

            }

            //INSERT文の準備
            String sql=" insert into user (login_id,name,birth_date,password,create_date,update_date) values (?,?,?,?,?,?)";
            //PreparedStatementの準備
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //バインドパラメータにバインド
            pstmt.setString(1,loginId);
            pstmt.setString(2,name);
            pstmt.setString(3,bDate);
            pstmt.setString(4,password);
            pstmt.setString(5,cDate);
            pstmt.setString(6,uDate);
            //INSERT文を実行
            int num = pstmt.executeUpdate();

            String eM = "成功";
            return eM;


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

    public User userDetail(String loginid) {


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
            pstmt.setString(1,loginid);
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

            	//リクエストスコープに保存するインスタンス
            	User user = new User(id , lId , name , bDate , password , cDate , uDate);
            	return user;

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

		return null;

    }

    public String userUpdate(String loginid , String password , String password2 , String name , String bDate) {

    	//データベース設定
    			Connection conn = null;

    			try {

    				// データベースへ接続
    	            conn = DBManager.getConnection();

    	            //パスワードとパスワード(確認)がどちらも空の場合
    	            if(password == "" && password2 == "") {

    	            	    //UPDATE文の準備
    	                String sql = " update user set name = ? , birth_date = ? where login_id = ? ";
    	                //PreparedStatementの準備
    	                PreparedStatement pstmt = conn.prepareStatement(sql);
    	                //バインドパラメータにバインド]
    	                pstmt.setString(1,name);
    	                pstmt.setString(2,bDate);
    	                pstmt.setString(3,loginid);
    	                //UPDATE文を実行
    	                int result = pstmt.executeUpdate();

    	                String message = "成功1";
    	                return message;

    	            }

    	            //UPDATE文の準備
    	            String sql = " update user set name = ? , password = ? , birth_date = ? where login_id = ? ";
    	            //PreparedStatementの準備
    	            PreparedStatement pstmt = conn.prepareStatement(sql);
    	            //バインドパラメータにバインド]
    	            pstmt.setString(1,name);
    	            pstmt.setString(2, password);
    	            pstmt.setString(3,bDate);
    	            pstmt.setString(4,loginid);
    	            //UPDATE文を実行
    	            int result = pstmt.executeUpdate();

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

    			//登録に成功した場合
    			String message = "成功2";
    			return message;

    }

    public String userDelete(String loginid) {

    	        //データベース設定
    			Connection conn = null;

    					try {

    						// データベースへ接続
    			            conn = DBManager.getConnection();

    			            //SELECT文の準備
    			            String sql = " delete from user where login_id = ? ";
    			            //PreparedStatementの準備
    			            PreparedStatement pstmt = conn.prepareStatement(sql);
    			            //バインドパラメータにバインド]
    			            pstmt.setString(1,loginid);
    			            //SELECT文を実行
    			            int result = pstmt.executeUpdate();

    			            if(result != 0) {

    			            	String message = "成功";
    			            	return "成功";

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

    					return null;

    }

    public User userSearch(String loginId , String passWord , String birthDate1 , String birthDate2) {

    //	String passwordPstmt = "%" + passWord + "%";

    	        //データベース設定
    			Connection conn = null;
    			try {

    				// データベースへ接続
    	            conn = DBManager.getConnection();

    	            // SELECT文を準備
    	            String sql = "SELECT * FROM user where login_id not in ('admin')";

    	            if(!loginId.equals("")) {
    	            	sql += "and login_id = '" + loginId + "'";
    	            }



    	             // SELECTを実行し、結果表を取得
    	            Statement stmt = conn.createStatement();
    	            ResultSet rs = stmt.executeQuery(sql);

    	            while(rs.next()) {

    	            	int id = rs.getInt("id");
    	            	String lId = rs.getString("login_id");
    	            	String name = rs.getString("name");
    	            	Date bDate = rs.getDate("birth_date");
    	            	String password = rs.getString("password");
    	            	String cDate = rs.getString("create_date");
    	            	String uDate = rs.getString("update_date");

    	            	//リクエストスコープに保存するインスタンス
    	            	User user = new User(id , lId , name , bDate , password , cDate , uDate);
    	            	return user;

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

    			return null;

    }

}

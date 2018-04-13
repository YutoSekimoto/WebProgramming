package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	   final private static String URL = "jdbc:mysql://localhost/";
	    final private static String DB_NAME = "user";
	    final private static String USER = "root";
	    final private static String PASS = "new_pass";

	    /**
	     * DBへ接続するコネクションを返す
	     */
	    public static Connection getConnection() {
	        Connection con = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(URL+DB_NAME,USER,PASS);
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return con;
	    }

}

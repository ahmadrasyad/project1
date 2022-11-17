package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.user;

public class database {

	private String URL = "jdbc:mysql://localhost:3306/myjavacode?useSSL=false";
	private String username = "root";
	private String password = "root";
	private String driver = "com.mysql.jdbc.Driver";
	
	
	private static final String insert_sql = "insert into registration1 (name, email, address, phone, city, country, education, photo) "+
												" values (?,?,?,?,?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "select name, email, address, phone, city, country, education from registration1 where id =?";
	private static final String SELECT_USER_PEEK = "select id, name, date, city from registration1 ";
	private static final String DELETE_USER_SQL = "delete from registration1 where id = ?;";
	private static final String UPDATE_USER_SQL = "update registration1 set name = ?,email= ?, address=?, phone=?, city=?, country =?, education =?, photo =? where id = ?;";
	private static final String VIEW_DATA = "select name, email, address, phone, city, country, education, photo from registration1 where id=?;";
	
	public database() {
		
	}
	
	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(URL,username,password);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void insert(user user) throws FileNotFoundException {
		System.out.println(insert_sql);
		try(Connection con = getConnection();
				PreparedStatement ptsmt = con.prepareStatement(insert_sql)){
			 	ptsmt.setString(1, user.getName());
	            ptsmt.setString(2, user.getEmail());
	            ptsmt.setString(3, user.getAddress());
	            ptsmt.setString(4, user.getPhone());
	            ptsmt.setString(5, user.getCity());
	            ptsmt.setString(6, user.getCountry());
	            ptsmt.setString(7, user.getEducation());
	            String photo = user.getPhoto();
	            File file = new File(photo);
	            FileInputStream fis = new FileInputStream(file);
	            int len = (int)file.length();
	            ptsmt.setBinaryStream(8, (InputStream)fis, len);
	            System.out.println(ptsmt);
	            ptsmt.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public user select(int id) {
		user user1 = null;
		try(Connection con = getConnection();
				PreparedStatement ptsmt = con.prepareStatement(SELECT_USER_BY_ID);){
			ptsmt.setInt(1, id);
			System.out.println(ptsmt);
			ResultSet rs = ptsmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String education = rs.getString("education");
                user1 = new user (id, name, email, address, phone, city, country, education);
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
			return user1;
		
	}
	
	public List<user> selectbypeek(){
		
		List<user> users = new ArrayList<>();
		
		try(Connection con = getConnection();
				PreparedStatement ptsmt = con.prepareStatement(SELECT_USER_PEEK);){
			System.out.println(ptsmt);
			ResultSet rs = ptsmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String date = rs.getString("date");
				String city = rs.getString("city");
				users.add(new user(id,name,date,city));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		
		return users;
	}
	
	
	public boolean editUser(user user)throws SQLException, FileNotFoundException{
		boolean rowUpdated;
		try(Connection con = getConnection();
				PreparedStatement ptsmt = con.prepareStatement(UPDATE_USER_SQL);){
			ptsmt.setString(1, user.getName());
			ptsmt.setString(2, user.getEmail());
			ptsmt.setString(3, user.getAddress());
			ptsmt.setString(4, user.getPhone());
			ptsmt.setString(5, user.getCity());
			ptsmt.setString(6, user.getCountry());
			ptsmt.setString(7, user.getEducation());
			String photo = user.getPhoto();
			File file = new File (photo);
			FileInputStream fis = new FileInputStream(file);
			int len = (int)file.length();
			ptsmt.setBinaryStream(8, (InputStream)fis, len);
			ptsmt.setInt(9, user.getId());
			rowUpdated = ptsmt.executeUpdate() > 0;
			
		}
		return rowUpdated;
		
	}
	
	
	public boolean delete(int id) throws SQLException{
		boolean rowdeleted;
		try(Connection con = getConnection();
				PreparedStatement ptsmt = con.prepareStatement(DELETE_USER_SQL);){
			ptsmt.setInt(1, id);
			rowdeleted = ptsmt.executeUpdate() >0;
		}
		return rowdeleted;
		
	}
	
	public user selectdata (int id) {
		user user = null;
		try (Connection con = getConnection();
				PreparedStatement ptsmt = con.prepareStatement(VIEW_DATA);) {
			ptsmt.setInt(1, id);
			System.out.println(ptsmt);
			ResultSet rs = ptsmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String city = rs.getString("city");
				String country = rs.getString("country");
				String education = rs.getString("education");
				Blob img = rs.getBlob("photo");
				byte image [] = img.getBytes(1, (int)img.length());
				user = new user (id, name, email, address, phone, city, country, education, image);
			}
			}catch (SQLException e) {
				printSQLException(e);
			}return user;
	}
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: "+((SQLException)e).getSQLState());
			System.err.println("Error Code: "+((SQLException)e).getSQLState());
			System.err.println("Message: "+e.getMessage());
			Throwable t = ex.getCause();
			while(t != null) {
				System.out.println("Cause: "+ t);
				t = t.getCause();
			}
			
		}
	}
	
	
	
}

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.database;
import bean.user;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private database dao;
   
     public void init() {
    	 dao = new database();
     }
     
     
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/new":
				viewUser(request, response);
				break;
			case "/add":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				editUser(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/select":
				selectdata(request, response);
				break;
			case"/view":
				viewdata(request, response);
				break;
			case"/list":
				listUser(request, response);
				break;
			default:
				home(request, response);
				break;
			}
		}catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<user> listUser = dao.selectbypeek();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		user existinguser = dao.select(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
		request.setAttribute("user", existinguser);
		dispatcher.forward(request, response);
	}
	
	private void viewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String education = request.getParameter("education");
		String photo = request.getParameter("photo");
		user newuser = new user(name, email, address, phone, city, country, education, photo);
		dao.insert(newuser);
		response.sendRedirect("list");
	}
	
	private void updateUser (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String education = request.getParameter("education");
		String photo = request.getParameter("photo");
		user book = new user(id, name, email, address, phone, city, country, education, photo);
		dao.editUser(book);
		response.sendRedirect("list");
	}
	
	private void deleteUser (HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		response.sendRedirect("list");

	}
	
	private void selectdata(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException, ServletException {
		response.setContentType("image/gif");
		RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
		dispatcher.forward(request, response);
	}
	private void viewdata(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		user existinguser = dao.selectdata(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
		request.setAttribute("user", existinguser);
		dispatcher.forward(request, response);
	}
	private void home(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,ServletException{
		response.sendRedirect("homepage.jsp");
	}


}

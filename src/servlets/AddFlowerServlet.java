package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.*;
@WebServlet("/add")

public class AddFlowerServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;
		
		private Connection db;

		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));

			Flower flower = new Flower (name, price );
			
			System.out.println(flower);
			//INSERT INTO public."FlowerStore" (id, name, price) VALUES (1, 'dsada', 2 )
			String query = "INSERT INTO public.\"store\" (NAME, PRICE) VALUES(?,?)";
			PreparedStatement st;
			try {
				st = db.prepareStatement(query);
				//st.setInt(1, 3);
				st.setString(1, flower.getName());
				st.setInt(2,  flower.getPrice());
				st.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*============= */
			
			ResultSet rs = null;
			
			try {
				Statement statement = db.createStatement();
				String retrieveQuery = "SELECT * FROM public.\"store\"";
				rs = statement.executeQuery(retrieveQuery);
				PrintWriter pw = response.getWriter();  
				pw.println("<html><table border=\"2\"><tr><th>Id</th><th>Name</th><th>Price</th></tr>"); 
				while(rs.next()) { 
					pw.println("<tr><td>"+ rs.getInt("id") +"</td>" + "<td>" + rs.getString("name") + "</td>" + "<td>"+rs.getInt("price")+"</td></tr>"); 
				}
				pw.println("</table></html>"); 
				pw.flush();
				pw.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	
	public void init() throws ServletException {
		try {
			db = connectToDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void destroy() {
	}
	
	private Connection connectToDB() throws SQLException, ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		Properties props = new Properties();
//		props.setProperty("port", "5432");
		props.setProperty("user", "postgres");
		props.setProperty("password", "admin");
		
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver version: " + org.postgresql.Driver.getVersion());
		
		Connection conn= DriverManager.getConnection(url,props);
		return conn;
	}
	
}

package SwiftAcad_Homework_14_Vasil_Stefanov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
	private int id;
	private String name;
	private String enrollment_date;

	public Student(int id, String name, String enrollment_date) {
		this.id = id;
		this.name = name;
		this.enrollment_date = enrollment_date;
	}

	public void insertStudent() throws SQLException{
		try(Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/School", "root", "123456789");
			PreparedStatement ps = 
					con.prepareStatement("insert into students (id, name, enrollment_date)"
							+ "values(?,?,?);")) {

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, enrollment_date);
			
			
			ps.execute();
		}
	
	}
	

	public void getStudent(int id) throws SQLException {

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/School", 
				"root", "123456789");
				Statement stm = con.createStatement();

				ResultSet rs = stm.executeQuery("select * from students where id =" + id + ";")){
		       
				while (rs.next()) {
				for (int i = 1; i <= getCountColumns(); i++) {
				System.out.print(rs.getString(i) + " ");
				}
				System.out.println();
			}
			
		}

	}

	public void getStudent(String str) throws SQLException {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/School", "root", "123456789");
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery("select * from students where " + " enrollment_date > '" + str + "';");)
		
		{
			while (rs.next()) {
				for (int i = 1; i <= getCountColumns(); i++) {
					System.out.print(rs.getString(i) + " ");
					}
					System.out.println();
			}
			
		}
	}
	public  int getCountColumns() throws SQLException {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/School",
				"root", "123456789");
				Statement stm = con.createStatement();

				ResultSet rs = stm.executeQuery("SELECT COUNT(*) AS NUMBEROFCOLUMNS FROM INFORMATION_SCHEMA.COLUMNS " + 
						"WHERE table_schema = 'school' AND table_name = 'students';"))
		{
			int count = 0;
			while (rs.next()) {
		    count = Integer.parseInt(rs.getString(1));
			}
			return count;
		}
		
	}
	
	
}

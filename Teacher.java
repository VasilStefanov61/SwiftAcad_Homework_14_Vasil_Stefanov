package SwiftAcad_Homework_14_Vasil_Stefanov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Teacher {
	private int id;
	private String name;
	private String email;
	private double salary;
	
	public Teacher(int id, String name, 
			String email, double salary) {
	
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = salary;
		
	}


	
	public  void insertTeacher() throws SQLException{
		try(Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/School", "root", "123456789");
			PreparedStatement ps = 
					con.prepareStatement("insert into teachers (id, name, email, salary)"
							+ "values(?,?,?,?);")) {
			
//			Scanner sc = new Scanner(System.in);
//			System.out.print("Enter teacher_id : ");
//			int id = sc.nextInt();
//			sc.nextLine();
//			System.out.print("Enter teacher_name : ");
//			String name = sc.nextLine();
//			System.out.print("Enter teacher_email : ");
//			String email = sc.nextLine();
//			System.out.print("Enter teacher_salary : ");
//			double salary = sc.nextDouble();
//			sc.nextLine();
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setDouble(4, salary);
			
			ps.execute();
		}
	
	}
public  void getTeacher(int id) throws SQLException {
		
		try(Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/School","root","123456789");
						Statement stm = con.createStatement();
						
		
		ResultSet rs = stm.executeQuery("select * from teachers where id =" + id +";"))
		
	    {while(rs.next()) {
	    	for (int i = 1; i <= getCountColumns(); i++) {
				System.out.print(rs.getString(i) + " ");
				}
	    	System.out.println();
		}
	    }
		
	}
public  void getTeachers(double x, double y)
		throws SQLException {
	try(Connection con = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/School","root","123456789");
					Statement stm = con.createStatement();
	ResultSet rs = stm.executeQuery("select * from teachers where "
			+ "salary between " +  x + " and " + y +";");)
	
	{while(rs.next()) {
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
					"WHERE table_schema = 'school' AND table_name = 'teachers';"))
	{
		int count = 0;
		while (rs.next()) {
	    count = Integer.parseInt(rs.getString(1));
		}
		
		return count;
	}
	
}	
		
}

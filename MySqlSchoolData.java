package SwiftAcad_Homework_14_Vasil_Stefanov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MySqlSchoolData {

	public static void main (String [] args) throws SQLException {

			Teacher teacher = new Teacher(5, "Ivan Ivanov", "iivanov@gmail.com", 3333.33);
		//teacher.insertTeacher();
		teacher.getTeacher(1);
		System.out.println("_______________________");
		teacher.getTeachers(1300,1900);
		System.out.println("_______________________");
		Student student = new Student(5, "Peter Petrov", "2010-10-01");
		//student.insertStudent();
		student.getStudent(3);
		System.out.println("_______________________");
		student.getStudent("2007-00-00");
		System.out.println("_______________________");
		getDisciplinesByTeacherId(teacher,2);
		System.out.println("_______________________");
		getTeachersByDisciplineName(teacher, "Mathematics");
		}
	
		public static void getDisciplinesByTeacherId(Teacher teacher, int id ) throws SQLException	{
			teacher.getTeacher(id);
			try(Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/School","root","123456789");
							Statement stm = con.createStatement();
							
			ResultSet rs = stm.executeQuery("select name from disciplines where teacher_id =" + id +";"))
			
			 {while(rs.next()) {
			    	
						System.out.println(rs.getString(1));
						
			    }
			 
			 }
			System.out.println();
						
		}
		
		public static void getTeachersByDisciplineName(Teacher teacher, String name ) throws SQLException	{
			System.out.println(name);
			int count = 0;
			
			try(Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/School","root","123456789");
							Statement stm = con.createStatement();
							
			ResultSet rs = stm.executeQuery("select teacher_id from disciplines where name = '" + name +"';"))
			
			 {while(rs.next()) {
				 count = Integer.parseInt(rs.getString(1));
				 teacher.getTeacher(count);
			    						
			    }
			 
			 }
			
			System.out.println();
		}
		
		
		
	}
	
	

	
	


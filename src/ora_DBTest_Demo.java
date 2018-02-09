import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ora_DBTest_Demo {
	public static void main(String[] args) {
		try {
	        oracle DBConnect_instance1 = new oracle();
	    	mySQL DBConnect_instance2 = new mySQL();

	    	DBConnect_instance1.openConnection();
	    	DBConnect_instance1.getDate(0);
	    	DBConnect_instance1.closeConnection();
	    	
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	    	
	        DBConnect_instance2.openConnection();
	        DBConnect_instance2.getDate(0);
	        DBConnect_instance2.createExample();
	        DBConnect_instance2.insertExample();
	        DBConnect_instance2.selectExample();
	        DBConnect_instance2.closeConnection();
		} 
		
		catch (Exception e){
			e.printStackTrace();
		}		
	}
}//change ip and connect to it
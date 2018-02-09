import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class oracle { 
	private Connection connect;
	private PreparedStatement preparedStatement;
	private ResultSet rs;

	public oracle() {
		connect = null;
	    preparedStatement = null;
	    rs = null;
	}
	
    public void openConnection() throws SQLException {
        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String dbURL1 = "jdbc:oracle:thin:@bonnet19.cs.qc.edu:1521:dminor";
        
        String userName1 = "lackner";
        String userPassword1 = "guest";
    	
        try {    
        	Class.forName(jdbcDriver);
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Connecting to Oracle server...");
        System.out.println("Location: " + dbURL1);
        System.out.println("Username: " + userName1);
        System.out.println("Password: " + userPassword1 + '\n');

        connect = DriverManager.getConnection(dbURL1, userName1, userPassword1);
        System.out.println("Oracle DB connection opened!" + '\n' + '\n');   
    }
    
    public void close() throws SQLException {
    	rs.close();
    	preparedStatement.close();
    }
    
    public void closeConnection() throws SQLException {
	   	if (rs != null) {
	   		close();
	   	}
	    	
	   	connect.close();
	   	System.out.println();
	    System.out.print("Oracle DB connection closed!");
	}
    
    
    //SELECT
    public void query(String stmtQuery) {
	    System.out.println(stmtQuery + '\n');
	    
        try {
			preparedStatement = connect.prepareStatement(stmtQuery);
	        rs = preparedStatement.executeQuery();
		} 
        
        catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    public void getDate(int hr_offset) throws SQLException {
    	System.out.println("Fetching time...");
	    String stmtQuery = "select sysdate + " + hr_offset + " from dual";
	    
	    query(stmtQuery);
	    
	    if (rs.next()) {
	      String newTime = rs.getString(1); 
	      System.out.println(hr_offset + " hour(s) ahead of the system clock of Oracle at bonnet19 is: " + newTime);
	    }
	    
	    System.out.println();
	    close();
	}
}

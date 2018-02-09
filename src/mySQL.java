import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mySQL {   
	private Connection connect;
	private PreparedStatement preparedStatement;
	private ResultSet rs;

	public mySQL() {
        connect = null;
        preparedStatement = null;
        rs = null;
	}

    public void openConnection() throws SQLException {
    	String jdbcDriver = "com.mysql.jdbc.Driver";
    	String dbURL1 = "jdbc:mysql://10.255.186.142:3306/studentdb?";
    	//"jdbc:mysql://127.0.0.1:3306/studentdb?" (localhost)
    	//"jdbc:mysql://Prestons-MBP.home:3306/studentdb?" (hostname)
    	//"jdbc:mysql://192.168.X.X:3306/studentdb?" (internal ip)
    	
    	String userName1 = "dude";
    	String userPassword1 = "password";
    	
    	try {
		    Class.forName(jdbcDriver);
	    } 
    	
        catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    	
	    System.out.println("Connecting to MySQL server...");
        System.out.println("Location: " + dbURL1);
        System.out.println("Username: " + userName1);
        System.out.println("Password: " + userPassword1 + '\n');
        
	    connect = DriverManager.getConnection(dbURL1, userName1, userPassword1);
	    System.out.println("MySQL DB connection opened!" + '\n' + '\n');          
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
	    System.out.print("MySQL DB connection closed!");
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
    
    //INSERT, UPDATE, DELETE, CREATE, ALTER
    public void update(String stmtQuery) {
    	int rows = 0;
    	System.out.println(stmtQuery + '\n');
    	
		try {
			preparedStatement = connect.prepareStatement(stmtQuery);
			rows = preparedStatement.executeUpdate();
			
			System.out.println(rows + " rows affected" + '\n');
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    public void getDate(int hr_offset) throws SQLException {
    	System.out.println("Fetching time...");
    	query("SELECT CURTIME() + " + hr_offset);
    	
	    if (rs.next()) {
	    	String newTime = rs.getString(1); 
	        System.out.println(hr_offset + " hour(s) ahead of the system clock of MySQL at localhost is: " + newTime + '\n');
	    }
	    
	    System.out.println();
	    close();
    }
    
	/*CREATE TABLE table_name(
	column1 datatype, constraint
	column2 datatype,
	column3 datatype,
	.....
	columnN datatype,
	PRIMARY KEY( one or more columns )
	);*/
	public void createExample() throws SQLException {
		System.out.println("Creating table...");
		
		String stmtQuery = "CREATE TABLE CUSTOMERS(" + '\n'
			+"   ID		INT		NOT NULL," + '\n'
			+"   NAME 	VARCHAR (20)	NOT NULL," + '\n'
			+"   AGE  	INT             NOT NULL," + '\n'
			+"   ADDRESS  	CHAR (25)," + '\n'
			+"   SALARY   	DECIMAL (18, 2)," + '\n' + '\n'     
			+"   PRIMARY KEY (ID)" + '\n'
			+");";
		update(stmtQuery);
		
		System.out.println();
		close();
	}
	
	//INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)  
	//VALUES (value1, value2, value3,...valueN);
	public void insertExample() throws SQLException {
		String stmtQuery = "";
		System.out.println("Inserting into table...");
		
		stmtQuery = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)" + '\n'
			+"VALUES (1, 'Ramesh', 32, 'Ahmedabad', 2000.00 );";
		update(stmtQuery); 

		stmtQuery =	"INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)" + '\n'
			+"VALUES (2, 'Khilan', 25, 'Delhi', 1500.00 );";
		update(stmtQuery); 

		stmtQuery = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)" + '\n'
			+"VALUES (3, 'kaushik', 23, 'Kota', 2000.00 );";
		update(stmtQuery);

		stmtQuery = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)" + '\n'
			+"VALUES (4, 'Chaitali', 25, 'Mumbai', 6500.00 );";
		update(stmtQuery);

		stmtQuery = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)" + '\n'
			+"VALUES (5, 'Hardik', 27, 'Bhopal', 8500.00 );";
		update(stmtQuery);
	
		stmtQuery = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)" + '\n'
			+"VALUES (6, 'Komal', 22, 'MP', 4500.00 );";
		update(stmtQuery);
		    
		System.out.println();
		close();
	}
	
	//SELECT column1, column2, ..., columnN FROM table_name;
	public void selectExample() throws SQLException {
		System.out.println("Selecting from table...");
		
		String stmtQuery = "SELECT ID, NAME, SALARY FROM CUSTOMERS;";
		query(stmtQuery);
		
	    while (rs.next()) {
	    	System.out.print(rs.getString(1) + " ");
	    	System.out.print(rs.getString(2) + " ");
	    	System.out.println(rs.getString(3) + " ");
	    }
	    
	    System.out.println();
	    
	    stmtQuery = "SELECT * FROM CUSTOMERS;";
		query(stmtQuery);
		
	    while (rs.next()) {
	    	System.out.print(rs.getString(1) + " ");
	    	System.out.print(rs.getString(2) + " ");
	    	System.out.print(rs.getString(3) + " ");
	    	System.out.print(rs.getString(4) + " ");
	    	System.out.println(rs.getString(5) + " ");
	    }
	    
	    System.out.println();
		close();
	}
}

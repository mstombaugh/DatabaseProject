import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class DBConnection {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public enum TableNames{
		CONTAIN(0),CUSTOMERS(1),EMPLOYEES(2),HAS(3),INVENTORY(4),STORES(5),TRANSACTIONS(6);
		private int value;
		private TableNames(int val){
				this.value = value;
		}
		
	}

	
	
	public DBConnection(String connectonInfo){
		//connect to DB
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection(connectonInfo);
			statement = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void query(String query, TableNames select){
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			if(select == TableNames.CONTAIN){
				resultContain(resultSet);
			}else if(select == TableNames.CUSTOMERS){
				resultCustomers(resultSet);
			}else if(select == TableNames.EMPLOYEES){
				resultEmployees(resultSet);
			}else if(select == TableNames.HAS){
				resultHas(resultSet);
			}else if(select == TableNames.INVENTORY){
				resultInventory(resultSet);
			}else if(select == TableNames.STORES){
				resultStores(resultSet);
			}else if(select == TableNames.TRANSACTIONS){
				resultTransactions(resultSet);
			}else return;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	private void resultContain(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      String transactionID = resultSet.getString("transactionID");
	      String itemID = resultSet.getString("itemID");
	      
	      System.out.println("transactionID: " + 	transactionID);
	      System.out.println("itemID: " + 			itemID);
	    }
	  }
	
	private void resultCustomers(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    System.out.println("customerID\tname\t\tphone\taddress\temail\t\tcustomerSince\t\t\tdiscount");
		while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int customerID = resultSet.getInt("customerID");
	      String name = resultSet.getString("name");
	      String phone = resultSet.getString("phone");
	      String address = resultSet.getString("address");
	      String email = resultSet.getString("email");
	      Timestamp customerSince = resultSet.getTimestamp("customerSince");
	      int discount = resultSet.getInt("discount");
	      
	      
	      System.out.print(customerID+'\t');
	      System.out.print(name+"\t\t");
	      System.out.print(phone+"\t");
	      System.out.print(address+"\t");
	      System.out.print(email+"\t\t");
	      System.out.print(customerSince+"\t\t\t");
	      System.out.println(discount);

	    }
	  }
	
	private void resultEmployees(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int employeeID = resultSet.getInt("employeeID");
	      String ssn = resultSet.getString("ssn");
	      String name = resultSet.getString("name");
	      String rank = resultSet.getString("rank");
	      Double wage = resultSet.getDouble("wage");
	      Timestamp employeeSince = resultSet.getTimestamp("employeeSince");
	      int locationNum = resultSet.getInt("locationNum");
	      
	      
	      System.out.println("employeeID: " + 		employeeID);
	      System.out.println("ssn: " + 				ssn);
	      System.out.println("name: " + 			name);
	      System.out.println("rank: " + 			rank);
	      System.out.println("wage: " + 			wage);
	      System.out.println("employeeSince: " + 	employeeSince);
	      System.out.println("locationNum: " + 		locationNum);

	    }
	  }
	
	private void resultHas(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int locationNum = resultSet.getInt("locationNum");
	      int itemID = resultSet.getInt("itemID");
	      int count = resultSet.getInt("count");
	      
	      
	      
	      System.out.println("locationNum: " + 		locationNum);
	      System.out.println("itemID: " + 			itemID);
	      System.out.println("count: " + 			count);
	    }
	  }
	
	private void resultInventory(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int itemID = resultSet.getInt("itemID");
	      String name = resultSet.getString("ssn");
	      String type = resultSet.getString("name");
	      int price = resultSet.getInt("rank");
	      
	      System.out.println("itemID: " + 		itemID);
	      System.out.println("name: " + 		name);
	      System.out.println("type: " + 		type);
	      System.out.println("price: " + 		price);


	    }
	  }
	
	private void resultStores(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int locatonNum = resultSet.getInt("locatonNum");
	      String address = resultSet.getString("address");
	      String managerName = resultSet.getString("managerName");
	      
	      
	      System.out.println("locatonNum: " + 		locatonNum);
	      System.out.println("address" + 			address);
	      System.out.println("managerName: " + 		managerName);

	    }
	  }
	
	private void resultTransactions(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      double total = resultSet.getDouble("total");
	      Timestamp timestamp = resultSet.getTimestamp("timestamp");
	      int customerID = resultSet.getInt("customerID");
	      int locationNum = resultSet.getInt("locationNum");
	      
	      
	      System.out.println("total: " + 			total);
	      System.out.println("timestamp: " + 		timestamp);
	      System.out.println("customerID: " + 		customerID);
	      System.out.println("locationNum: " + 		locationNum);

	    }
	  }
	
	/*private void close() {
	    close(resultSet);
	    close(statement);
	    close(connect);
	  }
	  private void close(Closeable c) {
	    try {
	      if (c != null) {
	        c.close();
	      }
	    } catch (Exception e) {
	    // don't throw now as it might leave following closables in undefined state
	    }
	  }
	} */
	
	
	
}

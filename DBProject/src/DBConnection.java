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
	
	public void close(){
		try {
			statement.close();
			connect.close();
			resultSet.close();
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
			System.out.println(query);
			close();
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
			close();
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
	    System.out.println("customerID\tname\t\t\tphone\t\taddress\t\t\temail\t\t\t\tCustomerSince\t\t\t\tdiscount");
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
	      
	      if(name.length()<10)name+="\t\t";
	      else if(name.length()<15)name+='\t';
	      if(address.length()<20)address+="\t";
	      
	      System.out.print(customerID+"\t\t");
	      System.out.print(name+"\t");
	      System.out.print(phone+"\t");
	      System.out.print(address+"\t");
	      System.out.print(email+"\t\t");
	      System.out.print(customerSince+"\t\t\t");
	      System.out.println(discount);

	    }
	  }
	
	private void resultEmployees(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("employeeID\tSSN\t\tName\t\t\tRank\t\t\tWage\t\tEmployeeSince\t\t\t\tlocationNum");
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
	      
	      //if(name.length()<8)name+="\t\t";
	      if(name.length()<8)name+="    ";
	      
	      System.out.print(employeeID + "\t\t");
	      System.out.print(ssn + '\t');
	      System.out.print(name + "\t\t");
	      System.out.print(rank + "\t\t");
	      System.out.print(wage + "\t\t");
	      System.out.print(employeeSince + "\t\t\t");
	      System.out.println(locationNum);

	    }
	  }
	
	private void resultHas(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("locationNum\titemID\tcount");
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int locationNum = resultSet.getInt("locationNum");
	      int itemID = resultSet.getInt("itemID");
	      int count = resultSet.getInt("count");
	      
	      
	      
	      System.out.println(locationNum + '\t');
	      System.out.println(itemID + '\t');
	      System.out.println(count);
	    }
	  }
	
	private void resultInventory(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("itemID\tName\t\tType\t\tPrice");
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int itemID = resultSet.getInt("itemID");
	      String name = resultSet.getString("name");
	      String type = resultSet.getString("type");
	      int price = resultSet.getInt("price");
	      
	      System.out.print(itemID + "\t");
	      System.out.print(name + '\t' );
	      System.out.print(type + '\t' + '\t');
	      System.out.println(price);


	    }
	  }
	
	private void resultStores(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("locationNum\tAddress\t\t\t\tManagerName");
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int locatonNum = resultSet.getInt("locationNum");
	      String address = resultSet.getString("address");
	      String managerName = resultSet.getString("managerName");
	      
	      
	      System.out.print(locatonNum + "\t\t");
	      System.out.print(address + '\t' + '\t');
	      System.out.println(managerName);

	    }
	  }
	
	private void resultTransactions(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("Total\t\tTimestamp\t\t\t\tCustomerID\tLocationNum");
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      double total = resultSet.getDouble("total");
	      Timestamp timestamp = resultSet.getTimestamp("timestamp");
	      int customerID = resultSet.getInt("customerID");
	      int locationNum = resultSet.getInt("locationNum");
	      
	      
	      System.out.print(total + "\t\t");
	      System.out.print(timestamp.toString() + '\t' + '\t' + '\t');
	      System.out.print(customerID + "\t\t");
	      System.out.println(locationNum);

	    }
	  }
}

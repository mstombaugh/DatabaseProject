/**
 *  File: DBConnection.java
 *  @author: Michael Stombaugh
 *  Date: 04-20-2014
 *  
 *  Description:  Handles all interactions between the program and the SQL database.
 *  Formats all query data in a readable form and outputs to System.out
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	/**
	 * Enum TableNames
	 * Desc: Used to specify which table a query/insertion/deletion is targeting so the program can properly output the query results
	 */
	public enum TableNames{
		CONTAIN(0),CUSTOMERS(1),EMPLOYEES(2),HAS(3),INVENTORY(4),STORES(5),TRANSACTIONS(6),NETWORTH(7);
		private TableNames(int val){
		}
		
	}

	
	/**
	 * @param connectionInfo	The URL of the SQL database, including the username and password
	 */
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
	
	/**
	 *  Closes all connections.  Helps prevent the SQL database from getting mad about too many connections
	 */
	public void close(){
		try {
			statement.close();
			connect.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e2){
			e2.printStackTrace();
		}
		
		
	}
	
	/**
	 * Outputs a formatted table of the query.
	 * 
	 * @param query  	The full SQL query (include SELECT _ FROM _ WHERE _ ...)
	 * @param select 	The name of the table, comes from the enum DBConnection.TableNames
	 */
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
			}else if(select == TableNames.NETWORTH){
				resultNetWorth(resultSet);
			}
			else return;
		}catch (Exception e){
			close();
			e.printStackTrace();
		}
	}
	
	/**
	 * Outputs a formatted table of the query.
	 * 
	 * @param query  	The full SQL query (include INSERT INTO [tablename] VALUES(...))
	 * @param select 	The name of the table, comes from the enum DBConnection.TableNames
	 */
	public void insert(String query, TableNames select){
		int result=0;
		try {
			result = statement.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(query);
			close();
			e1.printStackTrace();
		}
		try{
			if(select == TableNames.CONTAIN){
				resultInsertContain(result);
			}else if(select == TableNames.CUSTOMERS){
				resultInsertCustomers(result);
			}else if(select == TableNames.EMPLOYEES){
				resultInsertEmployees(result);
			}else if(select == TableNames.HAS){
				resultInsertHas(result);
			}else if(select == TableNames.INVENTORY){
				resultInsertInventory(result);
			}else if(select == TableNames.STORES){
				resultInsertStores(result);
			}else if(select == TableNames.TRANSACTIONS){
				resultInsertTransactions(result);
			}
			else return;
		}catch (Exception e){
			close();
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the ResultSet of a given query.
	 * 
	 * @param query 	The query being sent to the database
	 * @return			The ResultSet returned by the query
	 */
	public ResultSet getData(String query){
		try{
			resultSet = statement.executeQuery(query);
			return resultSet;
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			close();
			e1.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * Special helper for a specific query to help with transaction calculations
	 * 
	 * @param itemID	The itemID of the item you need the price for
	 * @return 			The price of the item with the specified itemID
	 */
	public int getPrice(String itemID){
		try{
			resultSet = statement.executeQuery("SELECT price FROM Inventory WHERE itemID = " + itemID);
			resultSet.next();
			return resultSet.getInt("price");
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("SELECT price FROM Inventory WHERE itemID = " + itemID);
			close();
			e1.printStackTrace();
	    }
		return 0;
	}
	
	/**
	 * Returns the last inserted ID in a given table
	 * <p>
	 * Note: for tables with no table-specific ID (Like Has or Contains), nothing is returned
	 * 
	 * @param t	A TableNames object specifying the table to get the last inserted ID from
	 * @return	The last inserted ID in the given table.  Returns null for tables that have no table-specific ID
	 */
	public String getLastID(TableNames t){
		try{
			switch(t){
			case CUSTOMERS:
				resultSet = statement.executeQuery("SELECT MAX(customerID) AS cid FROM Customers");
				resultSet.next();
				return resultSet.getString("cid");
				
			case EMPLOYEES:
				resultSet = statement.executeQuery("SELECT MAX(EmployeeID) AS eid FROM Employees");
				resultSet.next();
				return resultSet.getString("eid");
				
			case INVENTORY:
				resultSet = statement.executeQuery("SELECT MAX(itemID) AS iid FROM Inventory");
				resultSet.next();
				return resultSet.getString("iid");
				
			case STORES:
				resultSet = statement.executeQuery("SELECT MAX(locationNum) AS sid FROM Stores");
				resultSet.next();
				return resultSet.getString("sid");
				
			case TRANSACTIONS:
				resultSet = statement.executeQuery("SELECT MAX(transactionID) AS tid FROM Transactions");
				resultSet.next();
				return resultSet.getString("tid");
				
			default: return null;
			}
				
		}
	    catch (SQLException e1) {
			// TODO Auto-generated catch block
			close();
			e1.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * Delete a tuple from a given table.  Will output all tuples affected before removing them from the table.
	 * 
	 * @param query		The full SQL query (DELETE FROM [tablename] WHERE [expression])
	 * @param select	The TableNames object of the specified table
	 */
	public void delete(String query, TableNames select){
		int result=0;
		try {
			String query2 = query.replace("DELETE", "SELECT *");
			query(query2,select);
			result = statement.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(query);
			close();
			e1.printStackTrace();
		}
		try{
			if(select == TableNames.CONTAIN){
				resultDeleteContain(result);
			}else if(select == TableNames.CUSTOMERS){
				resultDeleteCustomers(result);
			}else if(select == TableNames.EMPLOYEES){
				resultDeleteEmployees(result);
			}else if(select == TableNames.HAS){
				resultDeleteHas(result);
			}else if(select == TableNames.INVENTORY){
				resultDeleteInventory(result);
			}else if(select == TableNames.STORES){
				resultDeleteStores(result);
			}else if(select == TableNames.TRANSACTIONS){
				resultDeleteTransactions(result);
			}
			else return;
		}catch (Exception e){
			close();
			e.printStackTrace();
		}
	}
	
	/**
	 * Internal function to print the result of a query on the table Contain
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultContain(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		//System.out.println("Transaction ID\t\tItem ID");
		List<String> itemID = new ArrayList<String>();
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      itemID.add(resultSet.getString("itemID"));
	    }
	    for(int i=0;i<itemID.size(); i++){
	    	query("SELECT * FROM Inventory WHERE itemID = " + itemID.get(i),TableNames.INVENTORY);
	    }
	  }
	
	/**
	 * Internal function to print the result of a query on the table Customers
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
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
	
	/**
	 * Internal function to print the result of a query on the table Employees
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
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
	
	/**
	 * Internal function to print the result of a query on the table Has
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultHas(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("locationNum\titemID\t\tcount");
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      int locationNum = resultSet.getInt("locationNum");
	      int itemID = resultSet.getInt("itemID");
	      int count = resultSet.getInt("count");
	      
	      
	      
	      System.out.print(locationNum + "\t\t");
	      System.out.print(itemID + "\t\t");
	      System.out.println(count);
	    }
	  }
	
	/**
	 * Internal function to print the result of a query on the table Inventory
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
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
	      System.out.print(type + '\t');
	      System.out.println(price);


	    }
	  }
	
	/**
	 * Internal function to print the result of a query on the table Stores
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
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
	
	/**
	 * Internal function to print the result of a query on the table Transaction
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultTransactions(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		//System.out.println("transactionID\tTotal\t\tTimestamp\t\t\tCustomerID\tLocationNum");
		List<String> transactionID = new ArrayList<String>();
		List<String> total = new ArrayList<String>();
		List<Timestamp> timestamp = new ArrayList<Timestamp>();
		List<String> customerID = new ArrayList<String>();
		List<String> locationNum = new ArrayList<String>();
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      transactionID.add( resultSet.getString("transactionID"));
	      total.add(resultSet.getString("total"));
	      timestamp.add( resultSet.getTimestamp("timestamp"));
	      customerID.add( resultSet.getString("customerID"));
	      locationNum.add(resultSet.getString("locationNum"));

	    }
	    for(int i=0; i<transactionID.size(); i++){
	      System.out.println("transactionID\tTotal\t\tTimestamp\t\t\tCustomerID\tLocationNum");
	      System.out.print(transactionID.get(i) + "\t\t");
	      System.out.print(total.get(i) + "\t\t");
	      System.out.print(timestamp.get(i).toString() + '\t' + '\t');
	      System.out.print(customerID.get(i) + "\t\t");
	      System.out.println(locationNum.get(i));
	      System.out.println("*****Contents*****");
	      query("SELECT * FROM Contain WHERE transactionID = " + transactionID.get(i), TableNames.CONTAIN);
	    }
	  }

	/**
	 * Special helper function to print the net worth of a store.
	 * 
	 * @param resultSet	The ResultSet from the query to get the net worth from a given store locationNum
	 * @throws SQLException	If this function is called with anything other than the resultSet from a very specific query
	 */
	private void resultNetWorth(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
		System.out.println("Net Worth");
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      //String locationNum = resultSet.getString("locationNum");
	      double netWorth = resultSet.getDouble("inventoryWorth");
	      
	      //System.out.print(locationNum + "\t\t");
	      System.out.println("$" + netWorth);
	      

	    }
	  }
	
	
	//Begin INSERTIONS***********************************************************************************************************
	
	/**
	 * Internal function to print the result of an Insertion on the table Contain
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertContain(int result) throws SQLException {
		if(result>0){
			System.out.println("Data inserted.");
		}
		else System.out.println("Nothing Inserted into Table.");
	}
	
	/**
	 * Internal function to print the result of an Insertion on the table Customers
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertCustomers(int resultSet) throws SQLException {
	    if(resultSet>0){
	    	query("SELECT * FROM Customers WHERE customerID = (SELECT MAX(customerID) FROM Customers)", TableNames.CUSTOMERS);
	    }
	}
	
	/**
	 * Internal function to print the result of an Insertion on the table Employees
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertEmployees(int resultSet) throws SQLException {
		if(resultSet>0){
			query("SELECT * FROM Employees WHERE employeeID = (SELECT MAX(employeeID) FROM Employees)",TableNames.EMPLOYEES);
		}
	}
	
	/**
	 * Internal function to print the result of an Insertion on the table Has
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertHas(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println("Inserted Data");
		}
	}
	
	/**
	 * Internal function to print the result of an Insertion on the table Inventory
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertInventory(int resultSet) throws SQLException {
	    if(resultSet>0){
	    	query("SELECT * FROM Inventory WHERE itemID = (SELECT MAX(itemID) FROM Inventory)", TableNames.INVENTORY);
	    }
	}
	
	/**
	 * Internal function to print the result of an Insertion on the table Stores
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertStores(int resultSet) throws SQLException {
	    if(resultSet>0){
	    	query("SELECT * FROM Stores WHERE locationNum = (SELECT MAX(locationNum) FROM Stores)", TableNames.STORES);
	    }
	}
	
	/*
	 * Internal function to print the result of an Insertion on the table Transactions
	 * 
	 * @param resultSet a ResultSet object returned from a query
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	private void resultInsertTransactions(int resultSet) throws SQLException {
	    if(resultSet>0){
	    	query("SELECT * FROM Transactions WHERE transactionID = (SELECT MAX(transactionID) FROM Transactions)", TableNames.TRANSACTIONS);
	    }
	}
	
	
	//Begin MODIFICATIONS***************************************************************************************************************************
	
	/**
	 * Function to print the result of a modification on a tuple in table Customers.  Only send values that are changed.  Any value that is not changed should be passed as null
	 * 
	 * @param customerID	The given customer's ID.  Must not be null
	 * @param name			The customer's name.  If null, it remains unchanged.
	 * @param phone			The customer's phone number.  If null, it remains unchanged.
	 * @param address		The customer's address.  If null, it remains unchanged.
	 * @param email			The customer's email. If null, it remains unchanged.
	 * @param discount		The customer's discount.  If null, it remains unchanged.
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	public void modifyCustomers(String customerID,String name, String phone, String address, String email, String discount) throws SQLException {
		ResultSet result = getData("SELECT * FROM Customers WHERE customerID = '" + customerID + '\'');
		
		
		String newName, newPhone, newAddr, newEmail, newDisc;
		newName = newPhone = newAddr = newEmail = newDisc = null;
		
		result.next();
		try{
			newName = result.getString("name");
			newPhone = result.getString("phone");
			newAddr = result.getString("address");
			newEmail = result.getString("email");
			newDisc = result.getString("discount");
			
			if(name!=null){
				newName = name;
			}
			
			
			if(phone!=null){
				newPhone = phone;
			}
			
			
			if(address!=null){
				newAddr = address;
			}
			
			
			if(email!=null){
				newEmail =email;
			}
			
			
			if(discount!=null){
				newDisc = discount;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("customerID\tname\t\t\tphone\t\taddress\t\t\temail\t\t\t\tCustomerSince\t\t\t\tdiscount");
		try {
			//updated data
			System.out.print(customerID+"\t\t");
		    System.out.print(newName+"\t");
		    System.out.print(newPhone+"\t");
		    System.out.print(newAddr+"\t");
		    System.out.print(newEmail+"\t\t");
			System.out.print(result.getTimestamp("customerSince").toString()+"\t\t\t");
		    System.out.println(newDisc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int res = statement.executeUpdate("UPDATE Customers SET name = '" + newName+"', phone = '"+newPhone+"', address = '"+newAddr+"', email = '"+newEmail+"', discount = '"+newDisc+"' WHERE customerID = " + customerID);
		if(res>0)System.out.println(res + " Rows Changed");
		else System.out.println("No rows changed");
	}
	
	/**
	 * Function to print the result of a modification on a tuple in table Employees.  
	 * Only send values that are changed.  Any value that is not changed should be passed as null, other than the employeeID.
	 * The employeeID must not be null.
	 * 
	 * @param employeeID	The given employee's ID.  Must not be null
	 * @param ssn			The employee's social security number.  If null, it remains unchanged.
	 * @param phone			The employee's name.  If null, it remains unchanged.
	 * @param address		The employee's rank.  If null, it remains unchanged.
	 * @param email			The employee's wage. If null, it remains unchanged.
	 * @param discount		The employee's location number.  If null, it remains unchanged.
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	public void modifyEmployees(String employeeID,String ssn, String name, String rank, String wage, String locationNum) throws SQLException {
		ResultSet result = getData("SELECT * FROM Employees WHERE employeeID = '" + employeeID + '\'');
		
		
		String newSSN, newName, newRank, newWage, newLoc;
		newSSN = newName = newRank = newWage = newLoc = null;
		
		result.next();
		try{
			newSSN = result.getString("ssn");
			newName = result.getString("name");
			newRank = result.getString("rank");
			newWage = result.getString("wage");
			newLoc = result.getString("locationNum");
			
			if(ssn!=null){
				newSSN = ssn;
			}
	
			if(name!=null){
				newName = name;
			}
	
			if(rank!=null){
				newRank = rank;
			}
	
			if(wage!=null){
				newWage =wage;
			}
	
			if(locationNum!=null){
				newLoc = locationNum;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("employeeID\tSSN\t\tName\t\t\tRank\t\t\tWage\t\tEmployeeSince\t\t\t\tlocationNum");
		try {
			if(newName.length()<8)newName+="    ";
			//updated data
			System.out.print(employeeID+"\t\t");
		    System.out.print(newSSN+"\t");
		    System.out.print(newName+"\t\t");
		    System.out.print(newRank+"\t\t");
		    System.out.print(newWage+"\t\t");
			System.out.print(result.getTimestamp("employeeSince").toString()+"\t\t\t");
		    System.out.println(newLoc);  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int res = statement.executeUpdate("UPDATE Employees SET ssn = '" + newSSN+"', name = '"+newName+"', rank = '"+newRank+"', wage = '"+newWage+"', locationNum = '"+newLoc+"' WHERE employeeID = " + employeeID);
		if(res>0)System.out.println(res + " Rows Changed");
		else System.out.println("No rows changed");
	}
	
	/**
	 * Function to print the result of a modification on a tuple in table Inventory.  
	 * Only send values that are changed.  Any value that is not changed should be passed as null, other than the itemID.
	 * The itemID must not be null.
	 * 
	 * @param itemID		The given item's ID.  Must not be null
	 * @param name			The item's name.  If null, it remains unchanged.
	 * @param type			The item's type.  If null, it remains unchanged.
	 * @param price			The item's price.  If null, it remains unchanged.
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	public void modifyInventory(String itemID,String name, String type, String price) throws SQLException {
		ResultSet result = getData("SELECT * FROM Inventory WHERE itemID = '" + itemID + '\'');
		
		String newItemName, newType, newPrice;
		newItemName = newType = newPrice = null;
		
		result.next();
		try{
			newItemName = result.getString("name");
			newType = result.getString("type");
			newPrice=result.getString("price");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(name != null){
			newItemName = name;
		}
		if(type!=null){
			newType = type;
		}
		if(price!=null){
			newPrice=price;
		}
		System.out.println("itemID\tName\t\tType\t\tPrice");
		System.out.print(itemID + "\t");
	    System.out.print(newItemName + '\t' );
	    System.out.print(newType + '\t' + '\t');
	    System.out.println(newPrice);
	    
	    int res = statement.executeUpdate("UPDATE Inventory SET name = '" + newItemName+"', type = '"+newType+"', price = '"+newPrice + "' WHERE itemID = " + itemID);
		if(res>0)System.out.println(res + " Rows Changed");
		else System.out.println("No rows changed");
		
	}
	
	/**
	 * Function to print the result of a modification on a tuple in table Stores.  
	 * Only send values that are changed.  Any value that is not changed should be passed as null, other than the locationNum.
	 * The itemID must not be null.
	 * 
	 * @param locationNum	The given store's ID.  Must not be null
	 * @param address			The store's address.  If null, it remains unchanged.
	 * @param manager			The store's general manager.  If null, it remains unchanged.
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	public void modifyStore(String locationNum ,String address, String manager) throws SQLException {
		ResultSet result = getData("SELECT * FROM Stores WHERE locationNum = '" + locationNum + '\'');
		
		String newAddress, newManager;
		newAddress = newManager = null;
		
		result.next();
		try{
			newAddress = result.getString("address");
			newManager = result.getString("managerName");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(address != null){
			newAddress = address;
		}
		if(manager!=null){
			newManager = manager;
		}
		System.out.println("locationNum\tAddress\t\t\t\tManagerName");
		System.out.print(locationNum + "\t\t");
	    System.out.print(newAddress + '\t'+ '\t' );
	    System.out.println(newManager);
	    
	    int res = statement.executeUpdate("UPDATE Stores SET address = '" + newAddress+"', managerName = '"+ newManager + "' WHERE locationNum = " + locationNum);
		if(res>0)System.out.println(res + " Rows Changed");
		else System.out.println("No rows changed");
		
	}
	
	/**
	 * Function to print the result of a modification on a tuple in table Transactions.  
	 * Only send values that are changed.  Any value that is not changed should be passed as null, other than the transactionID.
	 * The transactionID must not be null.
	 * 
	 * @param transactionID	The given transaction's ID.  Must not be null
	 * @param total			The total cost of the transaction.  If null, it remains unchanged.
	 * @param customerID	The Id of the customer involved in the transaction.  If null, it remains unchanged.
	 * @param locationNum	The store location number where the transaction occurred.
	 * @throws SQLException If the TableNames given in the query function points to the wrong table
	 */
	public void modifyTransaction(String transactionID,String total, String customerID, String locationNum) throws SQLException {
		ResultSet result = getData("SELECT * FROM Transactions WHERE transactionID = '" + transactionID + '\'');
		
		String newTotal, newCustomer, newLocation;
		newTotal = newCustomer = newLocation = null;
		Timestamp t = null;
		
		result.next();
		try{
			newTotal = result.getString("total");
			newCustomer = result.getString("customerID");
			newLocation=result.getString("locationNum");
			t=result.getTimestamp("timestamp");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(total != null){
			newTotal = total;
		}
		if(customerID!=null){
			newCustomer = customerID;
		}
		if(locationNum!=null){
			newLocation = locationNum;
		}
		System.out.println("transactionID\tTotal\t\tTimestamp\t\t\tCustomerID\tLocationNum");
		System.out.print(transactionID + "\t\t");
	    System.out.print(newTotal + "\t\t");
	    System.out.print(t.toString() + '\t' + '\t');
	    System.out.print(newCustomer + "\t\t");
	    System.out.println(newLocation);
	    
	    int res = statement.executeUpdate("UPDATE Transactions SET total = '" + newTotal+"', customerID = '"+newCustomer+"', locationNum = '"+newLocation + "' WHERE transactionID = " + transactionID);
		if(res>0)System.out.println(res + " Rows Changed");
		else System.out.println("No rows changed");
		
	}
	
	
	//Begin DELETIONS***************************************************************************************************************************
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Contain
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteContain(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Customers
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteCustomers(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Employees
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteEmployees(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Has
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteHas(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Inventory
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteInventory(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Stores
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteStores(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
	
	/**
	 * Prints out how many rows were deleted on a delete(query,tablename) call to the table Transactions
	 * 
	 * @param resultSet		The number of rows that were affected by the delete call
	 * @throws SQLException	If the tableName sent with the delete call was incorrect.
	 */
	private void resultDeleteTransactions(int resultSet) throws SQLException {
		if(resultSet>0){
			System.out.println(resultSet + " rows deleted");
		}
		else System.out.println("Nothing Deleted in Table.");
	}
}

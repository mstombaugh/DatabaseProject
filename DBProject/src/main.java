import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;


public class main {
	public enum MenuLevel{
		MAIN(0), DATAAGGREGATE(1), DATASPECIFIC(2), DATAINSERT(3), DATAMODIFY(4), DATADELETE(5), 
		CUSTOMERSEARCH(6), EMPLOYEESEARCH(7), STORESEARCH(8), INVENTORYSEARCH(9), TRANSACTIONSEARCH(10);
		private int value;
		private MenuLevel(int val){
				this.value = value;
		}
	}

	public static void main(String[] args) {
		Scanner cfgFile = null;
		Scanner in = new Scanner(System.in);
		try {
			cfgFile = new Scanner(new File("config"));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find config file");
			e.printStackTrace();
		}
		String username = cfgFile.next();
		String password = cfgFile.next();
		cfgFile.close();
		DBConnection con = new DBConnection("jdbc:mysql://db310.ckor58pfmu78.us-west-2.rds.amazonaws.com/electronicsdb?user="+username+"&password="+password);
		//con.query("SELECT * FROM Customers", DBConnection.TableNames.CUSTOMERS);
		int choice;
		MenuLevel menu = MenuLevel.MAIN;
		while(true){
			//main menu options
			System.out.println("**********Main Menu**********");
			System.out.println("1. View Data (aggregate)");
			System.out.println("2. View Data (specific store)");
			System.out.println("3. Insert Data");
			System.out.println("4. Modify Data");
			System.out.println("5. Delete Data");
			System.out.println("6. Special Queries");
			System.out.println("7. Exit");
			
			choice = in.nextInt();
			in.nextLine();
			
			switch(choice){
			case 1:
				menu=MenuLevel.DATAAGGREGATE;
				while(menu==MenuLevel.DATAAGGREGATE){
					//aggregate data
					System.out.println("**********Aggregate Data**********");
					System.out.println("1. Search Customers");
					System.out.println("2. Search Employees");
					System.out.println("3. Search Stores");
					System.out.println("4. Search Global Inventory");
					System.out.println("5. Search Transactions");
					System.out.println("6. Back");
					
					choice = in.nextInt();
					in.nextLine();
				//while(menu == MenuLevel.DATAAGGREGATE){
					switch(choice){
					case 1:
						menu=MenuLevel.CUSTOMERSEARCH;
						//*********************************************customer search*****************************************************************************
						while(menu == MenuLevel.CUSTOMERSEARCH){
							System.out.println("**********Customer Search**********");
							System.out.println("1. Seach by Customer ID");
							System.out.println("2. Seach by Customer Name");
							System.out.println("3. Search by Customer Phone Number");
							System.out.println("4. Search by Customer Address");
							System.out.println("5. Search by Customer Email");
							System.out.println("6. Search by Customer Discount");
							System.out.println("7. Back");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								System.out.println("**********Customer Search**********");
								System.out.print("Customer ID:");
								String customerID = in.nextLine();
								con.query("SELECT * FROM Customers WHERE customerID = \'" + customerID + '\'', DBConnection.TableNames.CUSTOMERS);
								break;
							case 2:
								System.out.println("**********Customer Search**********");
								System.out.print("Customer Name:");
								String customerName = in.nextLine();
								con.query("SELECT * FROM Customers WHERE name = \'" + customerName+ '\'', DBConnection.TableNames.CUSTOMERS);
								break;
							case 3:
								System.out.println("**********Customer Search**********");
								System.out.print("Customer Phone:");
								String customerPhone = in.nextLine();
								con.query("SELECT * FROM Customers WHERE phone = \'" + customerPhone+ '\'', DBConnection.TableNames.CUSTOMERS);
								break;
							case 4:
								System.out.println("**********Customer Search**********");
								System.out.print("Customer Address:");
								String customerAddr = in.nextLine();
								con.query("SELECT * FROM Customers WHERE address = \'" + customerAddr+ '\'', DBConnection.TableNames.CUSTOMERS);
								break;
							case 5:
								System.out.println("**********Customer Search**********");
								System.out.print("Customer Email:");
								String customerEmail = in.nextLine();
								con.query("SELECT * FROM Customers WHERE email = \'" + customerEmail+ '\'', DBConnection.TableNames.CUSTOMERS);
								break;
							case 6:
								System.out.println("**********Customer Search**********");
								System.out.print("Customer Discount:");
								String customerDiscount = in.nextLine();
								con.query("SELECT * FROM Customers WHERE discount = \'" + customerDiscount+ '\'', DBConnection.TableNames.CUSTOMERS);
								break;
							default:
								menu=MenuLevel.DATAAGGREGATE;
								break;
							}
						}
						break;
					case 2:
						menu=MenuLevel.EMPLOYEESEARCH;
						//*********************************************Employee Search************************************************************
						while(menu==MenuLevel.EMPLOYEESEARCH){
							System.out.println("**********Employee Search*********");
							System.out.println("1. Search by SSN");
							System.out.println("2. Search by Name");
							System.out.println("3. Search by Rank");
							System.out.println("4. Search by Wage");
							System.out.println("5. Search by Location Number");
							System.out.println("6. Back");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								System.out.println("**********Employee Search*********");
								System.out.print("Employee SSN:");
								String SSN = in.nextLine();
								con.query("SELECT * FROM Employees WHERE SSN = \'" + SSN+ '\'', DBConnection.TableNames.EMPLOYEES);
								break;
							case 2:
								System.out.println("**********Employee Search*********");
								System.out.print("Employee Name:");
								String name = in.nextLine();
								con.query("SELECT * FROM Employees WHERE name = \'" + name+ '\'', DBConnection.TableNames.EMPLOYEES);
								break;
							case 3:
								System.out.println("**********Employee Search*********");
								System.out.print("Employee Rank:");
								String rank = in .nextLine();
								con.query("SELECT * FROM Employees WHERE rank = \'" + rank+ '\'', DBConnection.TableNames.EMPLOYEES);
								break;
							case 4:
								System.out.println("**********Employee Search*********");
								System.out.print("Employee Wage:");
								String wage = in.nextLine();
								con.query("SELECT * FROM Employees WHERE wage = \'" + wage+ '\'', DBConnection.TableNames.EMPLOYEES);
								break;
							case 5:
								System.out.println("**********Employee Search*********");
								System.out.print("Employee Location Number");
								String locationNum = in.nextLine();
								con.query("SELECT * FROM Employees WHERE locationNum = \'" + locationNum+ '\'', DBConnection.TableNames.EMPLOYEES);
								break;
							case 6:
								menu=MenuLevel.DATAAGGREGATE;
							}
						}
						break;
					case 3:
						menu=MenuLevel.STORESEARCH;
						//*********************************************Store Search************************************************************
						while(menu==MenuLevel.STORESEARCH){
							System.out.println("**********Store Search*********");
							System.out.println("1. Search by Address");
							System.out.println("2. Search by Manager Name");
							System.out.println("3. Search by Location Number");
							System.out.println("4. Back");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								System.out.println("**********Store Search*********");
								System.out.print("Store Address:");
								String address = in.nextLine();
								con.query("SELECT * FROM Stores WHERE address = \'" + address + '\'', DBConnection.TableNames.STORES);
								break;
							case 2:
								System.out.println("**********Store Search*********");
								System.out.print("Store Manager Name:");
								String managerName = in.nextLine();
								con.query("SELECT * FROM Stores WHERE managerName = \'" + managerName + '\'', DBConnection.TableNames.STORES);
								break;
							case 3:
								System.out.println("**********Store Search*********");
								System.out.print("Store Location Number:");
								String locationNum = in.nextLine();
								con.query("SELECT * FROM Stores WHERE locationNum = \'" + locationNum + '\'', DBConnection.TableNames.STORES);
								break;
							default:
								menu=MenuLevel.DATAAGGREGATE;
								break;
								
							}
						}
						break;
					case 4:
						menu=MenuLevel.INVENTORYSEARCH;
						//*********************************************Inventory Search************************************************************
						while(menu==MenuLevel.INVENTORYSEARCH){
							System.out.println("**********Global Inventory Search*********");
							System.out.println("1. Search by Item Name");
							System.out.println("2. Search by Item Type");
							System.out.println("3. Search by Item Price");
							System.out.println("4. Search by Item ID");
							System.out.println("5. Back");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								System.out.println("**********Global Inventory Search*********");
								System.out.print("Item Name: ");
								String name = in.nextLine();
								con.query("SELECT * FROM Inventory WHERE name = \'" + name + '\'', DBConnection.TableNames.INVENTORY);
								break;
							case 2:
								System.out.println("**********Global Inventory Search*********");
								System.out.print("Item Type: ");
								String type = in.nextLine();
								con.query("SELECT * FROM Inventory WHERE type = \'" + type + '\'', DBConnection.TableNames.INVENTORY);
								break;
							case 3:
								System.out.println("**********Global Inventory Search*********");
								System.out.print("Item Price: ");
								String price = in.nextLine();
								con.query("SELECT * FROM Inventory WHERE price = \'" + price + '\'', DBConnection.TableNames.INVENTORY);
								break;
							case 4:
								System.out.println("**********Global Inventory Search*********");
								System.out.print("Item ID: ");
								String itemID = in.nextLine();
								con.query("SELECT * FROM Inventory WHERE itemID = \'" + itemID + '\'', DBConnection.TableNames.INVENTORY);
								break;
							default:
								menu=MenuLevel.DATAAGGREGATE;
							}
						}
						break;
					case 5:
						menu=MenuLevel.TRANSACTIONSEARCH;
						//*********************************************transaction Search************************************************************
						while(menu==MenuLevel.TRANSACTIONSEARCH){	
							System.out.println("**********Transaction Search*********");
							System.out.println("1. Search by Customer ID");
							System.out.println("2. Search by Location Number");
							System.out.println("3. Search by Total Cost");
							System.out.println("4. Search by Transaction ID");
							System.out.println("5. Back");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								System.out.println("**********Transaction Search*********");
								System.out.print("Customer ID:");
								String customerID = in.nextLine();
								con.query("SELECT * FROM Transactions WHERE customerID = \'" + customerID + '\'', DBConnection.TableNames.TRANSACTIONS);
								break;
							case 2:
								System.out.println("**********Transaction Search*********");
								System.out.print("Location Number:");
								String locationNum = in.nextLine();
								con.query("SELECT * FROM Transactions WHERE locationNum = \'" + locationNum + '\'', DBConnection.TableNames.TRANSACTIONS);
								break;
							case 3:
								System.out.println("**********Transaction Search*********");
								System.out.print("Total Cost:");
								String total = in.nextLine();
								con.query("SELECT * FROM Transactions WHERE total = \'" + total + '\'', DBConnection.TableNames.TRANSACTIONS);
								break;
							case 4:
								System.out.println("**********Transaction Search*********");
								System.out.print("Transaction ID:");
								String transactionID = in.nextLine();
								con.query("SELECT * FROM Transactions WHERE transactionID = \'" + transactionID + '\'', DBConnection.TableNames.TRANSACTIONS);
								break;
							default:
								menu=MenuLevel.DATAAGGREGATE;
							}
						}
						break;
					case 6:
						menu = MenuLevel.MAIN;
						break;
					default:
						break;	
					}
				}
				break;
			case 2:
				menu=MenuLevel.DATASPECIFIC;
				System.out.print("Store Location Number:");
				String locationNum = in.nextLine();
				while(menu == MenuLevel.DATASPECIFIC){
					//store specific data:
					
					
					System.out.println("**********Store Specific Data**********");
					System.out.println("1. Store Info");
					System.out.println("2. Store Employees");
					System.out.println("3. Store Inventory");
					System.out.println("4. Store Transactions");
					System.out.println("5. Back");
					
					choice = in.nextInt();
					in.nextLine();
					
					switch(choice){
					case 1:
						System.out.println("**********Store Info**********");
						con.query("SELECT * FROM Stores WHERE locationNum = \'" + locationNum + '\'', DBConnection.TableNames.STORES);
						con.query("SELECT * FROM (SELECT round(SUM(Inventory.price * Has.count),2) AS inventoryWorth, locationNum FROM Inventory JOIN Has ON Inventory.itemID = Has.itemID GROUP BY locationNum) t WHERE locationNum = " + locationNum, DBConnection.TableNames.NETWORTH);
						break;
					case 2:
						System.out.println("**********Store Employees**********");
						con.query("SELECT * FROM Employees WHERE locationNum = \'" + locationNum + '\'', DBConnection.TableNames.EMPLOYEES);
						break;
					case 3:
						System.out.println("**********Store Inventory**********");
						con.query("SELECT * FROM Has WHERE locationNum = \'" + locationNum + '\'', DBConnection.TableNames.HAS);
						break;
					case 4:
						System.out.println("**********Store Transactions**********");
						con.query("SELECT * FROM Transactions WHERE locationNum = \'" + locationNum + '\'', DBConnection.TableNames.TRANSACTIONS);
						break;
					case 5:
						menu = MenuLevel.MAIN;
					default:
						break;
					}
				}
				break;
			case 3:
				//insert data
				menu=MenuLevel.DATAINSERT;
				while(menu==MenuLevel.DATAINSERT){
					System.out.println("**********Insert Data**********");
					System.out.println("1. New Customer");
					System.out.println("2. New Employee");
					System.out.println("3. New Inventory Item");
					System.out.println("4. New Store");
					System.out.println("5. New Transaction");
					System.out.println("6. Back");
					
					choice = in.nextInt();
					in.nextLine();
					
					switch(choice){
					case 1:
						System.out.println("**********New Customer**********");
						System.out.print("Customer Name:");
						String cName = in.nextLine();
						
						System.out.print("\nCustomer Phone:");
						String cPhone = in.nextLine();
						
						System.out.print("\nCustomer Address:");
						String cAddress = in.nextLine();
						
						System.out.print("\nCustomer Email:");
						String cEmail = in.nextLine();
						
						System.out.print("\nCustomer Discount:");
						String cDiscount = in.nextLine();
						
						con.insert("INSERT INTO Customers(name,  phone, address, email, discount) VALUES(\'" + cName + '\'' + ',' + '\'' + cPhone + '\'' + ',' + '\'' + cAddress + '\'' + ',' + '\'' + cEmail + '\'' + ',' + '\'' + cDiscount + '\'' + ')',DBConnection.TableNames.CUSTOMERS);
						
						break;
					case 2:
						System.out.println("**********New Employee**********");
						System.out.print("Employee SSN:");
						String eSSN = in.nextLine();
						
						System.out.print("\nEmployee Name:");
						String eName = in.nextLine();
						
						System.out.print("\nEmployee Rank:");
						String eRank = in.nextLine();
						
						System.out.print("\nEmployee Wage:");
						String eWage = in.nextLine();
						
						System.out.print("\nEmployee Location Num:");
						String eLocation = in.nextLine();
						
						con.insert("INSERT INTO Employees(ssn,  name, rank, wage, locationNum) VALUES(\'" + eSSN + '\'' + ',' + '\'' + eName + '\'' + ',' + '\'' + eRank + '\'' + ',' + '\'' + eWage + '\'' + ',' + '\'' + eLocation + '\'' + ')',DBConnection.TableNames.EMPLOYEES);
						
						break;
					case 3:
						System.out.println("**********New Inventory Item**********");
						System.out.print("Item Name:");
						String iName = in.nextLine();
						
						System.out.print("\nItem Type:");
						String iType = in.nextLine();
						
						System.out.print("\nItem Price:");
						String iPrice = in.nextLine();
						
						con.insert("INSERT INTO Inventory(name,  type, price) VALUES(\'" + iName + '\'' + ',' + '\'' + iType + '\'' + ',' + '\'' + iPrice + '\'' + ')',DBConnection.TableNames.INVENTORY);
						break;
					case 4:
						System.out.println("**********New Store**********");
						System.out.print("Store Address:");
						String sAddr = in.nextLine();
						
						System.out.print("\nStore Manager Name:");
						String sMan = in.nextLine();
						
						con.insert("INSERT INTO Stores(address, managerName) VALUES(\'" + sAddr + '\'' + ',' + '\'' + sMan + '\'' + ')',DBConnection.TableNames.STORES);
						break;
					case 5:
						System.out.println("**********New Transaction**********");
						System.out.print("Transaction Total:");
						String tTot = in.nextLine();
						
						System.out.print("\nTransaction Customer ID:");
						String tCust = in.nextLine();
						
						System.out.print("\nTransaction Location Number:");
						String tLoc = in.nextLine();
						
						con.insert("INSERT INTO Transactions(total,  customerID, locationNum) VALUES(\'" + tTot + '\'' + ',' + '\'' + tCust + '\'' + ',' + '\'' + tLoc + '\''  + ')',DBConnection.TableNames.TRANSACTIONS);
						break;
					case 6:
						menu=MenuLevel.MAIN;
						break;
					default:
						menu=MenuLevel.MAIN;
						break;
					}
				}
				break;
			case 4:
				//modify data
				menu = MenuLevel.DATAMODIFY;
				while(menu == MenuLevel.DATAMODIFY){
					System.out.println("**********Modify Data**********");
					System.out.println("1. Modify Customer");
					System.out.println("2. Modify Employee");
					System.out.println("3. Modify Inventory Item");
					System.out.println("4. Modify Store");
					System.out.println("5. Modify Transaction");
					System.out.println("6. Back");
					
					choice = in.nextInt();
					in.nextLine();
					
					switch(choice){
					case 1:
						int go = 0;
						System.out.print("Customer ID:");
						String customerID = in.nextLine();
						
						String knowName, knowPhone, knowAddr, knowEmail, knowDisc;
						knowName = knowPhone = knowAddr = knowEmail = knowDisc = "No";
						
						while(go==0){
							
							System.out.println("**********Modify Customer**********");
							System.out.println("Select Changed Data:");
							System.out.println("Data\t\t\tChanged?");
							System.out.println("1. Customer Name\t"+knowName);
							System.out.println("2. Customer Phone\t"+knowPhone);
							System.out.println("3. Customer Address\t"+knowAddr);
							System.out.println("4. Customer Email\t"+knowEmail);
							System.out.println("5. Customer Discount\t"+knowDisc);
							System.out.println("6. Find Customer");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								if(knowName.equalsIgnoreCase("No")) knowName = "Yes";
								else knowName = "No";
								break;
							case 2:
								if(knowPhone.equalsIgnoreCase("No"))knowPhone = "Yes";
								else knowPhone = "No";
								break;
							case 3:
								if(knowAddr.equalsIgnoreCase("No"))knowAddr = "Yes";
								else knowAddr = "No";
								break;
							case 4:
								if(knowEmail.equalsIgnoreCase("No"))knowEmail = "Yes";
								else knowEmail = "Mo";
								break;
							case 5:
								if(knowDisc.equalsIgnoreCase("No"))knowDisc = "Yes";
								else knowDisc = "No";
								break;
							case 6:
								go=1;
							}
						}
						//use known changed data
						
						
						
						String newName, newPhone, newAddr, newEmail, newDisc;
						newName = newPhone = newAddr = newEmail = newDisc = null;
						
						
						try{
							if(knowName.equalsIgnoreCase("Yes")){
								System.out.print("New Name:");
								newName = in.nextLine();
								System.out.println();
							}
							
							
							if(knowPhone.equalsIgnoreCase("Yes")){
								System.out.print("New Phone:");
								newPhone = in.nextLine();
								System.out.println();
							}
							
							
							if(knowAddr.equalsIgnoreCase("Yes")){
								System.out.print("New Address:");
								newAddr = in.nextLine();
								System.out.println();
							}
							
							
							if(knowEmail.equalsIgnoreCase("Yes")){
								System.out.print("New email:");
								newEmail = in.nextLine();
								System.out.println();
							}
							
							
							if(knowDisc.equalsIgnoreCase("Yes")){
								System.out.print("New Discount:");
								newDisc = in.nextLine();
								System.out.println();
							}
							
						}catch(Exception e){
							e.printStackTrace();
						}
						try {
							//updated data
							con.modifyCustomers(customerID, newName, newPhone, newAddr, newEmail, newDisc);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						break;
					case 2: //modify employee
						int go2 = 0;
						System.out.print("Employee ID:");
						String employeeID = in.nextLine();
						
						String knowSSN, knowEmpName, knowRank, knowWage, knowLoc;
						knowSSN = knowEmpName = knowRank = knowWage = knowLoc = "No";
						
						while(go2==0){
							
							System.out.println("**********Modify Customer**********");
							System.out.println("Select Changed Data:");
							System.out.println("Data\t\t\t\tChanged?");
							System.out.println("1. Employee SSN\t\t\t"+knowSSN);
							System.out.println("2. Employee Name\t\t"+knowEmpName);
							System.out.println("3. Employee Rank\t\t"+knowRank);
							System.out.println("4. Employee Wage\t\t"+knowWage);
							System.out.println("5. Employee Location Number\t"+knowLoc);
							System.out.println("6. Find Customer");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								if(knowSSN.equalsIgnoreCase("No")) knowSSN = "Yes";
								else knowSSN = "No";
								break;
							case 2:
								if(knowEmpName.equalsIgnoreCase("No"))knowEmpName = "Yes";
								else knowEmpName = "No";
								break;
							case 3:
								if(knowRank.equalsIgnoreCase("No"))knowRank = "Yes";
								else knowRank = "No";
								break;
							case 4:
								if(knowWage.equalsIgnoreCase("No"))knowWage = "Yes";
								else knowWage = "Mo";
								break;
							case 5:
								if(knowLoc.equalsIgnoreCase("No"))knowLoc = "Yes";
								else knowLoc = "No";
								break;
							case 6:
								go2=1;
							}
						}

						String newSSN, newEmpName, newRank, newWage, newLoc;
						newSSN = newEmpName = newRank = newWage = newLoc = null;
						
						
						try{
							if(knowSSN.equalsIgnoreCase("Yes")){
								System.out.print("New SSN:");
								newSSN = in.nextLine();
								System.out.println();
							}
							
							
							if(knowEmpName.equalsIgnoreCase("Yes")){
								System.out.print("New Name:");
								newName = in.nextLine();
								System.out.println();
							}
							
							
							if(knowRank.equalsIgnoreCase("Yes")){
								System.out.print("New Rank:");
								newRank = in.nextLine();
								System.out.println();
							}
							
							
							if(knowWage.equalsIgnoreCase("Yes")){
								System.out.print("New Wage:");
								newWage = in.nextLine();
								System.out.println();
							}
							
							
							if(knowLoc.equalsIgnoreCase("Yes")){
								System.out.print("New Location Number:");
								newLoc = in.nextLine();
								System.out.println();
							}
							
						}catch(Exception e){
							e.printStackTrace();
						}
						try {
							//updated data
							con.modifyEmployees(employeeID, newSSN, newEmpName, newRank, newWage, newLoc);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						int go3 = 0;
						System.out.print("Item ID:");
						String itemID = in.nextLine();
						
						String knowItemName, knowType, knowPrice;
						knowItemName = knowType = knowPrice = "No";
						
						while(go3==0){
							
							System.out.println("**********Modify Inventory Item**********");
							System.out.println("Select Changed Data:");
							System.out.println("Data\t\t\tChanged?");
							System.out.println("1. Item Name\t\t"+knowItemName);
							System.out.println("2. Item Type\t\t"+knowType);
							System.out.println("3. Item price\t\t"+knowPrice);
							System.out.println("4. Find Item");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								if(knowItemName.equalsIgnoreCase("No")) knowItemName = "Yes";
								else knowItemName = "No";
								break;
							case 2:
								if(knowType.equalsIgnoreCase("No"))knowType = "Yes";
								else knowType = "No";
								break;
							case 3:
								if(knowPrice.equalsIgnoreCase("No"))knowPrice = "Yes";
								else knowPrice = "No";
								break;
							case 4:
								go3=1;
							}
						}

						String newItemName, newType, newPrice;
						newItemName = newType = newPrice = null;
						
						
						try{
							if(knowItemName.equalsIgnoreCase("Yes")){
								System.out.print("New Item Name:");
								newItemName = in.nextLine();
								System.out.println();
							}
							
							
							if(knowType.equalsIgnoreCase("Yes")){
								System.out.print("New Type:");
								newType = in.nextLine();
								System.out.println();
							}
							
							
							if(knowPrice.equalsIgnoreCase("Yes")){
								System.out.print("New Price:");
								newPrice = in.nextLine();
								System.out.println();
							}
							
						}catch(Exception e){
							e.printStackTrace();
						}
						try {
							//updated data
							con.modifyInventory(itemID, newItemName, newType, newPrice);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4://store
						int go4 = 0;
						System.out.print("Store ID:");
						String storeID = in.nextLine();
						
						String knowAddress, knowManager;
						knowAddress = knowManager = "No";
						
						while(go4==0){
							
							System.out.println("**********Modify Store**********");
							System.out.println("Select Changed Data:");
							System.out.println("Data\t\t\tChanged?");
							System.out.println("1. Store Address\t"+knowAddress);
							System.out.println("2. Store Manager\t"+knowManager);
							System.out.println("3. Find Store");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								if(knowAddress.equalsIgnoreCase("No")) knowAddress = "Yes";
								else knowAddress = "No";
								break;
							case 2:
								if(knowManager.equalsIgnoreCase("No"))knowManager = "Yes";
								else knowManager = "No";
								break;
							case 3:
								go4=1;
							}
						}

						String newAddress, newManager;
						newAddress = newManager = null;
						
						
						try{
							if(knowAddress.equalsIgnoreCase("Yes")){
								System.out.print("New Store Address:");
								newAddress = in.nextLine();
								System.out.println();
							}
							
							
							if(knowManager.equalsIgnoreCase("Yes")){
								System.out.print("New Store Manager:");
								newManager = in.nextLine();
								System.out.println();
							}
							
						}catch(Exception e){
							e.printStackTrace();
						}
						try {
							//updated data
							con.modifyStore(storeID, newAddress, newManager);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						break;
					case 5://transaction
						int go5 = 0;
						System.out.print("Transaction ID:");
						String transactionID = in.nextLine();
						
						String knowTotal, knowCustomerID, knowLocation;
						knowTotal = knowCustomerID = knowLocation = "No";
						
						while(go5==0){
							
							System.out.println("**********Modify Inventory Item**********");
							System.out.println("Select Changed Data:");
							System.out.println("Data\t\t\t\tChanged?");
							System.out.println("1. Transaction Total\t\t\t"+knowTotal);
							System.out.println("2. Transaction Customer ID\t\t"+knowCustomerID);
							System.out.println("3. Transaction Location Number\t\t"+knowLocation);
							System.out.println("4. Find Transaction");
							
							choice = in.nextInt();
							in.nextLine();
							
							switch(choice){
							case 1:
								if(knowTotal.equalsIgnoreCase("No")) knowTotal = "Yes";
								else knowTotal = "No";
								break;
							case 2:
								if(knowCustomerID.equalsIgnoreCase("No"))knowCustomerID = "Yes";
								else knowCustomerID = "No";
								break;
							case 3:
								if(knowLocation.equalsIgnoreCase("No"))knowLocation = "Yes";
								else knowLocation = "No";
								break;
							case 4:
								go5=1;
							}
						}

						String newTotal, newCustomer, newLocation;
						newTotal = newCustomer = newLocation = null;
						
						
						try{
							if(knowTotal.equalsIgnoreCase("Yes")){
								System.out.print("New Total:");
								newTotal = in.nextLine();
								System.out.println();
							}
							
							
							if(knowCustomerID.equalsIgnoreCase("Yes")){
								System.out.print("New Customer ID:");
								newCustomer = in.nextLine();
								System.out.println();
							}
							
							
							if(knowLocation.equalsIgnoreCase("Yes")){
								System.out.print("New Location Number:");
								newLocation = in.nextLine();
								System.out.println();
							}
							
						}catch(Exception e){
							e.printStackTrace();
						}
						try {
							//updated data
							con.modifyTransaction(transactionID, newTotal, newCustomer, newLocation);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case 6:
						menu=MenuLevel.MAIN;
						break;
					default:
						menu=MenuLevel.MAIN;
						break;
					}
				}
				break;
			case 5://delete data
				menu=MenuLevel.DATADELETE;
				while(menu==MenuLevel.DATADELETE){
					System.out.println("**********Delete Data**********");
					System.out.println("1. Delete Customer");
					System.out.println("2. Delete Employee");
					System.out.println("3. Delete Store");
					System.out.println("4. Delete Inventory");
					System.out.println("5. Delete Transaction");
					System.out.println("6. Back");
					
					choice = in.nextInt();
					in.nextLine();
					
					switch(choice){
					case 1://delete customer
						System.out.println("**********Delete Customer**********");
						System.out.print("Customer ID:");
						String customerID = in.nextLine();
						System.out.println();
						con.delete("DELETE FROM Customers WHERE customerID = " + customerID, DBConnection.TableNames.CUSTOMERS);
						break;
					case 2://delete employee
						System.out.println("**********Delete Employee**********");
						System.out.print("Employee ID:");
						String employeeID = in.nextLine();
						System.out.println();
						con.delete("DELETE FROM Employees WHERE employeeID = " + employeeID, DBConnection.TableNames.EMPLOYEES);
						break;
					case 3://delete store
						System.out.println("**********Delete Store**********");
						System.out.print("Store Location Number:");
						locationNum = in.nextLine();
						System.out.println();
						con.delete("DELETE FROM Stores WHERE locationNum = " + locationNum, DBConnection.TableNames.STORES);
						break;
					case 4://delete inventory
						System.out.println("**********Delete Item**********");
						System.out.print("Inventory Item ID:");
						String itemID = in.nextLine();
						System.out.println();
						con.delete("DELETE FROM Inventory WHERE itemID = " + itemID, DBConnection.TableNames.INVENTORY);
						break;
					case 5://delete transaction
						System.out.println("**********Delete Transaction**********");
						System.out.print("Transaction ID:");
						String transactionID = in.nextLine();
						System.out.println();
						con.delete("DELETE FROM Transactions WHERE transactionID = " + transactionID, DBConnection.TableNames.TRANSACTIONS);
						break;
					case 6://back
						menu=MenuLevel.MAIN;
						break;
					default:
						menu=MenuLevel.MAIN;
						break;
					}
				}
				
				break;
			case 6:
				System.out.println("**********Special Queries**********");
				System.out.println("1. Find all employees above a certain wage");
				System.out.println("2. Find all customers with the maximum or minimum discounts");
				System.out.println("");
			case 7:
				con.close();
				return;
			default:
				return;
			}
		}
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
			// TODO Auto-generated catch block
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
			System.out.println("6. Exit");
			
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
				
				break;
			case 4:
				//modify data
				
				break;
			case 5:
				//delete data
				
				break;
			case 6:
				con.close();
				return;
			default:
				return;
			}
		}
	}

}

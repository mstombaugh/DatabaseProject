import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		Scanner cfgFile = null;
		try {
			cfgFile = new Scanner(new File("config"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find config file");
			e.printStackTrace();
		}
		String username = cfgFile.next();
		String password = cfgFile.next();
		DBConnection con = new DBConnection("jdbc:mysql://db310.ckor58pfmu78.us-west-2.rds.amazonaws.com/electronicsdb?user="+username+"&password="+password);
		con.query("SELECT * FROM Customers", DBConnection.TableNames.CUSTOMERS);
	}

}

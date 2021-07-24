package ZohoL3;
import java.util.*;

class Customer{
	static int count = 0;
	HashMap<String,String> cInfo = new HashMap<>(3);
	static HashMap<String,HashMap<String,String>> cMap = new HashMap<>();
	
	Customer(){}
	
	Customer(String id, String name, String pass, String age){
		++count;
		this.cInfo.put("name", name);
		this.cInfo.put("pass", pass);
		this.cInfo.put("age",age);
		this.cInfo.put("id", id);
		this.cMap.put(id, cInfo);
	}
	
	//print customer info
	public void getCustomerDetails(String id){
//		if(cMap.containsKey(id)){
			String name = cMap.get(id).get("name");
			String pass = cMap.get(id).get("pass");
			String age = cMap.get(id).get("age");
			
			System.out.println("Customer ID :" + id);
			System.out.println("Name :" + name);
			System.out.println("Pass :" + pass);
			System.out.println("Age :" + age);
			
//		}
	}
	
	public boolean loginCustomer(String id, String pass) {
		if(cMap.containsKey(id)) {
			if(cMap.get(id).get("pass").equals(pass)) {
				System.out.println("Succesfully Logged In");
				return true;
			}else {
				System.out.println("Password Incorrect");
				return false;
			}
		}else {
			System.out.println("Customer doesn't exist");
			return true;
		}
	}
	
}

class Driver{
	static int count = 0;
	HashMap<String,String> dInfo = new HashMap<>(3);
	static HashMap<String,HashMap<String,String>> dMap = new HashMap<>();
	
	Driver(){}
	
	Driver(String id, String name, String pass, String age){
		++count;
		this.dInfo.put("name", name);
		this.dInfo.put("pass", pass);
		this.dInfo.put("age",age);
		this.dInfo.put("id", id);
		this.dMap.put(id, dInfo);
	}
	
	//print customer info
	public void getDriverDetails(String id){
			String name = dMap.get(id).get("name");
			String pass = dMap.get(id).get("pass");
			String age = dMap.get(id).get("age");
			
			System.out.println("Driver ID :" + id);
			System.out.println("Name :" + name);
			System.out.println("Pass :" + pass);
			System.out.println("Age :" + age);		
	}
	
	public void loginDriver(String id, String pass) {
		if(dMap.containsKey(id)) {
			if(dMap.get(id).get("pass").equals(pass)) {
				System.out.println("Succesfully Logged In");
			}else {
				System.out.println("Password Incorrect");
			}
		}else {
			System.out.println("Driver doesn't exist");
		}
	}
	
	public void historyDetails(){
		
	}
}

public class ZulaCab{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		double zulaComission = 0;
		
		//putting dummy values - Customer
//		System.out.println("CUSTOMERS");
		Customer c1 = new Customer("1","aaa","111","25");
		Customer c2 = new Customer("2","bbb","222","36");
		Customer c3 = new Customer("3","ccc","333","31");S
		Customer c4 = new Customer("4","ddd","444","28");
		
		//putting dummy values - Driver
		Driver d1 = new Driver("1","ww","55","25");
		Driver d2 = new Driver("2","xx","66","36");
		Driver d3 = new Driver("3","yy","77","31");
		Driver d4 = new Driver("4","zz","88","28");
		
//		Locations Details
//		System.out.println("Input all Location Details");
		HashMap<String,Integer> locations = new HashMap<String,Integer>();
		locations.put("A", 0);
		locations.put("B", 15);
		locations.put("C", 4);
		locations.put("D", 7);
		locations.put("E", 23);
		locations.put("F", 9);
		locations.put("G", 18);
		locations.put("H", 20);
			
//		initial Cab Positions
		HashMap<String,ArrayList<Integer>> positions = new HashMap<String,ArrayList<Integer>>();
		positions.put("D",new ArrayList<>(Arrays.asList(1)));
		positions.put("G",new ArrayList<>(Arrays.asList(2)));
		positions.put("H",new ArrayList<>(Arrays.asList(3)));
		positions.put("A",new ArrayList<>(Arrays.asList(4)));
		
		int mainOption;
		do {
			System.out.println("Welcome to ZULA");
			System.out.println("1. Cab Driver Login");
			System.out.println("2. Customer Login");
			System.out.println("3. Administration Login");
			System.out.println("4. Quit");
			
			System.out.println("Please Choose a Service: ");
			int choice = scan.nextInt();
			int option;
			switch(choice) {
				case 1: Driver obj1 = new Driver();
						do {
							System.out.println(" Login Page ");
							String id = scan.next();
							String pass = scan.next();
							obj1.loginDriver(id,pass);
							option = scan.nextInt();
						}while(option != -1);
						break;
				case 2: Customer obj2 = new Customer();
						boolean features = false;
						do {
							System.out.println("1. Login");
							System.out.println("2. Register");
							System.out.println("Press -1 to exit");
							option = scan.nextInt();
							//Login Customer
							if(option == 1) {
								System.out.println(" Login Page ");
								String id = scan.next();
								String pass = scan.next();
								features = obj2.loginCustomer(id,pass);
							}else if(option == 2) { //Register Customer
								System.out.println(" Register Page ");
								String id = scan.next();
								String name = scan.next();
								String pass = scan.next();
								String age = scan.next();
								Customer c = new Customer(id, name, pass, age);
								//when one user is created break from the loop.
								System.out.println("Customer Details");
								c.getCustomerDetails(id);
								features = true;
							}
							//calling the Zula Cab Features
							if(features == true) {
								System.out.println("Book a ZULA Cab !");
								System.out.println("Do you want to Book a Cab? Press 1");
								int book = scan.nextInt();
								if(book!=1) {
									option = -1;
								}else {
									System.out.println(positions);
									System.out.println("Enter Pickup location: ");
									String source = scan.next();
									System.out.println("Enter Drop location: ");
									String destination = scan.next();
									int calculatedFare = 0, totalDistance;
									if(positions.containsKey(source)) {
										totalDistance = Math.abs(locations.get(destination) -  locations.get(source));
										calculatedFare = totalDistance * 10;
										zulaComission += calculatedFare *  0.3;
										int cabId = positions.get(source).remove(0);
										if(positions.containsKey(destination)) {
											positions.get(destination).add(cabId);
										}else {
											positions.put("A",new ArrayList<>(Arrays.asList(cabId)));
										}
										
										System.out.println(positions);
										System.out.println("Total fare : " + calculatedFare );
										System.out.println("Total distance : " +totalDistance);
									}
								}
							}
						}while(option != -1);
						break;
				case 3: //Administration Login
					break;
				case 4: System.out.println("Successfully Exited!");
					break;
				default: 
					System.out.println("Choose a valid option.");
			}
			System.out.println("Enter any number to return to Main Menu otherwise enter -1");
			mainOption = scan.nextInt();
		}while(mainOption != -1);
		
		System.out.println("ZULA COMISSON: "+ zulaComission);
	}
}

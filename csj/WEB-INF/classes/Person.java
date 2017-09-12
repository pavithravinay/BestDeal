public class Person implements java.io.Serializable{
	
	private String userId;
	private String password;
	private String fName;
	private String lName;
	private String email;
	//private Address address;
	private String contactNumber;
	private String role;

	public Person(){

	}

	public Person(String userId, String password, String fName, String lName, String email, String contactNumber, String role){
		this.userId = userId;
		this.password = password;
		this.fName = fName; 
		this.lName  = lName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;

	}

	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getFName(){
		return fName;
	}
	public void setFName(String fName){
		this.fName = fName;
	}
	public String getlName(){
		return lName;
	}
	public void setlName(String lName){
		this.lName = lName;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getContactNumber(){
		return contactNumber;
	}
	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}
	public String getRole(){
		return role;
	}
	public void setRole(String role){
		this.role = role;
	}

	public String toString(){
		return userId+" "+password+" "+fName+" "+lName+" "+email+" "+contactNumber+" "+role;
	}
	public Person getDefaultCustomerLoginCredentials(){
		Person person1 = new Person("123", "123", "John", "Doe", "jdoe@g.com", "123-456-6666", "Customer" );
		return person1;

	}

	public static Person getDefaultManagerLoginCredentials(){
		Person person1 = new Person("sm1", "sm1", "Sandra", "McCarthy", "sandram@g.com", "123-123-1230", "Sales Manager" );
		return person1;
	}

	public static Person getDefaultSalesmanLoginCredentials(){
		Person person1 = new Person("sales", "sales", "Robert", "Scott", "robscott@g.com", "333-333-3333", "Salesman" );
		return person1;
	}


}
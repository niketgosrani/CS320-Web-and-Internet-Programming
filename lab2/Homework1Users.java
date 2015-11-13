package lab2;

public class Homework1Users {

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String reEnterpassword;
	private String zipcode;
	
	public Homework1Users(String firstname , String lastname , String email , String password , String reEnterpassword , String zipcode){
	
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.reEnterpassword = reEnterpassword;
		this.zipcode = zipcode;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnterpassword() {
		return reEnterpassword;
	}

	public void setReEnterpassword(String reEnterpassword) {
		this.reEnterpassword = reEnterpassword;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}

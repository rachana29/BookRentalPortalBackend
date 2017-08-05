package com.alacriti.bookRental.model.vo;

//import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Login {
	 
	 
		 public int User_id;
		public String User_name,Password,User_type,email_id,mobile_number;
		
		 
		 public Login(int User_id, String User_name, String Password, String User_type,
					String email_id, String mobile_number) {
				super();
				this.User_id=User_id;
				this.User_name=User_name;
				this.Password=Password;
				this.User_type=User_type;
				this.email_id=email_id;
				this.mobile_number=mobile_number;	
	 }
		 public Login() {
			// TODO Auto-generated constructor stub
		}
		public int getUser_id() {
			return User_id;
		}

		public void setUser_id(int user_id) {
			User_id = user_id;
		}

		public String getUser_name() {
			return User_name;
		}

		public void setUser_name(String user_name) {
			User_name = user_name;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getUser_type() {
			return User_type;
		}

		public void setUser_type(String user_type) {
			User_type = user_type;
		}

		public String getEmail_id() {
			return email_id;
		}

		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}

		public String getMobile_number() {
			return mobile_number;
		}

		public void setMobile_number(String mobile_number) {
			this.mobile_number = mobile_number;
		}
		 

}

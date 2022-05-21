package com.ssafy.happy.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {
		String id;
		String password;
		String name;
		String email;
		String registDate;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRegistDate() {
			return registDate;
		}
		public void setRegistDate(String registDate) {
			this.registDate = registDate;
		}
		
		@Override
		public String toString() {
			return "UserDto [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
					+ ", registDate=" + registDate + "]";
		}
		
		
	}

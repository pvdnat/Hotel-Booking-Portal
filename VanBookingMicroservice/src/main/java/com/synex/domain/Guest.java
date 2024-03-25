package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="guest")
public class Guest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int 	guestId;

	private String 	firstName;
	private String 	lastName;
	private String 	gender;
	private int 	age;
	
	public Guest() {} 
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", age=" + age + "]";
	}
	
	
}

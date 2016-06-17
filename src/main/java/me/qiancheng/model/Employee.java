package me.qiancheng.model;

public class Employee {

	private int id;
	private int age;
	private String name;
	private String mail;
	
	public Employee() {}
	
	public Employee(String name, int age, String mail) {
		this.age = age;
		this.name = name;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + age + "\t" + mail;
	}
	
}
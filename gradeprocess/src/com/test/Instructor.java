package com.test;

//강사 데이터 타입
public class Instructor {

	private String instructor_id, inst_name, phone;
	private int inst_pw;

	public String getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}

	public String getInst_name() {
		return inst_name;
	}

	public void setInst_name(String inst_name) {
		this.inst_name = inst_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getInst_pw() {
		return inst_pw;
	}

	public void setInst_pw(int inst_pw) {
		this.inst_pw = inst_pw;
	}

}

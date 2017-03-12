package com.test;

//수강생 데이터 타입
public class Students {
   
   private int st_pw;
   private String student_id, st_name, phone, register_date;
   
   public int getSt_pw() {
      return st_pw;
   }
   public void setSt_pw(int st_pw) {
      this.st_pw = st_pw;
   }
   public String getStudent_id() {
      return student_id;
   }
   public void setStudent_id(String student_id) {
      this.student_id = student_id;
   }
   public String getSt_name() {
      return st_name;
   }
   public void setSt_name(String st_name) {
      this.st_name = st_name;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getRegister_date() {
      return register_date;
   }
   public void setRegister_date(String register_date) {
      this.register_date = register_date;
   }
   /*
   @Override
   public String toString() {
      return String.format("%s / %s / %s / %s", this.sid, this.date, this.hour, this.content);
   }
   */

}
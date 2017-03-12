package com.test;

public class AllList {
	
	// 과정ID, 과정명, 과목ID, 과목명, 과목명id, 과정 시작, 과정 종료, 교재명
	private String course_id, cou_name, subject_id, sub_name, subjectName_id, cou_start_date, cou_end_date, text_name,
	// 강의실명, 과정명id, 강의실id, 출결배점, 필기배점, 실기배점, 출결점수, 필기점수, 실기점수, 강사id, 강사명, 강사전화번호
	class_name, courseName_id, classroom_id, po_attend, po_write, po_practice, gr_attend, gr_write, gr_practice, instructor_id, inst_name, inst_phone,
	// 과목 시작일, 과목 종료일, 수강생id, 수강생명, 수강생전화번호, 수강생등록일,
	sub_start_date, sub_end_date, student_id, st_name, st_phone, register_date,
	// 수료여부, 중도탈락 날짜, 교재id, 출판사, 강의진행여부
	fin, mid_fail_date, textbook_id, publisher, progress_ox,
	// 시험날짜, 시험문제, 성적등록여부, 시험등록여부, 강의가능과목, 교재이미지, 시험문제(.zip)
	exam_date, examination, grade_ox, exam_ox, teachable_sub, text_img, exam_zip,
	// 관리자 아이디
	admin_id;

	// 강사 암호, 수강생 암호, 강의실총원, 수강인원수, 과목수, 과정수, 과정탈락인원수, 수료인원수, 강사스케줄, 관리자 패스워드, 삭제 가능 여부
	private int inst_pw, st_pw, class_total, st_total, sub_count, cou_count, mid_fail_count, fin_count, schedule, admin_pw, delCheck,
	// ROW_NUMBER
	rn;
	
	public String getExam_zip() {
		return exam_zip;
	}

	public void setExam_zip(String exam_zip) {
		this.exam_zip = exam_zip;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCou_name() {
		return cou_name;
	}

	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}

	public String getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getSubjectName_id() {
		return subjectName_id;
	}

	public void setSubjectName_id(String subjectName_id) {
		this.subjectName_id = subjectName_id;
	}

	public String getCou_start_date() {
		return cou_start_date;
	}

	public void setCou_start_date(String cou_start_date) {
		this.cou_start_date = cou_start_date;
	}

	public String getCou_end_date() {
		return cou_end_date;
	}

	public void setCou_end_date(String cou_end_date) {
		this.cou_end_date = cou_end_date;
	}

	public String getText_name() {
		return text_name;
	}

	public void setText_name(String text_name) {
		this.text_name = text_name;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getCourseName_id() {
		return courseName_id;
	}

	public void setCourseName_id(String courseName_id) {
		this.courseName_id = courseName_id;
	}

	public String getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(String classroom_id) {
		this.classroom_id = classroom_id;
	}

	public String getPo_attend() {
		return po_attend;
	}

	public void setPo_attend(String po_attend) {
		this.po_attend = po_attend;
	}

	public String getPo_write() {
		return po_write;
	}

	public void setPo_write(String po_write) {
		this.po_write = po_write;
	}

	public String getPo_practice() {
		return po_practice;
	}

	public void setPo_practice(String po_practice) {
		this.po_practice = po_practice;
	}

	public String getGr_attend() {
		return gr_attend;
	}

	public void setGr_attend(String gr_attend) {
		this.gr_attend = gr_attend;
	}

	public String getGr_write() {
		return gr_write;
	}

	public void setGr_write(String gr_write) {
		this.gr_write = gr_write;
	}

	public String getGr_practice() {
		return gr_practice;
	}

	public void setGr_practice(String gr_practice) {
		this.gr_practice = gr_practice;
	}

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

	public String getInst_phone() {
		return inst_phone;
	}

	public void setInst_phone(String inst_phone) {
		this.inst_phone = inst_phone;
	}

	public String getSub_start_date() {
		return sub_start_date;
	}

	public void setSub_start_date(String sub_start_date) {
		this.sub_start_date = sub_start_date;
	}

	public String getSub_end_date() {
		return sub_end_date;
	}

	public void setSub_end_date(String sub_end_date) {
		this.sub_end_date = sub_end_date;
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

	public String getSt_phone() {
		return st_phone;
	}

	public void setSt_phone(String st_phone) {
		this.st_phone = st_phone;
	}

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getMid_fail_date() {
		return mid_fail_date;
	}

	public void setMid_fail_date(String mid_fail_date) {
		this.mid_fail_date = mid_fail_date;
	}

	public String getTextbook_id() {
		return textbook_id;
	}

	public void setTextbook_id(String textbook_id) {
		this.textbook_id = textbook_id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getProgress_ox() {
		return progress_ox;
	}

	public void setProgress_ox(String progress_ox) {
		this.progress_ox = progress_ox;
	}

	public String getExam_date() {
		return exam_date;
	}

	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public String getGrade_ox() {
		return grade_ox;
	}

	public void setGrade_ox(String grade_ox) {
		this.grade_ox = grade_ox;
	}

	public String getExam_ox() {
		return exam_ox;
	}

	public void setExam_ox(String exam_ox) {
		this.exam_ox = exam_ox;
	}

	public String getTeachable_sub() {
		return teachable_sub;
	}

	public void setTeachable_sub(String teachable_sub) {
		this.teachable_sub = teachable_sub;
	}

	public String getText_img() {
		return text_img;
	}

	public void setText_img(String text_img) {
		this.text_img = text_img;
	}

	public int getInst_pw() {
		return inst_pw;
	}

	public void setInst_pw(int inst_pw) {
		this.inst_pw = inst_pw;
	}

	public int getSt_pw() {
		return st_pw;
	}

	public void setSt_pw(int st_pw) {
		this.st_pw = st_pw;
	}

	public int getClass_total() {
		return class_total;
	}

	public void setClass_total(int class_total) {
		this.class_total = class_total;
	}

	public int getSt_total() {
		return st_total;
	}

	public void setSt_total(int st_total) {
		this.st_total = st_total;
	}

	public int getSub_count() {
		return sub_count;
	}

	public void setSub_count(int sub_count) {
		this.sub_count = sub_count;
	}

	public int getCou_count() {
		return cou_count;
	}

	public void setCou_count(int cou_count) {
		this.cou_count = cou_count;
	}

	public int getMid_fail_count() {
		return mid_fail_count;
	}

	public void setMid_fail_count(int mid_fail_count) {
		this.mid_fail_count = mid_fail_count;
	}

	public int getFin_count() {
		return fin_count;
	}

	public void setFin_count(int fin_count) {
		this.fin_count = fin_count;
	}

	public int getSchedule() {
		return schedule;
	}

	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public int getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(int admin_pw) {
		this.admin_pw = admin_pw;
	}

	public int getDelCheck() {
		return delCheck;
	}

	public void setDelCheck(int delCheck) {
		this.delCheck = delCheck;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	
}

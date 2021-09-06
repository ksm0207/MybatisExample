package m.vo;

public class DepartmentsVO {
	
	String department_id;
	String department_name;
	LocationVO lvo;
	
	
	public String getDepartment_id() {
		return department_id;
	}
	
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	
	public String getDepartment_name() {
		return department_name;
	}
	
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	public LocationVO getLvo() {
		return lvo;
	}
	
	public void setLvo(LocationVO lvo) {
		this.lvo = lvo;
	}
	
	

}

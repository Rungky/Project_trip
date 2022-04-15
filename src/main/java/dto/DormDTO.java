package dto;

public class DormDTO {
	private int dorm_no; 
	private String dorm_name; 
	private String dorm_contents; 
	private String dorm_addr; 
	private String dorm_picture; 
	private int like_cnt;
	private int dorm_category_no;
	
	
	public int getDorm_no() {
		return dorm_no;
	}
	public void setDorm_no(int dorm_no) {
		this.dorm_no = dorm_no;
	}
	public String getDorm_name() {
		return dorm_name;
	}
	public void setDorm_name(String dorm_name) {
		this.dorm_name = dorm_name;
	}
	public String getDorm_contents() {
		return dorm_contents;
	}
	public void setDorm_contents(String dorm_contents) {
		this.dorm_contents = dorm_contents;
	}
	public String getDorm_addr() {
		return dorm_addr;
	}
	public void setDorm_addr(String dorm_addr) {
		this.dorm_addr = dorm_addr;
	}
	public String getDorm_picture() {
		return dorm_picture;
	}
	public void setDorm_picture(String dorm_picture) {
		this.dorm_picture = dorm_picture;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getDorm_category_no() {
		return dorm_category_no;
	}
	public void setDorm_category_no(int dorm_category_no) {
		this.dorm_category_no = dorm_category_no;
	}
	
}

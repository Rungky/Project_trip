package dto;

import java.sql.Date;

public class ReservationDTO {
	private int reserve_no;
	private String member_id;
	private Date reserve_date;
	private Date reserve_checkin;
	private Date reserve_checkout;
	private int reserve_pay;
	private int reserve_person;
	private String room_name;
	
	public int getReserve_no() {
		return reserve_no;
	}
	public void setReserve_no(int reserve_no) {
		this.reserve_no = reserve_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(Date reserve_date) {
		this.reserve_date = reserve_date;
	}
	public Date getReserve_checkin() {
		return reserve_checkin;
	}
	public void setReserve_checkin(Date reserve_checkin) {
		this.reserve_checkin = reserve_checkin;
	}
	public Date getReserve_checkout() {
		return reserve_checkout;
	}
	public void setReserve_checkout(Date reserve_checkout) {
		this.reserve_checkout = reserve_checkout;
	}
	public int getReserve_pay() {
		return reserve_pay;
	}
	public void setReserve_pay(int reserve_pay) {
		this.reserve_pay = reserve_pay;
	}
	public int getReserve_person() {
		return reserve_person;
	}
	public void setReserve_person(int reserve_person) {
		this.reserve_person = reserve_person;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getDorm_name() {
		return dorm_name;
	}
	public void setDorm_name(String dorm_name) {
		this.dorm_name = dorm_name;
	}
	private String dorm_name;
	
	
	
	
	
	
	
	
	
}

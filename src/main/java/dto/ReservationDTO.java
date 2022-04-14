package dto;

import java.sql.Date;

public class ReservationDTO {
	private int reserve_no;
	private String member_id;
	private Date reserve_date;
	private Date reserve_checkin;
	private Date reserve_checkout;
	private int reserve_pay;
	private int room_no;
	private int dorm_no;
	
	
	
	
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
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getDorm_no() {
		return dorm_no;
	}
	public void setDorm_no(int dorm_no) {
		this.dorm_no = dorm_no;
	}
	
	
	
	
}

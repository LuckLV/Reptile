package model;

public class Address {

	private String addr_id;
	private String addr_url;
	private String title;
	private String craw_time;
		
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	public String getAddr_url() {
		return addr_url;
	}
	public void setAddr_url(String addr_url) {
		this.addr_url = addr_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCraw_time() {
		return craw_time;
	}
	public void setCraw_time(String craw_time) {
		this.craw_time = craw_time;
	}	
}

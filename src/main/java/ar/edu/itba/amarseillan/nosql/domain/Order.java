package ar.edu.itba.amarseillan.nosql.domain;

import java.util.Date;
import java.util.List;

public class Order {
	public String order_key; 
	public String order_status; 
	public double order_total_price; 
	public Date order_date; 
	public String order_priority; 
	public String order_clerk; 
	public int order_ship_priority; 
	public String order_comment;
	public List<LineItem> items;
	
	public String cus_key;
	public String cus_name;
	public String cus_address;
	public String cus_phone;
	public String cus_mkt_segment;
	public String nation_name;
	public String region_name;

}

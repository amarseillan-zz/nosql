package ar.edu.itba.amarseillan.nosql.domain;

import java.util.Date;

import org.jongo.marshall.jackson.oid.ObjectId;

public class LineItem {

	@ObjectId
	public String _id;
	
	public int line_number;
	public double quantity;
	public double extended_price;
	public double discount;
	public double tax;
	public String return_flag;
	public String line_status;
	public Date ship_date;
	public Date commit_date;
	public Date receipt_date;
	public String ship_in_struct;
	public String ship_mode;
	public String comment;

	public String supp_nation;
	public String supp_region;
	
	public String cus_nation;
	public String cus_region;
	
	
	@Override
	public String toString() {
		return "LineItem [_id=" + _id + ", line_number=" + line_number
				+ ", quantity=" + quantity + ", extended_price="
				+ extended_price + ", discount=" + discount + ", tax=" + tax
				+ ", return_flag=" + return_flag + ", line_status="
				+ line_status + ", ship_date=" + ship_date + ", commit_date="
				+ commit_date + ", receipt_date=" + receipt_date
				+ ", ship_in_struct=" + ship_in_struct + ", ship_mode="
				+ ship_mode + ", comment=" + comment + ", supp_nation="
				+ supp_nation + ", supp_region=" + supp_region
				+ ", cus_nation=" + cus_nation + ", cus_region=" + cus_region
				+ "]";
	}
}

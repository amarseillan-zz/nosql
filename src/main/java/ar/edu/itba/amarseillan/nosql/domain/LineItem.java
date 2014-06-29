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


	@Override
	public String toString() {
		return "LineItem [_id=" + _id + ", lineNumber=" + line_number
				+ ", quantity=" + quantity + ", extendedPrice=" + extended_price
				+ ", discount=" + discount + ", tax=" + tax + ", returnFlag="
				+ return_flag + ", lineStatus=" + line_status + ", shipDate="
				+ ship_date + ", commitDate=" + commit_date + ", receiptDate="
				+ receipt_date + ", shipInStruct=" + ship_in_struct
				+ ", shipMode=" + ship_mode + ", comment=" + comment + "]";
	}
	
	
	
}

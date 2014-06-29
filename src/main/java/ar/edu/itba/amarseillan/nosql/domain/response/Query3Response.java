package ar.edu.itba.amarseillan.nosql.domain.response;

import java.util.Date;

public class Query3Response {
	public String order_key;
	public double revenue;
	public Date order_date;
	public int order_ship_priority;
	
	@Override
	public String toString() {
		return "Query3Response [order_key=" + order_key + ", revenue="
				+ revenue + ", order_date=" + order_date
				+ ", order_ship_priority=" + order_ship_priority + "]";
	}
}

package ar.edu.itba.amarseillan.nosql.domain.response;

import java.util.Map;

public class Query1Response {
	
	public Map<String, String> _id;
	public double sum_qty;
	public double sum_base_price;
	public double sum_disc_price;
	public double sum_charge;
	public double avg_qty;
	public double avg_price;
	public double avg_disc;
	public int count_order;
	
	@Override
	public String toString() {
		return "Query1Response [_id=" + _id + ", sum_qty=" + sum_qty
				+ ", sum_base_price=" + sum_base_price + ", sum_disc_price="
				+ sum_disc_price + ", sum_charge=" + sum_charge + ", avg_qty="
				+ avg_qty + ", avg_price=" + avg_price + ", avg_disc="
				+ avg_disc + ", count_order=" + count_order + "]";
	}
	
}

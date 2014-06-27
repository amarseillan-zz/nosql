package ar.edu.itba.amarseillan.nosql.domain;

import org.jongo.marshall.jackson.oid.ObjectId;

public class Part {

	@ObjectId
	public String _id;
	
	public String supp_name;
	public String supp_address;
	public String supp_phone;
	public String nation_name;
	public String region_name;
	public double supp_acc_balance;
	public String supp_comment;
	
	public String part_name;
	public String part_key;
	public String part_mfgr;
	public String part_brand;
	public String part_type;
	public int part_size;
	public String part_container;
	public double part_retail_price;
	
	public int available_qty;
	public double supply_cost;
}

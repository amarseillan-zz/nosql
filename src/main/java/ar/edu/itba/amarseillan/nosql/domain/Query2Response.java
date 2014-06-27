package ar.edu.itba.amarseillan.nosql.domain;

public class Query2Response {
	public double supp_acc_balance;
	public String supp_name;
	public String supp_address;
	public String supp_phone; 
	public String nation_name;
	public String part_key;
	public String part_mfgr;
	
	@Override
	public String toString() {
		return "Query2Response [supp_acc_balance=" + supp_acc_balance
				+ ", supp_name=" + supp_name + ", supp_address=" + supp_address
				+ ", supp_phone=" + supp_phone + ", nation_name=" + nation_name
				+ ", part_key=" + part_key + ", part_mfgr=" + part_mfgr + "]";
	}
}

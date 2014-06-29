package ar.edu.itba.amarseillan.nosql.app;

import java.util.ArrayList;
import java.util.Date;

import ar.edu.itba.amarseillan.nosql.domain.LineItem;
import ar.edu.itba.amarseillan.nosql.domain.Order;
import ar.edu.itba.amarseillan.nosql.domain.Part;

public class DummyGenerator {
	
	private static int i = 0;

	public static LineItem genLineItem() {
		LineItem res = new LineItem();
		
		res._id = null;
		res.comment = "Test line item";
		res.commit_date = new Date();
		res.discount = 0.1;
		res.extended_price = 20;
		res.line_number = 1;
		res.quantity = 2;
		res.receipt_date = new Date();
		res.ship_date = new Date();
		res.ship_in_struct = "TRUE";
		res.ship_mode = "TRUCK";
		res.tax = 0.2;
		
		switch (i%4) {
			case 0:
				res.line_status = "CAN";
				res.return_flag = "R";
				res.supp_nation = "uganda";
				res.supp_region = "africa";
				res.cus_nation = "uganda";
				res.cus_region = "africa";
				break;
			case 1:
				res.line_status = "APP";
				res.return_flag = "N";
				res.supp_nation = "argentina";
				res.supp_region = "america";
				res.cus_nation = "uganda";
				res.cus_region = "africa";
				break;
			case 2:
				res.line_status = "PEN";
				res.return_flag = "R";
				res.supp_nation = "egipt";
				res.supp_region = "africa";
				res.cus_nation = "uganda";
				res.cus_region = "africa";
				break;
			case 3:
				res.line_status = "CAN";
				res.return_flag = "N";
				res.supp_nation = "argentina";
				res.supp_region = "america";
				res.cus_nation = "argentina";
				res.cus_region = "america";
				break;
		}
		
		i++;
		return res;
	}
	
	public static Part genPart() {
		Part part = new Part();
		
		part.available_qty = 4;
		part.nation_name = "Uganda";
		part.part_brand = "Coca-Cola";
		part.part_container = "Yes";
		part.part_key = "123123";
		part.part_mfgr = "No_idea_what_this_is";
		part.part_name = "Sprite";
		part.part_retail_price = 5;
		part.part_size = 1;
		part.part_type = "Bevrage";
		part.region_name = "Africa";
		part.supp_acc_balance = 4000200;
		part.supp_address = "uganda 239 - Uganda";
		part.supp_comment = "Good one";
		part.supp_name = "Coke inc";
		part.supp_phone = "4892-2020";
		part.supply_cost = 5;
		
		
		return part;
	}
	
	static int key = 0;
	
	public static Order genOrder() {
		Order order = new Order();
		order.cus_address = "first st 200";
		order.cus_key = String.valueOf(key++);
		order.cus_mkt_segment = "high";
		order.cus_name = "Mantin Inc";
		order.cus_phone = "4802-2022";
		order.nation_name = "uganda";
		order.order_clerk = "No idea";
		order.order_comment = "Cool order";
		order.order_date = new Date();
		order.order_key = String.valueOf(key++);
		order.order_priority = "high";
		order.order_ship_priority = 1;
		order.order_status = "approved";
		order.order_total_price = 800;
		order.region_name = "africa";
		
		order.items = new ArrayList<LineItem>(10);
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		order.items.add(genLineItem());
		
		return order;
		
	}
}

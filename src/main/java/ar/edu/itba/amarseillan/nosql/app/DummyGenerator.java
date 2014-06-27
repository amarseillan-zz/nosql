package ar.edu.itba.amarseillan.nosql.app;

import java.util.Date;

import ar.edu.itba.amarseillan.nosql.domain.LineItem;
import ar.edu.itba.amarseillan.nosql.domain.Part;

public class DummyGenerator {
	
	private static int i = 0;

	public static LineItem genLineItem() {
		LineItem res = new LineItem();
		
		res.set_id(null);
		res.setComment("Test line item");
		res.setCommitDate(new Date());
		res.setDiscount(0.1);
		res.setExtendedPrice(20);
		res.setLineNumber(1);
		res.setQuantity(2);
		res.setReceiptDate(new Date());
		res.setShipDate(new Date());
		res.setShipInStruct("TRUE");
		res.setShipMode("TRUCK");
		res.setTax(0.2);
		
		switch (i%4) {
			case 0:
				res.setLineStatus("CAN");
				res.setReturnFlag("R");
				break;
			case 1:
				res.setLineStatus("APP");
				res.setReturnFlag("N");
				break;
			case 2:
				res.setLineStatus("PEN");
				res.setReturnFlag("R");
				break;
			case 3:
				res.setLineStatus("CAN");
				res.setReturnFlag("N");
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
}

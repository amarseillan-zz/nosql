package ar.edu.itba.amarseillan.nosql.app;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import ar.edu.itba.amarseillan.nosql.domain.response.ObjectContainer;
import ar.edu.itba.amarseillan.nosql.domain.response.Query1Response;
import ar.edu.itba.amarseillan.nosql.domain.response.Query2Response;
import ar.edu.itba.amarseillan.nosql.domain.response.Query3Response;
import ar.edu.itba.amarseillan.nosql.domain.response.Query4Response;

import com.mongodb.MongoClient;


public class App {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws UnknownHostException, ParseException {
		
		System.out.println(args.length);
		Jongo jongo = new Jongo(new MongoClient().getDB("nosql"));
		
		int option = Integer.valueOf(args[0]);
		
		List response = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		switch (option) {
			case 0:
				fillDatabase(jongo);
				break;
			case 1:
				response = query1(jongo, sdf.parse(args[1]));
				break;
			case 2:
				query2(jongo, Integer.valueOf(args[1]), args[2], args[3]);
				break;
			case 3:
				query3(jongo, "high", sdf.parse(args[1]), sdf.parse(args[2]));
				break;
			case 4:
				query4(jongo, sdf.parse(args[1]), "africa");
				break;
			default:
				System.out.println("No such option, cya!");
				return;
		}
		response.stream().forEach(i -> System.out.println(i));
		
	}
	
	private static void fillDatabase(Jongo jongo) {
		MongoCollection orders = jongo.getCollection("orders");
		orders.insert(DummyGenerator.genOrder());
		orders.insert(DummyGenerator.genOrder());
		orders.insert(DummyGenerator.genOrder());
		orders.insert(DummyGenerator.genOrder());
		orders.insert(DummyGenerator.genOrder());
		orders.insert(DummyGenerator.genOrder());
		
		MongoCollection parts = jongo.getCollection("parts");
		parts.insert(DummyGenerator.genPart());
		parts.insert(DummyGenerator.genPart());
		parts.insert(DummyGenerator.genPart());
		parts.insert(DummyGenerator.genPart());
		parts.insert(DummyGenerator.genPart());
	}
	
	private static List<Query1Response> query1(Jongo jongo, Date date) {
		MongoCollection orders = jongo.getCollection("orders");
		List<Query1Response> response = orders.aggregate("{$project: {items:1}}").and("{$unwind: '$items'}").and("{$project: {ship_date:'$items.ship_date', return_flag:'$items.return_flag', line_status: '$items.line_status', quantity: '$items.quantity', extended_price: '$items.extended_price', tax: '$items.tax', discount: '$items.discount'}}").and("{$match: {ship_date: {$lt: #}}}", date).and("{$group : {_id: {return_flag:'$return_flag', line_status: '$line_status'}, "
				+ "sum_qty: {$sum: '$quantity'}," // sum(l_quantity)
				+ "sum_base_price: {$sum: '$extended_price'}," // sum(l_extendedprice)
				+ "sum_disc_price: {$sum: {$multiply: ['$extended_price',{$subtract:[1,'$discount']}]}}," // sum(l_extendedprice*(1-l_discount))
				+ "sum_charge: {$sum: {$multiply: ['$extended_price',{$subtract:[1,'$discount']},{$add:[1,'$tax']}]}}," // sum(l_extendedprice*(1-l_discount)*(1+l_tax))
				+ "avg_qty: {$avg: '$quantity'}," // avg(l_quantity) avg_qty,
				+ "avg_price: {$avg: '$extended_price'}," // avg(l_extendedprice) avg_price,
				+ "avg_disc: {$avg: '$discount'}," // avg(l_discount) avg_disc,
				+ "count_order: {$sum: 1}" // count(*) count_order
				+ "}}").and("{$sort: {'return_flag':1, 'line_status':1}}").as(Query1Response.class);
		
		return response;
	}
	
	private static List<Query2Response> query2(Jongo jongo, int size, String type, String region) {
		MongoCollection parts = jongo.getCollection("parts");
		
		List<ObjectContainer> response = parts.aggregate("{$match: {region_name: #}}", region).and("{$group: {'_id': '$supply_cost', result: {$push: '$$ROOT'  }}}").and("{$sort: {'_id': 1}}").and("{$group: {_id: '0', result: {$first: '$$ROOT'}} }").and("{$unwind: '$result.result'}").and("{$match: {'result.result.part_size': #}}", size).and("{$match: {'result.result.part_type': #}}", type).and("{$project: {object : '$result.result'}}").and("{$sort: {'supp_acc_balance': -1, 'nation_name': 1, 'supp_name': 1, 'part_key': 1}}").as(ObjectContainer.class);
		
		return response.stream().map(i -> i.object).collect(Collectors.toList());
	}
	
	private static List<Query3Response> query3(Jongo jongo, String mkt_segment, Date order_date, Date ship_date) {
		MongoCollection orders = jongo.getCollection("orders");
		
		List<Query3Response> response = orders.aggregate("{$match: {order_date: {$lt: #}}}", order_date).and("{$match: {cus_mkt_segment: #}}", mkt_segment).and("{$project: {'order_key':1, 'order_date':1, 'order_ship_priority':1, 'items': 1}}").and("{$unwind: '$items'}").and("{$match: {items.ship_date: {$gt: #}}}", ship_date).and("{$group: {_id: {'order_key': '$order_key', 'order_date': '$order_date', 'order_ship_priority':'$order_ship_priority'}, revenue: {$sum: {$multiply: ['$items.extended_price',{$subtract:[1,'$items.discount']}]}} }}").and("{$project:{'_id':0, 'order_key':'$_id.order_key','order_date':'$_id.order_date','order_ship_priority':'$_id.order_ship_priority','revenue':1}}").and("{$sort: {'revenue':-1, 'order_date':1}}").as(Query3Response.class);

		return response;
	}
	
	private static List<Query4Response> query4(Jongo jongo, Date date, String region) {
		MongoCollection orders = jongo.getCollection("orders");
		
		Date from = date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		Date to = c.getTime();
		
		List<Query4Response> response = orders.aggregate("{$match:{order_date: {$gte: #}}}", from).and("{$match:{order_date: {$lt: #}}}", to).and("{$project: {items:1}}").and("{$unwind: '$items'}").and("{$match: {'items.supp_region': #}}", region).and("{$group: {_id: '$items.supp_nation', revenue: {$sum: {$multiply: ['$items.extended_price',{$subtract:[1,'$items.discount']}]}} }}").and("{$project: {_id:0, 'nation':'$_id', 'revenue':1}}").as(Query4Response.class);
		
		return response;
	}
}

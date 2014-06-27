package ar.edu.itba.amarseillan.nosql.app;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import ar.edu.itba.amarseillan.nosql.domain.ObjectContainer;
import ar.edu.itba.amarseillan.nosql.domain.Query1Response;
import ar.edu.itba.amarseillan.nosql.domain.Query2Response;

import com.mongodb.MongoClient;


public class App {

	public static void main(String[] args) throws UnknownHostException {
		
		
		Jongo jongo = new Jongo(new MongoClient().getDB("nosql"));
		
		int option = Integer.valueOf(args[0]);
		
		switch (option) {
			case 0:
				fillDatabase(jongo);
				break;
			case 1:
				List<Query1Response> filtered = query1(jongo, new Date(args[1]));
				for (Query1Response item: filtered) {
					System.out.println(item);
				}
				break;
			case 2:
				query2(jongo, Integer.valueOf(args[1]), args[2], args[3]);
				break;
			case 3:
				query3(jongo);
				break;
			case 4:
				query4(jongo);
				break;
			default:
				System.out.println("No such option, cya!");
				return;
		}
		
	}
	
	private static void fillDatabase(Jongo jongo) {
		MongoCollection lineItems = jongo.getCollection("lineitems");
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		lineItems.insert(DummyGenerator.genLineItem());
		
		MongoCollection parts = jongo.getCollection("parts");
		parts.insert(DummyGenerator.genPart());
	}
	
	private static List<Query1Response> query1(Jongo jongo, Date date) {
		MongoCollection lineItems = jongo.getCollection("lineitems");
		String filter = "{$match: {shipDate: {$lt: #}}}";
		List<Query1Response> filtered = lineItems.aggregate(filter, date).and("{$group : {_id: {returnFlag:'$returnFlag', lineStatus: '$lineStatus'}, "
				+ "sum_qty: {$sum: '$quantity'}," // sum(l_quantity)
				+ "sum_base_price: {$sum: '$extendedPrice'}," // sum(l_extendedprice)
				+ "sum_disc_price: {$sum: {$multiply: ['$extendedPrice',{$subtract:['$discount',1]}]}}," // sum(l_extendedprice*(1-l_discount))
				+ "sum_charge: {$sum: {$multiply: ['$extendedPrice',{$subtract:[1,'$discount']},{$add:[1,'$tax']}]}}," // sum(l_extendedprice*(1-l_discount)*(1+l_tax))
				+ "avg_qty: {$avg: '$quantity'}," // avg(l_quantity) avg_qty,
				+ "avg_price: {$avg: '$extendedPrice'}," // avg(l_extendedprice) avg_price,
				+ "avg_disc: {$avg: '$discount'}," // avg(l_discount) avg_disc,
				+ "count_order: {$sum: 1}" // count(*) count_order
				+ "}}").and("{$sort: {'returnFlag':1, 'lineStatus':1}}").as(Query1Response.class);
		
		return filtered;
	}
	
	private static void query2(Jongo jongo, int size, String type, String region) {
		MongoCollection parts = jongo.getCollection("parts");
		
		List<ObjectContainer> response = parts.aggregate("{$match: {region_name: #}}", region).and("{$group: {'_id': '$supply_cost', result: {$push: '$$ROOT'  }}}").and("{$sort: {'_id': 1}}").and("{$group: {_id: '0', result: {$first: '$$ROOT'}} }").and("{$unwind: '$result.result'}").and("{$match: {'result.result.part_size': #}}", size).and("{$match: {'result.result.part_type': #}}", type).and("{$project: {object : '$result.result'}}").as(ObjectContainer.class);
		
		System.out.println(response.size());
		
		response.stream().map(i -> i.object).forEach(h -> System.out.println(h));
		
	}
	
	private static void query3(Jongo jongo) {}
	
	private static void query4(Jongo jongo) {}
}

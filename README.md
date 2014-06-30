#Document stores: MongoDB


Alumnos:
 + Luciana Reznik
 + Agustín Marseillan

##Diseño

El diseño de la base de datos es el siguiente: Se poseen dos colecciones llamadas orders y parts. En el apéndice se puede ver un ejemplo de cada colección.

La elección del diseño fue realizada tomando en cuenta las queries planteadas y la cardinalidad de cada tabla en sql. En algunos casos, se tomó en cuenta también si este elemento era propenso a cambio o no, para decidir si tenía sentido repetirlo en distintas colecciones o no. Se desnormalizaron casi todas las tablas llegando así a estos 2 documentos. Hay algunos campos que no se incluyeron ya que no se pedían en ninguna query (en un ambiente productivo los incluiríamos igual ya que no queremos perder información).

##Programa
Se utilizó [Jongo](jongo.org) para la conexión a la base de datos dado que facilita la lectura del código para las consultas de mongo.
El programa se debe llamar con argumentos.
Si lo llamamos con 0, estamos diciendo que ponga datos en la base de datos.
Si lo llamamos con [1-4] estamos diciendo que ejecute la query [1-4] y nos muestre el resultado.
Se utilizó mongo 2.6.3 para aprovechar mejoras como $$ROOT que sirvieron para resolver algunas queries.
Si se quiere correr el programa se debe tener java 8.


Código: [github](https://github.com/amarseillan/nosql)


##Apéndice: 

db.orders.findOne()


	{
		"_id" : ObjectId("53b15daf0f09fbe7094f287f"),
		"order_key" : "1",
		"order_status" : "approved",
		"order_total_price" : 800,
		"order_date" : ISODate("2014-06-30T12:53:03.192Z"),
		"order_priority" : "high",
		"order_clerk" : "No idea",
		"order_ship_priority" : 1,
		"order_comment" : "Cool order",
		"items" : [
			{
				"line_number" : 1,
				"quantity" : 2,
				"extended_price" : 20,
				"discount" : 0.1,
				"tax" : 0.2,
				"return_flag" : "R",
				"line_status" : "CAN",
				"ship_date" : ISODate("2014-06-30T12:53:03.192Z"),
				"commit_date" : ISODate("2014-06-30T12:53:03.192Z"),
				"receipt_date" : ISODate("2014-06-30T12:53:03.192Z"),
				"ship_in_struct" : "TRUE",
				"ship_mode" : "TRUCK",
				"comment" : "Test line item",
				"supp_nation" : "uganda",
				"supp_region" : "africa",
				"cus_nation" : "uganda",
				"cus_region" : "africa"
			},
		"cus_key" : "0",
		"cus_name" : "Mantin Inc",
		"cus_address" : "first st 200",
		"cus_phone" : "4802-2022",
		"cus_mkt_segment" : "high",
		"nation_name" : "uganda",
		"region_name" : "africa"
	}
	
db.parts.findOne()

	{
		"_id" : ObjectId("53b15dafe4b04ba21f99f668"),
		"supp_name" : "Coke inc",
		"supp_address" : "uganda 239 - Uganda",
		"supp_phone" : "4892-2020",
		"nation_name" : "Uganda",
		"region_name" : "Africa",
		"supp_acc_balance" : 4000200,
		"supp_comment" : "Good one",
		"part_name" : "Sprite",
		"part_key" : "123123",
		"part_mfgr" : "No_idea_what_this_is",
		"part_brand" : "Coca-Cola",
		"part_type" : "Bevrage",
		"part_size" : 1,
		"part_container" : "Yes",
		"part_retail_price" : 5,
		"available_qty" : 4,
		"supply_cost" : 5
	}


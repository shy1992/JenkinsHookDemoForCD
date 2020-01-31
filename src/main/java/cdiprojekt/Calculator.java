package cdiprojekt;
import static spark.Spark.*;

import redis.clients.jedis.Jedis;


public class Calculator {
	
	private Jedis jedis = new Jedis("redis:6379");
	{
		System.out.println("Set redis block");
		jedis.set("invokedCounter", "0");
		System.out.println("after redis block");
	}
	
	public static void main(String[] args) {
		System.out.println("Calculator is running");
		Calculator calc = new Calculator();
		System.out.println("perform add");
		int sum = calc.add(42, 1337);
		System.out.println(sum);
		
		get("/hello", (req, res) -> calc.getInvokedCount());
		get("/add/:add1/:add2", (req, res) -> calc.add(Integer.parseInt(req.params("add1")),Integer.parseInt(req.params("add2") )));
		System.out.println("Running on port 4567 here is a link!");
		System.out.println("http://localhost:4567/hello");
	}

	public int add(int... summands) {
		try {
			String cachedResponse = jedis.get("invokedCounter");
			int lastValue = Integer.parseInt(cachedResponse);
			lastValue++;
			jedis.set("invokedCounter", lastValue+"");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		int sum = 0;
		for(int summand : summands) {
			sum += summand;
		}
		// TODO Auto-generated method stub
		return sum;
	}
	
	
	public String getInvokedCount() {
		return jedis.get("invokedCounter");
	}


	
	
	
}

package com.br.microserviceregimen.service;

import java.io.IOException;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.json.*;

public class RegimenService {
	
	public static void main(String[] args) {		
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		String Response = client.prepare("GET", "https://calorieninjas.p.rapidapi.com/v1/nutrition?query=tomato")
				.setHeader("X-RapidAPI-Key", "834cdc197bmsh406c60b10ca5275p18b7d4jsn0a3285059be5")
				.setHeader("X-RapidAPI-Host", "calorieninjas.p.rapidapi.com")
				.execute()
				.toCompletableFuture()
				.join().getResponseBody();
			
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Response.getClass().getSimpleName());
		String[] nutritionalInformation = Response.replace("[",";").replace("]",";").split(";");
		System.out.println(nutritionalInformation[0]);
		System.out.println(nutritionalInformation[1]);
		System.out.println(nutritionalInformation[2]);
		
		JSONObject jsonObject = new JSONObject(nutritionalInformation[1]);
		System.out.println(jsonObject.getClass().getSimpleName());
		System.out.println(jsonObject);
		
		ArrayList<String> foodList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			foodList.add("tomato");
		}
		String urlAPI = "https://calorieninjas.p.rapidapi.com/v1/nutrition?query=".concat(foodList.get(0));
		System.out.println(urlAPI);
		System.out.println(foodList.get(0));
		System.out.println(foodList.get(1));
		System.out.println(foodList.get(2));
		System.out.println(foodList.get(3));
		System.out.println(foodList.get(4));
				
	}
}
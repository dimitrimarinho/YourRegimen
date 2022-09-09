package com.platform.yourregimen.diet.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RegimenService {
	
	private double sugar_g;
	private double fiber_g;
	private double serving_size_g;
	private double sodium_mg;
	private String name;
	private double potassium_mg;
	private double fat_saturated_g;
	private double fat_total_g;
	private double calories;
	private double cholesterol_mg;
	private double protein_g;
	private double carbohydrates_total_g;

	public RegimenService(double sugar_g, double fiber_g, double serving_size_g, double sodium_mg, String name,
			double potassium_mg, double fat_saturated_g, double fat_total_g, double calories, double cholesterol_mg,
			double protein_g, double carbohydrates_total_g) {
		this.sugar_g = sugar_g;
		this.fiber_g = fiber_g;
		this.serving_size_g = serving_size_g;
		this.sodium_mg = sodium_mg;
		this.name = name;
		this.potassium_mg = potassium_mg;
		this.fat_saturated_g = fat_saturated_g;
		this.fat_total_g = fat_total_g;
		this.calories = calories;
		this.cholesterol_mg = cholesterol_mg;
		this.protein_g = protein_g;
		this.carbohydrates_total_g = carbohydrates_total_g;
	}

	public RegimenService() {
	}

	public double getSugar_g() {
		return sugar_g;
	}

	public void setSugar_g(double sugar_g) {
		this.sugar_g = sugar_g;
	}

	public double getFiber_g() {
		return fiber_g;
	}

	public void setFiber_g(double fiber_g) {
		this.fiber_g = fiber_g;
	}

	public double getServing_size_g() {
		return serving_size_g;
	}

	public void setServing_size_g(double serving_size_g) {
		this.serving_size_g = serving_size_g;
	}

	public double getSodium_mg() {
		return sodium_mg;
	}

	public void setSodium_mg(double sodium_mg) {
		this.sodium_mg = sodium_mg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPotassium_mg() {
		return potassium_mg;
	}

	public void setPotassium_mg(double potassium_mg) {
		this.potassium_mg = potassium_mg;
	}

	public double getFat_saturated_g() {
		return fat_saturated_g;
	}

	public void setFat_saturated_g(double fat_saturated_g) {
		this.fat_saturated_g = fat_saturated_g;
	}

	public double getFat_total_g() {
		return fat_total_g;
	}

	public void setFat_total_g(double fat_total_g) {
		this.fat_total_g = fat_total_g;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getCholesterol_mg() {
		return cholesterol_mg;
	}

	public void setCholesterol_mg(double cholesterol_mg) {
		this.cholesterol_mg = cholesterol_mg;
	}

	public double getProtein_g() {
		return protein_g;
	}

	public void setProtein_g(double protein_g) {
		this.protein_g = protein_g;
	}

	public double getCarbohydrates_total_g() {
		return carbohydrates_total_g;
	}

	public void setCarbohydrates_total_g(double carbohydrates_total_g) {
		this.carbohydrates_total_g = carbohydrates_total_g;
	}

	public String TranslateApi(String query) throws IOException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		String queryResp = client.prepare("POST", "https://google-translate1.p.rapidapi.com/language/translate/v2")
			.setHeader("content-type", "application/x-www-form-urlencoded")
			.setHeader("Accept-Encoding", "application/gzip")
			.setHeader("X-RapidAPI-Key", "c7bcdbfb72mshd0504f627ba07aap13087ajsn90ded51d1d19")
			.setHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
			.setBody("q=" +query+ "&target=en&source=pt")
			.execute()
			.toCompletableFuture()
			.join().getResponseBody();

		client.close();
		
		return queryResp;
	}
	
	public String ConectarApi(String inputFoods) throws IOException, InterruptedException {
		String foodsSearch = URLEncoder.encode(inputFoods, StandardCharsets.UTF_8);
		System.out.print("\n" + foodsSearch);
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		String resp = client.prepare("GET", "https://calorieninjas.p.rapidapi.com/v1/nutrition?query="+foodsSearch)
			.setHeader("X-RapidAPI-Key", "c7bcdbfb72mshd0504f627ba07aap13087ajsn90ded51d1d19")
			.setHeader("X-RapidAPI-Host", "calorieninjas.p.rapidapi.com")
			.execute()
			.toCompletableFuture()
			.join().getResponseBody();

		client.close();
		//System.out.println(resp);
		return resp;		
	}

	public JSONArray getInformations(String responseBody) {
		JSONObject tmp = new JSONObject(responseBody);
		JSONArray foodList = tmp.getJSONArray("items");
		int lenghtJSON = foodList.length();
		System.out.println("\n" + foodList + "\n");	
		for (int i = 0; i < lenghtJSON; i++) {
			JSONObject nutritionalInformation = foodList.getJSONObject(i);
			System.out.println("\nName: " + nutritionalInformation.get("name"));	
			System.out.println("Calories: " + nutritionalInformation.get("calories"));	
			System.out.println("Carbohydrates_total_g: " + nutritionalInformation.get("carbohydrates_total_g"));	
			System.out.println("Cholesterol_mg: " + nutritionalInformation.get("cholesterol_mg"));	
			System.out.println("Fat_saturated_g: " + nutritionalInformation.get("fat_saturated_g"));	
			System.out.println("Fat_total_g: " + nutritionalInformation.get("fat_total_g"));	
			System.out.println("Fiber_g: " + nutritionalInformation.get("fiber_g"));
			System.out.println("Potassium_mg: " + nutritionalInformation.get("potassium_mg"));	
			System.out.println("Protein_g: " + nutritionalInformation.get("protein_g"));	
			System.out.println("Serving_size_g: " + nutritionalInformation.get("serving_size_g"));	
			System.out.println("Sodium_mg: " + nutritionalInformation.get("sodium_mg"));	
			System.out.println("Sugar_g: " + nutritionalInformation.get("sugar_g"));	
		}
		System.out.print("\n\n");
		
		return foodList;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Digite a lista de alimentos da dieta: ");
		String query = scan.nextLine();
		scan.close();
		RegimenService alimento = new RegimenService();
		alimento.TranslateApi(query);
		String getfromAPI = alimento.ConectarApi(query);
		alimento. getInformations(getfromAPI);			
		
	}

}
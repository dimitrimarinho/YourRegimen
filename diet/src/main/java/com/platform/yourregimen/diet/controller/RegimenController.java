package com.platform.yourregimen.diet.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.yourregimen.diet.model.InfoApi;
import com.platform.yourregimen.diet.model.Regimen;
import com.platform.yourregimen.diet.repository.RegimenRepository;
import com.platform.yourregimen.diet.service.RegimenService;

@RestController
@RequestMapping("/service-diet/regimen")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegimenController {

	@Autowired
	private RegimenRepository repository;

	@Autowired
	private RegimenService service;

	@GetMapping("/getall")
	public ResponseEntity<List<Regimen>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/infoApi")
	String ConectarApi(String foodSearch) throws IOException, InterruptedException {
		InfoApi infosApi = new InfoApi();
		infosApi.setFoodSearch(foodSearch);
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		String resp = client.prepare("GET", "https://calorieninjas.p.rapidapi.com/v1/nutrition?query=" + foodSearch)
				.setHeader("X-RapidAPI-Key", "c7bcdbfb72mshd0504f627ba07aap13087ajsn90ded51d1d19")
				.setHeader("X-RapidAPI-Host", "calorieninjas.p.rapidapi.com").execute().toCompletableFuture().join()
				.getResponseBody();

		client.close();

		infosApi.setInfoApi(resp);

		// System.out.println(resp);
		return infosApi.getInfoApi();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Regimen> getById(@PathVariable UUID id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/regimenName/{regimenName}")
	public ResponseEntity<List<Regimen>> getByName(@PathVariable String regimenName) {
		return ResponseEntity.ok(repository.findAllByRegimenNameContainingIgnoreCase(regimenName));
	}

	@PostMapping(value = "/")
	public ResponseEntity<Regimen> post(@Valid @RequestBody Regimen regimen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(regimen));
	}

	@PutMapping(value = "/")
	public ResponseEntity<Regimen> put(@Valid @RequestBody Regimen regimen) {
		return ResponseEntity.ok(repository.save(regimen));
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable UUID id) {
		repository.deleteById(id);
	}

}
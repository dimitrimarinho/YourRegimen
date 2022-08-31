package com.platform.yourregimen.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;
import com.platform.yourregimen.model.Regimen;
import com.platform.yourregimen.repository.RegimenRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegimenController {

	@Autowired
	private RegimenRepository repository;

	@GetMapping(value = "/regimen")
	public ResponseEntity<List<Regimen>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Regimen> getById(@PathVariable UUID id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/regimenName/{regimenName}")
	public ResponseEntity<List<Regimen>> getByName(@PathVariable String regimenName){
		return ResponseEntity.ok(repository.findAllByRegimenNameContainingIgnoreCase(regimenName));
	}
	
	@PostMapping(value = "/regimen")
	public ResponseEntity <Regimen> post(@Valid @RequestBody Regimen regimen){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(regimen));
	}
	
	@PutMapping(value = "/regimen") 
	public ResponseEntity <Regimen> put (@Valid @RequestBody Regimen regimen){
		return ResponseEntity.ok(repository.save(regimen));
	}
	
	@DeleteMapping(value="/regimen/{id}")
	public void delete (@PathVariable UUID id) {
		repository.deleteById(id);
	}
	
	@GetMapping(value="/nutritionalInformation")	
	public String nutritionalnformation() {		
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
		
		return Response;		
	}	
	
}
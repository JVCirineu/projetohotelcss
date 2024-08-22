package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Hospede;
import com.example.service.HospedeService;

@RestController
@RequestMapping("/api/guests")
public class HospedeController {
private final HospedeService hospedeService;
	
	@Autowired
	public HospedeController (HospedeService hospedeService) {
		this.hospedeService = hospedeService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospede> buscaHospedeControlId(@PathVariable Long id){
		Hospede Hospede = hospedeService.buscaHospedeId(id);
		if(Hospede != null) {
			return ResponseEntity.ok(Hospede);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Hospede>> buscaTodosHospedeControl(){
		List<Hospede> Hospede = hospedeService.buscaTodosHospedes();
		return ResponseEntity.ok(Hospede);
	}
	
	@PostMapping
	public ResponseEntity<Hospede> salvaHospedeControl(@RequestBody Hospede hospede){
		Hospede salvaHospede = hospedeService.salvaHospede(hospede);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaHospede);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hospede> alterarHospedeControl(@PathVariable Long id, @RequestBody Hospede hospede){
		Hospede alterarHospede = hospedeService.alterarHospede(id, hospede);
		if(hospede != null) {
			return ResponseEntity.ok(hospede);
		}
		else {
			return ResponseEntity.notFound().build();   	
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagarHospedeControl(@PathVariable Long id){
		boolean apagar = hospedeService.apagarHospede(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Hospede foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}

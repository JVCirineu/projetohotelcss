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

import com.example.entities.Quarto;
import com.example.service.QuartoService;


@RestController
@RequestMapping("/api/rooms")
public class QuartoController {
	@Autowired
	private QuartoService quartoService;
		
		@Autowired
		public QuartoController (QuartoService quartoService) {
			this.quartoService = quartoService;
		}
		@GetMapping("/{id}")
		public ResponseEntity<Quarto> buscaQuartoControlId(@PathVariable Long id){
			Quarto Quarto = quartoService.buscaQuartoId(id);
			if(Quarto != null) {
				return ResponseEntity.ok(Quarto);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		
		@GetMapping
		public ResponseEntity<List<Quarto>> buscaTodosQuartoControl(){
			List<Quarto> Quarto = quartoService.buscaTodosQuartos();
			return ResponseEntity.ok(Quarto);
		}
		
		@PostMapping
		public ResponseEntity<Quarto> salvaQuartoControl(@RequestBody Quarto quarto){
			Quarto salvaQuarto = quartoService.salvaQuarto(quarto);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvaQuarto);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Quarto> alteraQuartoControl(@PathVariable Long id, @RequestBody Quarto quarto){
			Quarto alterarQuarto = quartoService.alterarQuarto(id, quarto);
			if(quarto != null) {
				return ResponseEntity.ok(quarto);
			}
			else {
				return ResponseEntity.notFound().build();   	
			}
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<String> apagarQuartoControl(@PathVariable Long id){
			boolean apagar = quartoService.apagarQuarto(id);
			if (apagar) {
				return ResponseEntity.ok().body("O Quarto foi excluido com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

	}


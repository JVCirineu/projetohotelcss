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

import com.example.entities.Reserva;
import com.example.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
	private final ReservaService reservaService;
	
	@Autowired
	public ReservaController (ReservaService reservaService) {
		this.reservaService = reservaService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> buscaReservaControlId(@PathVariable Long id){
		Reserva Reserva = reservaService.buscaReservaId(id);
		if(Reserva != null) {
			return ResponseEntity.ok(Reserva);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Reserva>> buscaTodosReservaControl(){
		List<Reserva> Reserva = reservaService.buscaTodosReservas();
		return ResponseEntity.ok(Reserva);
	}
	
	@PostMapping
	public ResponseEntity<Reserva> salvaReservaControl(@RequestBody Reserva reserva){
		Reserva salvaReserva = reservaService.salvaReserva(reserva);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaReserva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Reserva> alteraReservaControl(@PathVariable Long id, @RequestBody Reserva reserva){
		Reserva alterarReserva = reservaService.alterarReserva(id, reserva);
		if(reserva != null) {
			return ResponseEntity.ok(reserva);
		}
		else {
			return ResponseEntity.notFound().build();   	
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagarReservaControl(@PathVariable Long id){
		boolean apagar = reservaService.apagarReserva(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Reserva foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	

}

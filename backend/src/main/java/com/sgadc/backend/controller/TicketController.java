package com.sgadc.backend.controller;

import com.sgadc.backend.model.Ticket;
import com.sgadc.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*") 
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    // 1. Obtener todos los tickets (GET)
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // 2. Crear un nuevo ticket (POST)
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // 3. Actualizar un ticket (PUT) - Con categoría y prioridad corregidas
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable String id, @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + id));
        
        ticket.setTitulo(ticketDetails.getTitulo());
        ticket.setDescripcion(ticketDetails.getDescripcion());
        ticket.setCategoria(ticketDetails.getCategoria());
        ticket.setPrioridad(ticketDetails.getPrioridad());
        ticket.setEstado(ticketDetails.getEstado());
        
        return ticketRepository.save(ticket);
    }

    // 4. Eliminar un ticket (DELETE)
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable String id) {
        ticketRepository.deleteById(id);
    }
}

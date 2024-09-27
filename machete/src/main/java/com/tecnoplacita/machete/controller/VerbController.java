package com.tecnoplacita.machete.controller;



import com.tecnoplacita.machete.dto.Busquedad;
import com.tecnoplacita.machete.model.Verb;
import com.tecnoplacita.machete.service.VerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/verbs")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen. Configura según tus necesidades de seguridad.
public class VerbController {

    @Autowired
    private VerbService verbService;

    // Obtener todos los verbos
    @GetMapping
    public List<Verb> getAllVerbs() {
        return verbService.getAllVerbs();
    }

    // Buscar verbos por baseForm
    @PostMapping("/search")
    public List<Verb> searchVerbs(@RequestBody Busquedad query) {
        return verbService.searchVerbs(query.getBusquedad().toString());
    }

    // Añadir un nuevo verbo
    @PostMapping
    public Verb createVerb(@RequestBody Verb verb) {
        return verbService.addVerb(verb);
    }

    // Actualizar un verbo existente
    @PutMapping("/{id}")
    public ResponseEntity<Verb> updateVerb(@PathVariable Long id, @RequestBody Verb verbDetails) {
        Verb updatedVerb = verbService.updateVerb(id, verbDetails);
        return ResponseEntity.ok(updatedVerb);
    }

    // Eliminar un verbo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerb(@PathVariable Long id) {
        verbService.deleteVerb(id);
        return ResponseEntity.noContent().build();
    }
}

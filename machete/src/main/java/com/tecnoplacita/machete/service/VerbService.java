package com.tecnoplacita.machete.service;


import com.tecnoplacita.machete.exceptions.ResourceNotFoundException;
import com.tecnoplacita.machete.model.Verb;
import com.tecnoplacita.machete.repository.VerbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerbService {

    @Autowired
    private VerbRepository verbRepository;

    public List<Verb> getAllVerbs() {
        return verbRepository.findAll();
    }

    public List<Verb> searchVerbs(String query) {
    	
        return verbRepository.findByBaseFormContainingIgnoreCase(query);
    }

    public Verb addVerb(Verb verb) {
        return verbRepository.save(verb);
    }

    public Verb updateVerb(Long id, Verb verbDetails) {
        Verb verb = verbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Verbo no encontrado con id  :: " + id));

        verb.setBaseForm(verbDetails.getBaseForm());
        verb.setThirdPerson(verbDetails.getThirdPerson());
        verb.setPast(verbDetails.getPast());
        verb.setPastParticiple(verbDetails.getPastParticiple());
        verb.setGerund(verbDetails.getGerund());

        return verbRepository.save(verb);
    }

    public void deleteVerb(Long id) {
        Verb verb = verbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Verbo no encontrado con id  :: " + id));
        verbRepository.delete(verb);
    }
}

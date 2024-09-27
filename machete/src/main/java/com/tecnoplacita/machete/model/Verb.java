package com.tecnoplacita.machete.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "verbs")
public class Verb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String baseForm;
    
    private String translation;

    private String thirdPerson;

    private String past;

    private String pastParticiple;

    private String gerund;

    
    public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	// Relaciones con conjugaciones y oraciones
    @OneToMany(mappedBy = "verb", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sentence> sentences;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBaseForm() {
		return baseForm;
	}

	public void setBaseForm(String baseForm) {
		this.baseForm = baseForm;
	}

	public String getThirdPerson() {
		return thirdPerson;
	}

	public void setThirdPerson(String thirdPerson) {
		this.thirdPerson = thirdPerson;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}

	public void setPastParticiple(String pastParticiple) {
		this.pastParticiple = pastParticiple;
	}

	public String getGerund() {
		return gerund;
	}

	public void setGerund(String gerund) {
		this.gerund = gerund;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}
	
	   public Verb() {
	        this.sentences = new ArrayList<>(); // Inicializa la lista aquí
	    }

	public Verb(Long id, String baseForm, String translation, String thirdPerson, String past, String pastParticiple,
			String gerund, List<Sentence> sentences) {
		super();
		this.id = id;
		this.baseForm = baseForm;
		this.translation = translation;
		this.thirdPerson = thirdPerson;
		this.past = past;
		this.pastParticiple = pastParticiple;
		this.gerund = gerund;
		this.sentences = sentences;
	}
	   
	   


 
}

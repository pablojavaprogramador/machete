package com.tecnoplacita.machete.loader;

import com.tecnoplacita.machete.model.Sentence;
import com.tecnoplacita.machete.model.Verb;
import com.tecnoplacita.machete.repository.VerbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private VerbRepository verbRepository;

    @Override
    public void run(String... args) throws Exception {
        if (verbRepository.count() == 0) {
            Verb load = createLoadVerb();
            verbRepository.save(load);
        }
    }

    private Verb createLoadVerb() {
        Verb load = new Verb();
        load.setBaseForm("load");
        load.setThirdPerson("loads");
        load.setPast("loaded");
        load.setPastParticiple("loaded");
        load.setGerund("loading");
        load.setTranslation("cargar"); // Traducción del verbo

        addSentencesToVerb(load, new String[][]{
            // Presente Simple
            {"Presente Simple", "Afirmativo", "I load the data every day.", "/ai lóud de deita evri dei/", "Yo cargo los datos todos los días."},
            {"Presente Simple", "Interrogativo", "Do you load the data?", "/du iu lóud de deita/", "¿Cargas los datos?"},
            {"Presente Simple", "Negativo", "I do not load the data.", "/ai du not lóud de deita/", "No cargo los datos."},
            // Presente Perfecto
            {"Presente Perfecto", "Afirmativo", "I have loaded the data.", "/ai hav lóudid de deita/", "He cargado los datos."},
            {"Presente Perfecto", "Interrogativo", "Have you loaded the data?", "/hav iu lóudid de deita/", "¿Has cargado los datos?"},
            {"Presente Perfecto", "Negativo", "I have not loaded the data.", "/ai hav not lóudid de deita/", "No he cargado los datos."},
            // Presente Continuo
            {"Presente Continuo", "Afirmativo", "I am loading the data.", "/ai em lóuding de deita/", "Estoy cargando los datos."},
            {"Presente Continuo", "Interrogativo", "Are you loading the data?", "/ar iu lóuding de deita/", "¿Estás cargando los datos?"},
            {"Presente Continuo", "Negativo", "I am not loading the data.", "/ai em not lóuding de deita/", "No estoy cargando los datos."},
            // Pasado Simple
            {"Pasado Simple", "Afirmativo", "I loaded the data yesterday.", "/ai lóudid de deita yesturdei/", "Cargué los datos ayer."},
            {"Pasado Simple", "Interrogativo", "Did you load the data yesterday?", "/did iu lóud de deita yesturdei/", "¿Cargaste los datos ayer?"},
            {"Pasado Simple", "Negativo", "I did not load the data yesterday.", "/ai did not lóud de deita yesturdei/", "No cargué los datos ayer."},
            // Pasado Perfecto
            {"Pasado Perfecto", "Afirmativo", "I had loaded the data before the meeting.", "/ai had lóudid de deita bifor de míting/", "Había cargado los datos antes de la reunión."},
            {"Pasado Perfecto", "Interrogativo", "Had you loaded the data before the meeting?", "/had iu lóudid de deita bifor de míting/", "¿Habías cargado los datos antes de la reunión?"},
            {"Pasado Perfecto", "Negativo", "I had not loaded the data before the meeting.", "/ai had not lóudid de deita bifor de míting/", "No había cargado los datos antes de la reunión."},
            // Pasado Continuo
            {"Pasado Continuo", "Afirmativo", "I was loading the data when he called.", "/ai wás lóuding de deita wen hi kold/", "Estaba cargando los datos cuando él llamó."},
            {"Pasado Continuo", "Interrogativo", "Were you loading the data when he called?", "/wer iu lóuding de deita wen hi kold/", "¿Estabas cargando los datos cuando él llamó?"},
            {"Pasado Continuo", "Negativo", "I was not loading the data when he called.", "/ai wás not lóuding de deita wen hi kold/", "No estaba cargando los datos cuando él llamó."},
            // Futuro Simple
            {"Futuro Simple", "Afirmativo", "I will load the data tomorrow.", "/ai wil lóud de deita tumórró/", "Cargaré los datos mañana."},
            {"Futuro Simple", "Interrogativo", "Will you load the data tomorrow?", "/wil iu lóud de deita tumórró/", "¿Cargarás los datos mañana?"},
            {"Futuro Simple", "Negativo", "I will not load the data tomorrow.", "/ai wil not lóud de deita tumórró/", "No cargaré los datos mañana."},
            // Futuro Perfecto
            {"Futuro Perfecto", "Afirmativo", "I will have loaded the data by 5 PM.", "/ai wil hav lóudid de deita bai 5 PM/", "Habré cargado los datos para las 5 PM."},
            {"Futuro Perfecto", "Interrogativo", "Will you have loaded the data by 5 PM?", "/wil iu hav lóudid de deita bai 5 PM/", "¿Habrá cargado los datos para las 5 PM?"},
            {"Futuro Perfecto", "Negativo", "I will not have loaded the data by 5 PM.", "/ai wil not hav lóudid de deita bai 5 PM/", "No habré cargado los datos para las 5 PM."},
            // Futuro Continuo
            {"Futuro Continuo", "Afirmativo", "I will be loading the data.", "/ai wil bi lóuding de deita/", "Estaré cargando los datos."},
            {"Futuro Continuo", "Interrogativo", "Will you be loading the data?", "/wil iu bi lóuding de deita/", "¿Estarás cargando los datos?"},
            {"Futuro Continuo", "Negativo", "I will not be loading the data.", "/ai wil not bi lóuding de deita/", "No estaré cargando los datos."},
            // Condicional
            {"Condicional", "Afirmativo", "I would load the data if I had it.", "/ai wud lóud de deita if ai had it/", "Cargaría los datos si los tuviera."},
            {"Condicional", "Interrogativo", "Would you load the data if you had it?", "/wud iu lóud de deita if iu had it/", "¿Cargarías los datos si los tuvieras?"},
            {"Condicional", "Negativo", "I would not load the data if I had it.", "/ai wud not lóud de deita if ai had it/", "No cargaría los datos si los tuviera."}
        });

        return load;
    }

    private void addSentencesToVerb(Verb verb, String[][] sentenceData) {
        for (String[] data : sentenceData) {
            verb.getSentences().add(createSentence(data[0], data[1], data[2], data[3], data[4], verb));
        }
    }

    private Sentence createSentence(String tense, String type, String content, String pronunciation, String translation, Verb verb) {
        Sentence sentence = new Sentence();
        sentence.setTense(tense);
        sentence.setType(type);
        sentence.setContent(content);
        sentence.setFigurativePronunciation(pronunciation);
        sentence.setTranslation(translation); // Añadir traducción
        sentence.setVerb(verb);
        return sentence;
    }
}

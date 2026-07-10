package org.orpheus.typeracer.text.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.text.model.TextSnippet;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;


@Service
@RequiredArgsConstructor
public class TextService {

    private final TextRepository textRepository;

    public TextSnippet getRandomText(){

        refreshPoolAsync(); // ξεκινάει async στο background, η getRandomText() επιστρέφει αμέσως.
        return textRepository.getRandomText().orElseThrow(() -> new RuntimeException("Text not found"));

    }

    @Async
    public void refreshPoolAsync()
    {
        if (textRepository.count() <= 100){
            textRepository.save(dummyJsonApi());
        }
        else {
          TextSnippet random = textRepository.getRandomText().orElseThrow(() -> new RuntimeException("Text not found"));
          TextSnippet newSnippet = dummyJsonApi();
          random.setContent(newSnippet.getContent());
          random.setAuthor(newSnippet.getAuthor());

          textRepository.save(random);

        }
    }

    private TextSnippet dummyJsonApi(){

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                "https://dummyjson.com/quotes/random",
                HttpMethod.GET,
                null,
                JsonNode.class
        );

        TextSnippet textSnippet = new TextSnippet();
        textSnippet.setAuthor(response.getBody().get("author").asString());
        textSnippet.setContent(response.getBody().get("quote").asString());

        return textSnippet;

    }

}



package org.orpheus.typeracer.text.core;

import org.orpheus.typeracer.text.model.TextSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TextRepository extends JpaRepository<TextSnippet, Long> {

    @Query("SELECT t FROM TextSnippet t ORDER BY RANDOM() LIMIT 1")
    Optional<TextSnippet> getRandomText();
}

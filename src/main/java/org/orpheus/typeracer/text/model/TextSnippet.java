package org.orpheus.typeracer.text.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "text_snippets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextSnippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 10, max = 1000)
    private String content;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 1, max = 1000)
    private String author;

}

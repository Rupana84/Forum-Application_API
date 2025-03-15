package com.example.forum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Message content cannot be empty")
    private String content;

    @NotBlank(message = "Author name cannot be empty")
    private String author;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)  // Ensuring channel_id exists
    @JsonBackReference
    private Channel channel;
}
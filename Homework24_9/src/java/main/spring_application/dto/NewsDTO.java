package main.spring_application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NewsDTO {

    private Long id;

    private String title;

    private String text;

    private LocalDateTime timestamp;

    private Long categoryId;

    public NewsDTO(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }
}

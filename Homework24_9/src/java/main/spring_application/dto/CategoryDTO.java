package main.spring_application.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CategoryDTO {

    private Long id;

    private String title;

    private List<NewsDTO> newsDTOList = new ArrayList<>();
}

package main.spring_application.service;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.spring_application.dto.NewsDTO;
import main.spring_application.exception.ResourcesNotFoundException;
import main.spring_application.model.Category;
import main.spring_application.model.News;
import main.spring_application.repositories.CategoryRepository;
import main.spring_application.repositories.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@EqualsAndHashCode
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    private final CategoryRepository categoryRepository;

    public NewsDTO getByIdNews(Integer id) {
        log.info("Получение новости с id: {}", id);
        News news = newsRepository.findById(id).orElseThrow(()
                -> new ResourcesNotFoundException(String.format("Новость с таким id: %d не найдена",id)));
        return mapToDto(news);
    }

    public List<NewsDTO> getByAllNews() {
        if (newsRepository.findAll().isEmpty()) {
            throw new ResourcesNotFoundException("Список новостей пуст");
        }
        log.info("Список всех новостей");
        return newsRepository.findAll()
                .stream()
                .map(NewsService::mapToDto)
                .toList();
    }

    public void createNews(NewsDTO newsDTO) {
        log.info("Создание новой новости");
        News news = mapToEntity(newsDTO);
        Category category = categoryRepository.findById(Math.toIntExact(newsDTO.getCategoryId()))
                        .orElseThrow(()
                                -> new ResourcesNotFoundException(String.format("Категория по такому id: %d не найдена", newsDTO.getCategoryId())));
        news.setCategory(category);
        newsRepository.save(news);
    }

    public void updateNews(NewsDTO newsDTO) {
        if(newsRepository.existsById(Math.toIntExact(newsDTO.getId()))){
            throw new ResourcesNotFoundException(String.format("Новость с таким id: %d не найдена", newsDTO.getId()));
        }
        log.info("Обновление новости c id: {}", newsDTO.getId());
        News news = mapToEntity(newsDTO);
        Category category = categoryRepository.findById(Math.toIntExact(newsDTO.getCategoryId()))
                .orElseThrow(()
                        -> new ResourcesNotFoundException(String.format("Категория с таким id: %d не найдена", newsDTO.getCategoryId())));
        news.setCategory(category);
        newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        if(newsRepository.existsById(Math.toIntExact(id))){
            throw new ResourcesNotFoundException(String.format("Новость с таким id: %d не найдена", id));
        }
        log.info("Удаление новости c id: {}", id);
        newsRepository.deleteById(Math.toIntExact(id));
    }

    public static News mapToEntity(NewsDTO newsDTO){
        News news = new News();
        news.setId(newsDTO.getId());
        news.setText(newsDTO.getText());
        return news;
    }

    public static NewsDTO mapToDto(News news){
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setText(news.getText());
        newsDTO.setCategoryId(news.getCategory().getId());
        return newsDTO;
    }
}

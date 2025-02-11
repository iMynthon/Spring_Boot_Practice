package main.spring_application.controller;
import main.spring_application.dto.NewsDTO;
import main.spring_application.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public NewsDTO getIdNews(@PathVariable Long id) {
        return newsService.getByIdNews(Math.toIntExact(id));
    }

    @GetMapping
    public List<NewsDTO> getAll() {
        return newsService.getByAllNews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDTO createNews(@RequestBody NewsDTO news) {
        newsService.createNews(news);
        return newsService.getByIdNews(Math.toIntExact(news.getId()));
    }

    @PutMapping
    public NewsDTO updateNews(@RequestBody NewsDTO news) {
        newsService.updateNews(news);
        return newsService.getByIdNews(Math.toIntExact(news.getId()));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }


}

package pl.sdacademy.projektplus.quiz.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.projektplus.quiz.dto.CategoriesDto.CategoryDto;
import pl.sdacademy.projektplus.quiz.frontend.Difficulty;
import pl.sdacademy.projektplus.quiz.frontend.GameOptions;
import pl.sdacademy.projektplus.quiz.services.OngoingGameService;
import pl.sdacademy.projektplus.quiz.services.QuizDataService;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
@Log
public class QuizRestController {
    private final QuizDataService quizDataService;
    private final OngoingGameService ongoingGameService;

    @GetMapping("/difficulties")
    public Difficulty[] getDifficulties() {
        return Difficulty.values();
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories() {
        return quizDataService.getQuizCategories();
    }

    @PostMapping("/startGame")
    public ResponseEntity<?> startGame(@RequestBody GameOptions gameOptions) {
        log.info("Starting new game with parameters: " + gameOptions);
        ongoingGameService.init(gameOptions);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package com.example.springrestotot;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteRepository repository;

    QuoteController(QuoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Quote> all() {
        return repository.findAll();
    }

    @PostMapping
    Quote newQuote(@RequestBody Quote newQuote) {
        return repository.save(newQuote);
    }

    // Single item

    @GetMapping("/{id}")
    Quote one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @PutMapping("/{id}")
    Quote replaceQuote(@RequestBody Quote newQuote, @PathVariable Long id) {

        return repository.findById(id)
                .map(quote -> {
                    quote.setContent(newQuote.getContent());
                    quote.setAuthor(newQuote.getAuthor());
                    return repository.save(quote);
                })
                .orElseGet(() -> {
                    newQuote.setId(id);
                    return repository.save(newQuote);
                });
    }

    @DeleteMapping("/{id}")
    void deleteQuote(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

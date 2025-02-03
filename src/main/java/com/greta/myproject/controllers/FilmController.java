package com.greta.myproject.controllers;


import com.greta.myproject.daos.FilmDao;
import com.greta.myproject.entities.Film;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {


    private final FilmDao filmDao;

    public FilmController(FilmDao filmDao) {
        this.filmDao = filmDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Film>> getAllFilm() {
        return ResponseEntity.ok(filmDao.findAll());
    }


    @GetMapping("/{title_film}")
    public ResponseEntity<Film> getFilmByTitle_film(@PathVariable String title_film) {
        return ResponseEntity.ok(filmDao.findByTitle_film(title_film));
    }


    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film createdFilm = filmDao.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }


    @PutMapping("/{title_film}")
    public ResponseEntity<Film> updateFilm(@PathVariable String title_film, @RequestBody Film film) {
        Film updatedFilm = filmDao.update(title_film, film);
        return ResponseEntity.ok(updatedFilm);
    }


    @DeleteMapping("/{title_film}")
    public ResponseEntity<Void> deleteFilm(@PathVariable String title_film) {
        if (filmDao.delete(title_film)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

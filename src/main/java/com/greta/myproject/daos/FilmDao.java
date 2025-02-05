package com.greta.myproject.daos;

import com.greta.myproject.entities.Film;
import com.greta.myproject.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmDao {

    private final JdbcTemplate jdbcTemplate;

    public FilmDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final RowMapper<Film> filmRowMapper = (rs, _) -> new Film(
            rs.getInt("id_film"),
            rs.getString("title_film"),
            rs.getString("image_url"),
            rs.getString("genre"),
            rs.getInt("released_date"),
            rs.getString("overview")
    );


    public List<Film> findAll() {
        String sql = "SELECT * FROM film";
        return jdbcTemplate.query(sql, filmRowMapper);
    }



    public Film findByTitle_film(String title_film){
        String sql = "SELECT * FROM film WHERE title_film = ?";
        return jdbcTemplate.query(sql, filmRowMapper, title_film)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Film avec comme nom : " + title_film + " n'existe pas"));

    }



    public Film save(Film film) {
        String sql = "INSERT INTO film (title_film, image_url, genre, released_date, overview) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, film.getTitle_film(), film.getImage_url(), film.getGenre(), film.getReleased_date(), film.getOverview());
        return findByTitle_film(film.getTitle_film());
    }




    public Film update(String title_film, Film film) {
        if (!filmExists(title_film)) {
            throw new ResourceNotFoundException("Le film avec le titre : " + title_film + " n'existe pas");
        }

        String sql = "UPDATE film SET genre = ? WHERE title_film = ?";
        int rowsAffected = jdbcTemplate.update(sql, film.getGenre(), title_film);

        if (rowsAffected <= 0) {
            throw new ResourceNotFoundException("Échec de la mise à jour du film avec le titre : " + title_film);
        }

        return this.findByTitle_film(title_film);
    }

    // méthode utilitaire à mettre en bas du fichier
    private boolean filmExists(String title_film) {
        String checkSql = "SELECT COUNT(*) FROM film WHERE title_film = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, title_film);
        return count > 0;
    }



    public boolean delete(String title_film) {
        String sql = "DELETE FROM film WHERE title_film = ?";
        int rowsAffected = jdbcTemplate.update(sql, title_film);
        return rowsAffected > 0;
    }

}

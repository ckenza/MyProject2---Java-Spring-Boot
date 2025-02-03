package com.greta.myproject.entities;

public class Film{

    private int id_film;
    private String title_film;
    private String image_url;
    private String genre;
    private int released_date;
    private String overview;




    public Film(){}
    public Film(int id_film, String title_film, String image_url, String genre, int released_date, String overview){
        this.id_film = id_film;
        this.title_film = title_film;
        this.image_url = image_url;
        this.genre = genre;
        this.released_date = released_date;
        this.overview = overview;
    }



    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }



    public String getTitle_film() {
        return title_film;
    }

    public void setTitle_film(String title_film) {
        this.title_film = title_film;
    }



    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }



    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getReleased_date() {
        return released_date;
    }

    public void setReleased_date(int released_date) {
        this.released_date = released_date;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}

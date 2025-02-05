package com.greta.myproject.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    private int id_user;

    @NotBlank(message = "Insérer une adresse mail")
    @Email(message = "le format de l'email n'est pas conforme")
    private String email;

    @NotBlank(message = "Entrer un mot de passe")
    @Size(min = 5, message = "Le mot de passe doit avoir 5 caractères minimum")
    private String password;


    public User(){}
    public User(int id_user, String email, String password){
        this.id_user = id_user;
        this.email = email;
        this.password = password;
    }


    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

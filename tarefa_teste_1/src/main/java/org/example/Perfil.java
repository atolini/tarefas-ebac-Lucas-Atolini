package org.example;

public class Perfil {
    String name;
    Gender gender;

    public Perfil(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}

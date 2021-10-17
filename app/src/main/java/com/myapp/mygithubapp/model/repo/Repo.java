package com.myapp.mygithubapp.model.repo;

public class Repo {
    String name = null;

    public Repo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                '}';
    }
}

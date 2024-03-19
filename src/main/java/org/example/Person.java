package org.example;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

public class Person {
    private String id;
    private String password;
    private String name;
    private boolean login = false;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public boolean isGoodButton() {
        return goodButton;
    }

    public void setGoodButton(boolean goodButton) {
        this.goodButton = goodButton;
    }

    private boolean goodButton = false;
        public Person(String id, String password, String name){
        this.id = id;
        this.password = password;
        this.name = name;
    }
    public Person(){
    }
}

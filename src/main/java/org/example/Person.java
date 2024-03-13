package org.example;

class Person {
    private String id;
    private String password;
    private String name;
    private boolean sign = false;

    public Person(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
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
}

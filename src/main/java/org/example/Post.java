package org.example;

public class Post {
    int num;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit + 1;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String body;
    private int hit; //조회수
    private String Date;
    private Person person = new Person();
    private String date;

    public Post(){
    }

    public Post(int num, String title, String body, Person person, String date){
        this.num = num;
        this.title = title;
        this.body = body;
        this.hit = 0;
        this.person = person;
        this.date = date;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

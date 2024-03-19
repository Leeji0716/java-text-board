package org.example;

import java.util.ArrayList;

public class Post {
    CommonUtill commonUtill = new CommonUtill();
    int num;
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    private String date;
    private Person person = new Person();

    public ArrayList<String> getComment() {
        return Comment;
    }

    public void setComment(ArrayList<String> comment) {
        Comment = comment;
    }

    ArrayList<String> Comment = new ArrayList<>();

    public ArrayList<String> getCommentDate() {
        return CommentDate;
    }

    public void setCommentDate(ArrayList<String> commentDate) {
        CommentDate = commentDate;
    }
    private int good = 0;



    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    ArrayList<String> CommentDate = new ArrayList<>();

    public Post(){
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Post(int num, String title, String body, Person person, String date){
        this.num = num;
        this.title = title;
        this.body = body;
        this.hit = 0;
        this.person = person;
        this.date = date;
    }
    public void addComment(String comment, String commentDate){
        Comment.add(comment);
        CommentDate.add(commentDate);
    }

    public void goodplus() {
        this.good = this.good + 1;
    }

    public void goodminus() {
        this.good = this.good - 1;
    }
}

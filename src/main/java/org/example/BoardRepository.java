package org.example;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
    ArrayList<Post> postList = new ArrayList<>();
    ArrayList<Person> personList = new ArrayList<>();
    CommonUtill commonUtill = new CommonUtill();
    int number = 1;
    public BoardRepository(){
        TestData();
    }

    public void TestData(){
        Person test = new Person("manager", "manager","tester");
        addPost("안녕하세요. 반갑습니다. 자바 공부중이에요.", "body1",test);
        addPost("자바 질문좀 할게요~", "body2",test);
        addPost("정처기 따야 하나요?", "body3",test);
    }

    public ArrayList<Post> getPostList() {
        return postList;
    }

    public void addPerson(String id, String password, String name){
        personList.add(new Person(id, password, name));
    }

    public Post findPostNum(int num){
        for (int i = 0; i < postList.size(); i++) {
            Post post = postList.get(i);
            if (post.getNum() == num) {
                return post;
            }
        }
        return null;
    }
    public List<Person> getPersonList() {
        return personList;
    }

    public Post addPost(String title, String body, Person person) {
        Post post = new Post(number, title, body, person, commonUtill.getCurrentDateTime());
        postList.add(post);
        number++;

        return post;
    }
    public void listPost(Post post){

    }
    public void updatePost(Post post, String title, String body){
        post.setTitle(title);
        post.setBody(body);
    }
    public void deletePost(Post post){
        postList.remove(post);
    }
    public ArrayList<Post> searchTitle(String keyword){

        return null;
    }

    public void sort(String target, String method){

    }
}

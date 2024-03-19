package org.example.domain;

import java.util.ArrayList;

public class BoardView {
    public void detailPost(Post post, Person person){
        System.out.println("===================");
        System.out.println("번호 : " + post.getNum());
        System.out.println("제목 : " + post.getTitle());
        System.out.println("내용 : " + post.getBody());
        System.out.println("등록날짜 : " + post.getDate());
        System.out.println("조회수 : " + post.getHit());
        System.out.println("===================");
    }
    public void commentPost(Post post, Person person){
        for (int i = 0; i < post.getComment().size(); i++) {
            System.out.println("===========댓글==========");
            System.out.println(post.getComment().get(i));
            System.out.println(post.getCommentDate().get(i));
            System.out.println("========================");
        }
    }
    public void PrintPostList(ArrayList<Post> targetList) {
        System.out.println("========================");
        for (Post post : targetList) {
            System.out.println("번호 : " + post.getNum());
            System.out.println("제목 : " + post.getTitle());
            System.out.println("========================");
        }
    }
}

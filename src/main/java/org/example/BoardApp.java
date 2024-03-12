package org.example;


import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

class BoardManager {
    private ArrayList<Post> postList;
    private int count;

    public BoardManager(){
        postList = new ArrayList<>();
        count = 0;
    }
     public void addPost(String title, String body){ //Post 생성
        count++;
        postList.add(new Post(count, title, body, LocalDateTime.now())); //고유번호와 제목, 내용, 입력시간으로 새 Post를 생성
     }
     public List<Post> getPostList(){
        return postList;
     }
     public void updatePost(Post post, String title, String body){
        post.setTitle(title);
        post.setBody(body);
        post.setDateTime(LocalDateTime.now());
        System.out.println(post.getNumber() + "번 게시물이 수정되었습니다.");
     }
     public void deletePost(int postNumber){
         for (int i = 0; i < postList.size(); i++) {
             if (postList.get(i).getNumber() == postNumber) {
                 postList.remove(i);
                 System.out.println(postNumber + "번 게시물이 삭제되었습니다.");
                 return; // 삭제 완료 후 메서드 종료
             }
         }
         System.out.println("존재하지 않는 게시물 번호입니다.-delete");
     }
     public void showPost(Post post){
        //시간 형식화
        LocalDateTime currentDateTime = post.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        System.out.println("========================");
        System.out.println("번호 : " + post.getNumber());
        System.out.println("제목 : " + post.getTitle());
        System.out.println("내용 : " + post.getBody());
        System.out.println("등록 날짜 : " + formattedDateTime);
        System.out.println("========================");
     }
}
class Post {
    private int number;
    private String title;
    private String body;
    private LocalDateTime dateTime;

    public Post(int number, String title, String body, LocalDateTime dateTime){ //post 생성
        this.number = number;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
public class BoardApp {
    public void run(){
        Scanner scan = new Scanner(System.in);
        BoardManager boardManager = new BoardManager();

        //Test Data
        boardManager.addPost("안녕하세요. 반갑습니다. 자바 공부중이에요.", "body1");
        boardManager.addPost("자바 질문좀 할게요~", "body2");
        boardManager.addPost("정처기 따야 하나요?", "body3");

        //반복 횟수를 정할 수 없음. => 무한 반복 (while) 구조
        while(true) { //반복 조건이 true이기 때문에 무한 반복 구조
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) { //숫자가 아닌 경우 같다라는 표현 사용 시, ==이 아닌 .equals를 사용
                System.out.println("프로그램을 종료합니다.");
                break; //반복문 탈출
            }
            else if (cmd.equals("add")) {
                System.out.print("제목을 입력하세요 : "); //제목 입력받음
                String title = scan.nextLine();

                System.out.print("내용을 입력하세요 : "); //내용 입력받음
                String body = scan.nextLine();

                boardManager.addPost(title, body); //제목과 내용을 입력받고 boardManager에게 addPost작업을 시킴

                System.out.println("게시물이 등록되었습니다."); //게시물 등록
            }
            else if (cmd.equals("list")) {
                System.out.println("========================");
                List<Post> posts = boardManager.getPostList(); //Post의 리스트를 가져온다.
                for (Post post : posts) {
                    System.out.println("번호 : " + post.getNumber());
                    System.out.println("제목 : " + post.getTitle());
                    System.out.println("========================");
                }
            }
            else if (cmd.equals("update")) {
                Post indexPost = null;
                boolean postNum = false;
                System.out.print("수정할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());
                List<Post> posts = boardManager.getPostList();
                for(Post post : posts){ //리스트에 있는지 찾는다
                    if(post.getNumber() == num){
                        indexPost = post;
                        postNum = true;
                    }
                }
                if(postNum){ //있다
                    System.out.print("새 제목을 입력하세요 : ");
                    String title = scan.nextLine();

                    System.out.print("새 내용을 입력하세요 : ");
                    String body = scan.nextLine();

                    boardManager.updatePost(indexPost, title, body);
                }else {
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
            else if (cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());
                boardManager.deletePost(num);
            }
            else if (cmd.equals("detail")) {
                Post indexPost = null;
                boolean postNum = false;
                System.out.print("상세보기 할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());
                List<Post> posts = boardManager.getPostList();

                for (Post post : posts) {
                    if (num == post.getNumber()) {
                        indexPost = post;
                        postNum = true;
                    }
                }
                if (postNum) {
                    boardManager.showPost(indexPost);
                } else {
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
        }
    }
}

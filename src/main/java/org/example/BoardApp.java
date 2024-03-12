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
    private ArrayList<DetailComment> detailList;
    private int good = 0;
    public BoardManager(){
        postList = new ArrayList<>();
        detailList = new ArrayList<>();
        count = 0;
    }
     public void addPost(String title, String body){ //Post 생성
        count++;
        postList.add(new Post(count, title, body, LocalDateTime.now()));
     }
     public List<Post> getPostList(){
        return postList;
     }
     public ArrayList<DetailComment> getDetailList() {
        return detailList;
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
         System.out.println("존재하지 않는 게시물 번호입니다.");
    }
    public void listPost(Post post){
         System.out.println("번호 : " + post.getNumber());
         System.out.println("제목 : " + post.getTitle());
         System.out.println("========================");
    }
    public void detailPost(Post post){
        Scanner scan = new Scanner(System.in);
        //시간 형식화
        LocalDateTime currentDateTime = post.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        System.out.println("========================");
        System.out.println("번호 : " + post.getNumber());
        System.out.println("제목 : " + post.getTitle());
        System.out.println("내용 : " + post.getBody());
        System.out.println("등록 날짜 : " + formattedDateTime);
        System.out.println("조회수 : " + post.getView());
        System.out.println("========================");

        while(true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            String detailNum = scan.nextLine();
            if (detailNum.equals("1")){
                System.out.print("댓글 달기 : ");
                String comment = scan.nextLine();
                detailList.add(new DetailComment(comment, LocalDateTime.now()));

                System.out.println("========================");
                System.out.println("번호 : " + post.getNumber());
                System.out.println("제목 : " + post.getTitle());
                System.out.println("내용 : " + post.getBody());
                System.out.println("등록 날짜 : " + formattedDateTime);
                System.out.println("조회수 : " + post.getView());
                System.out.println("========================");
                for (int i = 0; i < detailList.size(); i++) {
                    LocalDateTime commentTime = detailList.get(i).getCommentTime();
                    DateTimeFormatter formatterDe = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDeTime = commentTime.format(formatterDe);

                    System.out.println("===========댓글==========");
                    System.out.println(detailList.get(i).getComment());
                    System.out.println(formattedDeTime);
                    System.out.println("========================");
                }
            }
            else if (detailNum.equals("2")){
                good++;
                System.out.println("추천 갯수 : " + good);
            }
            else if (detailNum.equals("3")){
                System.out.println("수정");
            }
            else if (detailNum.equals("4")){
                System.out.println("삭제");
            }
            else if (detailNum.equals("5")){
                System.out.println("상세보기 화면을 빠져나갑니다.");
                break;
            }
        }


    }
    public void searchTitle(String keyword){
        for(int i = 0; i < postList.size(); i++){
            if (postList.get(i).getTitle().contains(keyword)){
                listPost(postList.get(i));
            }
        }
         System.out.println("검색 결과가 없습니다.");
    }
}
class DetailComment {
    private String Comment;
    private LocalDateTime CommentTime;

    public DetailComment(String comment, LocalDateTime commentTime){
        this.Comment = comment;
        this.CommentTime = commentTime;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public LocalDateTime getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        CommentTime = commentTime;
    }
}
class Post {
    private int number;
    private String title;
    private String body;
    private LocalDateTime dateTime;
    private int view = 1;

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

    public int getView() {
        return view++;
    }

    public void setView(int view) {
        this.view = view;
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
                System.out.print("제목을 입력하세요 : ");
                String title = scan.nextLine();
                System.out.print("내용을 입력하세요 : ");
                String body = scan.nextLine();
                boardManager.addPost(title, body);
                System.out.println("게시물이 등록되었습니다.");
            }
            else if (cmd.equals("list")) {
                System.out.println("========================");
                List<Post> posts = boardManager.getPostList(); //Post의 리스트를 가져온다.
                for (Post post : posts) {
                    boardManager.listPost(post);
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
                    boardManager.detailPost(indexPost);
                } else {
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
            else if (cmd.equals("search")){
                System.out.print("검색 키워드를 입력해주세요. : ");
                String Keyword = scan.nextLine();
                boardManager.searchTitle(Keyword);
            }
        }
    }
}

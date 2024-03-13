package org.example;
import javax.xml.stream.events.Comment;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;
class BoardManager {
    private ArrayList<Post> postList;
    private ArrayList<Person> personList;
    private int count;
    private int good = 0;
    public BoardManager(){
        postList = new ArrayList<>();
        personList = new ArrayList<>();
        count = 0;
    }
    public void addPost(String title, String body){
        count++;
        postList.add(new Post(count, title, body, LocalDateTime.now()));
     } //Manager Post 생성
    public void addPost(String title, String body, Person person){ //회원 Post 생성
        count++;
        postList.add(new Post(count, title, body, LocalDateTime.now(), person));
    } //Post 생성
    public void addPerson(String id, String password, String name) {
        personList.add(new Person(id, password, name));
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
         System.out.println("존재하지 않는 게시물 번호입니다.");
    }
    public void listPost(){
        System.out.println("========================");
        List<Post> posts = getPostList();
        for (Post post : posts) {
            System.out.println("번호 : " + post.getNumber());
            System.out.println("제목 : " + post.getTitle());
            System.out.println("========================");
        }
    }
    public void listPost(Post post){
        System.out.println("========================");
        System.out.println("번호 : " + post.getNumber());
        System.out.println("제목 : " + post.getTitle());
        System.out.println("========================");
    }
    public void detailPost(Post post){
        showpost(post);
        showcomment(post);
    } //비회원 detailpost
    public void logDetailPost(Post post){
        showpost(post);
        showcomment(post);
        viewdetail(post);
    } //회원 detailpost
    public void viewdetail(Post post){
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            String detailNum = scan.nextLine();
            if (detailNum.equals("1")){
                System.out.print("댓글 달기 : ");
                String comment = scan.nextLine();
                post.Comment(comment, LocalDateTime.now());

                showpost(post);
                showcomment(post);
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
    } //상세보기 - 회원기능
    public void showpost(Post post) {
        LocalDateTime currentDateTime = post.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("========================");
        System.out.println("번호 : " + post.getNumber());
        System.out.println("제목 : " + post.getTitle());
        System.out.println("내용 : " + post.getBody());
        System.out.println("등록 날짜 : " + formattedDateTime);
        System.out.println("조회수 : " + post.getView());
        System.out.println("작성자 : " + post.getNickName());
        System.out.println("========================");
    } //post 보여주기
    public void showcomment(Post post) {
        for(int i = 0; i < post.getComment().size(); i++){
            LocalDateTime commentTime = post.getCommentTime().get(i);
            DateTimeFormatter formatterDe = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            String formattedDeTime = commentTime.format(formatterDe);
            System.out.println("===========댓글==========");
            System.out.println(post.getComment().get(i));
            System.out.println(formattedDeTime);
            System.out.println("========================");
        }
    } //comment 보여주기
    public void searchTitle(String keyword){
        for(int i = 0; i < postList.size(); i++){
            if (postList.get(i).getTitle().contains(keyword)){
                listPost(postList.get(i));
            }
        }
         System.out.println("검색 결과가 없습니다.");
    }
    public void indexPost(int num){
        List<Post> posts = getPostList();
        for (Post post : posts) { //번호가 있는지 확인
            if (num == post.getNumber()) {
                detailPost(post);
                break;
            }
            System.out.println("존재하지 않는 게시물입니다.");
            break;
        }
    }
    public void logIndexPost(int num){
        List<Post> posts = getPostList();
        for (Post post : posts) { //번호가 있는지 확인
            if (num == post.getNumber()) {
                logDetailPost(post);
                break;
            }
            System.out.println("존재하지 않는 게시물입니다.");
            break;
        }
    }
    public Person login(String id, String password) {
        boolean loggedIn = false; // 로그인 여부를 나타내는 변수
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId().equals(id) && person.getPassword().equals(password)) {
                System.out.println(person.getName() + "님 환영합니다!");
                loggedIn = true;
                person.setSign(loggedIn);
                return person;
            }
        }
        if (!loggedIn) {
            System.out.println("잘못된 회원 정보입니다.");
        }
        return null;
    }
}
class Post {
    private int number;
    private String title;
    private String body;
    private LocalDateTime dateTime;
    private int view = 1;
    private String nickName = "";

    public ArrayList<LocalDateTime> getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(ArrayList<LocalDateTime> commentTime) {
        this.commentTime = commentTime;
    }

    public ArrayList<String> getComment() {
        return comment;
    }

    public void setComment(ArrayList<String> comment) {
        this.comment = comment;
    }

    ArrayList<String> comment = new ArrayList<>();
    ArrayList<LocalDateTime> commentTime = new ArrayList<>();
    public Post(int number, String title, String body, LocalDateTime dateTime){
        this.number = number;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
    } //post 생성

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Post(int number, String title, String body, LocalDateTime dateTime, Person person){
        this.number = number;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
        this.nickName = person.getName();
    } //회원 post 생성
    public void Comment(String comment, LocalDateTime commentTime){
        this.comment.add(comment);
        this.commentTime.add(commentTime);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
    Scanner scan = new Scanner(System.in);
    BoardManager boardManager = new BoardManager();
    public void run(){
        //Test Data
        boardManager.addPost("안녕하세요. 반갑습니다. 자바 공부중이에요.", "body1");
        boardManager.addPost("자바 질문좀 할게요~", "body2");
        boardManager.addPost("정처기 따야 하나요?", "body3");

        while(true) {
            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            }
            else if (cmd.equals("add")) {
                if(log인여부){
                    add(person가 작성);
                }else {
                    System.out.println("로그인 후 이용해 주세요.");
                }
            }
            else if (cmd.equals("list")) {
                list();
            }
            else if (cmd.equals("update")) {
                System.out.println("로그인 후 이용해 주세요.");
            }
            else if (cmd.equals("delete")) {
                System.out.println("로그인 후 이용해 주세요.");
            }
            else if (cmd.equals("detail")) {
                detail();
            }
            else if (cmd.equals("search")){
                search();
            }
            else if (cmd.equals("signup")){
                signup();
            }
            else if (cmd.equals("login")){
                logIn();
            }
            else{
                System.out.println("올바른 명령어를 입력해주세요 : ");
            }
        }
    } //비회원 상태일 때 작동

    private void add() {
        System.out.print("제목을 입력하세요 : ");
        String title = scan.nextLine();
        System.out.print("내용을 입력하세요 : ");
        String body = scan.nextLine();
        boardManager.addPost(title, body, person);
        System.out.println("게시물이 등록되었습니다.");
    }

    private void list() {
        boardManager.listPost();
    }

    private void detail() {
        System.out.print("상세보기 할 게시물 번호 : ");
        int num = Integer.parseInt(scan.nextLine());
        boardManager.indexPost(num);
    }

    private void search() {
        System.out.print("검색 키워드를 입력해주세요. : ");
        String Keyword = scan.nextLine();
        boardManager.searchTitle(Keyword);
    }

    private void logIn() {
        System.out.print("아이디 : ");
        String id = scan.nextLine();
        System.out.print("비밀번호 : ");
        String password = scan.nextLine();
        Person logInPerson = boardManager.login(id, password);
        if (logInPerson != null) {
            logrun(logInPerson);
        } else {
            System.out.println("로그인에 실패했습니다.");
        }
    }

    private void signup() {
        System.out.println("==== 회원 가입을 진행합니다. ====");
        System.out.print("아이디를 입력해주세요 : ");
        String id = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String name = scan.nextLine();
        boardManager.addPerson(id, password, name);
        System.out.println("=== 회원 가입이 완료되었습니다. ===");
    }

    public void logrun(Person person){ //회원 상태일 때 작동
        Scanner scan = new Scanner(System.in);
        BoardManager boardManager = new BoardManager();

        while(true) {
            System.out.print("명령어를 입력해주세요[" + person.getId() + "(" + person.getName() + ")] : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            }
            else if (cmd.equals("add")) {

            }
            else if (cmd.equals("list")) {
                list();
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
                delete();
            }
            else if (cmd.equals("detail")) {
                System.out.print("상세보기 할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());
                boardManager.logIndexPost(num);
            }
            else if (cmd.equals("search")){
                System.out.print("검색 키워드를 입력해주세요. : ");
                String Keyword = scan.nextLine();
                boardManager.searchTitle(Keyword);
            }
            else{
                System.out.println("올바른 명령어를 입력해주세요 : ");
            }
        }

    }

    private void delete() {
        System.out.print("삭제할 게시물 번호 : ");
        int num = Integer.parseInt(scan.nextLine());
        boardManager.deletePost(num);
    }
}

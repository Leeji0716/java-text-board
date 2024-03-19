package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardManager {
    CommonUtill commonUtill = new CommonUtill();
    BoardRepository boardRepository = new BoardRepository();
    BoardView boardView = new BoardView();

    Scanner scan = commonUtill.getScanner();

    Person indexPerson = null;





    private void sort() {
        System.out.println("정렬 대상을 선택해주세요 : (1. 번호, 2. 조회수");
        String sortTarget = scan.nextLine();
        System.out.println("정렬 방법을 선택해주세요 : (1.오름차순, 2.내림차순");
        String sortMathod = scan.nextLine();
    }

    public void logcmd() {
        if (indexPerson != null){
            System.out.print("명령어를 입력하세요[" + indexPerson.getId() + "(" + indexPerson.getName() + ")] : ");
        }
        else{
            System.out.print("명령어 : ");
        }
    }

    public void login() {
        System.out.print("아이디 : ");
        String id = scan.nextLine();
        System.out.print("비밀번호 : ");
        String password = scan.nextLine();
        indexPerson = boardRepository.logYN(id, password);
        if(indexPerson!=null){
            System.out.println(indexPerson.getName() + "님 환영합니다!");
            return;
        }
        System.out.println("비밀번호를 틀렸거나, 잘못된 회원정보입니다.");
    }
    public void logout(){
        indexPerson = null;
    }

    public void signup() {
        System.out.println("==== 회원 가입을 진행합니다 ====");
        System.out.print("아이디를 입력해주세요 : ");
        String id = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String name = scan.nextLine();
        boardRepository.addPerson(id, password, name);
        System.out.println("==== 회원가입이 완료되었습니다. ====");
    }

    public void detail() {
        System.out.print("상세보기 할 게시물 번호 : ");
        int num = Integer.parseInt(scan.nextLine());

        Post post = boardRepository.findPostNum(num);
        if (post != null) {
            post.setHit(post.getHit());
            boardView.detailPost(post, indexPerson);
        } else {
            System.out.println("존재하지 않는 게시물입니다.");
        }

        while(indexPerson != null) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            String detailNum = scan.nextLine();
            if (detailNum.equals("1")) {
                System.out.print("댓글 달기 : ");
                String comment = scan.nextLine();
                assert post != null;
                post.addComment(comment, commonUtill.getCurrentDateTime());

                boardView.detailPost(post, indexPerson);
                boardView.commentPost(post, indexPerson);

            } else if (detailNum.equals("2")) {
                indexPerson.setGoodButton(!indexPerson.isGoodButton());
                if (indexPerson.isGoodButton()){
                    post.setGood(post.getGood() + 1);
                }
                else {
                    post.setGood(post.getGood() - 1);
                }
                System.out.println("추천 갯수 : " + post.getGood());

            } else if (detailNum.equals("3")) {
                assert post != null;
                if(post.getPerson().equals(indexPerson)){
                    System.out.println("수정");
                    updatePost(post);
                }else {
                    System.out.println("접근할 수 없습니다.");
                }
            } else if (detailNum.equals("4")) {
                assert post != null;
                if(post.getPerson().equals(indexPerson)){
                    System.out.println("삭제");
                    deletePost(post);
                }else {
                    System.out.println("접근할 수 없습니다.");
                }
            } else if (detailNum.equals("5")) {
                System.out.println("상세보기 화면을 빠져나갑니다.");
                break;
            }
        }
    }

    private void deletePost(Post post) {
        boardRepository.deletePost(post);
        System.out.println("게시물이 삭제되었습니다.");
    }

    private void updatePost(Post post) {
        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();
        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();
        boardRepository.updatePost(post, newTitle, newBody);
        System.out.println("게시물이 수정되었습니다.");
    }

    public void search() {
        System.out.print("검색 키워드를 입력해주세요. : ");
        String keyword = scan.nextLine();
        ArrayList<Post> searchedList = boardRepository.searchTitle(keyword);

        boardView.PrintPostList(searchedList);
    }

    public void delete() {
        if(indexPerson != null){
            System.out.print("삭제할 게시물 번호 : ");
            int num = Integer.parseInt(scan.nextLine());
            Post post = boardRepository.findPostNum(num);
            deletePost(post);
        }else {
            System.out.println("로그인 후 이용해주세요.");
        }
    }

    public void update() {
        if(indexPerson != null){
            System.out.print("수정할 게시물 번호 : ");
            int num = Integer.parseInt(scan.nextLine());
            Post post = boardRepository.findPostNum(num);

            if (post != null) { //있다
                updatePost(post);
            } else {
                System.out.println("존재하지 않는 게시물입니다.");
            }
        }else {
            System.out.println("로그인 후 이용해주세요.");
        }
    }

    public void list() {
        ArrayList<Post> postList = boardRepository.getPostList();
        boardView.PrintPostList(postList);

    }

    public void add() {
        if (indexPerson != null){
            System.out.print("제목을 입력하세요 : ");
            String title = scan.nextLine();
            System.out.print("내용을 입력하세요 : ");
            String body = scan.nextLine();

            boardRepository.addPost(title, body, indexPerson);
            System.out.println("게시물이 등록되었습니다.");
        }
        else {
            System.out.println("로그인 후 이용해주세요.");
        }
    }
}

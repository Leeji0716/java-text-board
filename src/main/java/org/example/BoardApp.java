package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    public void run(){
        Scanner scan = new Scanner(System.in);

        //변수에는 하나의 값만 저장 가능하므로 여러개의 게시물을 저장하려면 ArrayList를 사용한다.
        ArrayList<String> titleList = new ArrayList<>(); //제목 배열
        ArrayList<String> bodyList = new ArrayList<>(); //내용 배열
        ArrayList<Integer> numList = new ArrayList<>(); //고유번호 배열
        ArrayList<String> timeList = new ArrayList<>(); //날짜 배열

        int count = 0; //고유번호

        //반복 횟수를 정할 수 없음. => 무한 반복 (while) 구조
        while(true) { //반복 조건이 true이기 때문에 무한 반복 구조
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            //프로그램 종료
            if (cmd.equals("exit")) { //숫자가 아닌 경우 같다라는 표현 사용 시, ==이 아닌 .equals를 사용
                System.out.println("프로그램을 종료합니다.");
                break; //반복문 탈출
            }
            else if (cmd.equals("add")) {
                System.out.print("제목을 입력하세요 : "); //제목 입력받음
                String title = scan.nextLine();
                titleList.add(title);

                System.out.print("내용을 입력하세요 : "); //내용 입력받음
                String body = scan.nextLine();
                bodyList.add(body);

                System.out.println("게시물이 등록되었습니다.");

                count++; //고유번호 지정
                numList.add(count); //고유번호 리스트에 입력

                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //형식 생성
                String formattedDateTime = dateTime.format(formatter); //변환된 형식을 적용
                timeList.add(formattedDateTime); //형식으로 변환된 시간을 리스트에 추가

            }
            else if (cmd.equals("list")) {
                System.out.println("========================");
                for (int i = 0; i < numList.size(); i++) {

                    int num1 = numList.get(i);
                    System.out.println("번호 : " + num1);

                    String title = titleList.get(i);
                    System.out.println("제목 : " + title);

                    System.out.println("========================");
                }
            }
            else if (cmd.equals("update")) {
                int index = 0;
                int forcount = 0;
                System.out.print("수정할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());

                //입력받은 번호와 등록된 번호가 같은 인덱스를 찾는다.
                //for문이 한번이라도 실행되었는지 확인한다.
                for (int i = 0; i < numList.size(); i++) {
                    if (num == numList.get(i)) {
                        index = i;
                        forcount++;
                    }
                }
                if (forcount > 0) { //for문이 한번이라도 실행되었다면 인덱스를 찾아 수정한다.
                    System.out.print("제목 : ");
                    String title = scan.nextLine();
                    titleList.set(index, title);

                    System.out.print("내용 : ");
                    String body = scan.nextLine();
                    bodyList.set(index, body);

                    System.out.println(num + "번 게시물이 수정되었습니다.");

                    //수정 시간 update
                    LocalDateTime dateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //형식 생성
                    String formattedDateTime = dateTime.format(formatter); //변환된 형식을 적용
                    timeList.set(index, formattedDateTime); //인덱스에 변환된 시간 수정
                } else { //for문이 한번도 실행되지 않았거나, 그 외에는 모두 없는 게시물이다.
                    System.out.println("없는 게시물 번호입니다.");
                }
            }
            else if (cmd.equals("delete")) {
                int index = 0;
                int forcount = 0;
                System.out.print("삭제할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());

                for (int i = 0; i < numList.size(); i++) {
                    if (num == numList.get(i)) {
                        index = i;
                        forcount++;
                    }
                }
                if (forcount > 0) {
                    titleList.remove(index);
                    bodyList.remove(index);
                    numList.remove(index);
                    timeList.remove(index);
                    System.out.println(num + "번 게시물이 삭제되었습니다.");

                } else {
                    System.out.println("없는 게시물입니다.");
                }
            }
            else if (cmd.equals("detail")) {
                int index = 0;
                int forcount = 0;
                System.out.print("상세보기 할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());

                for (int i = 0; i < numList.size(); i++) {
                    if (num == numList.get(i)) {
                        index = i;
                        forcount++;
                    }
                }
                if (forcount > 0) {
                    System.out.println("========================");
                    System.out.println("번호 : " + numList.get(index));
                    System.out.println("제목 : " + titleList.get(index));
                    System.out.println("내용 : " + bodyList.get(index));
                    System.out.println("등록 날자 : " + timeList.get(index));
                    System.out.println("========================");
                } else { //없으면 출력
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
        }
    }
}
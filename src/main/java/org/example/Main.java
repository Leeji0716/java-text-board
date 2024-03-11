package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //변수에는 하나의 값만 저장 가능하므로 여러개의 게시물을 저장하려면 ArrayList를 사용한다.
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> bodyList = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();

        int count = 0;

        //반복 횟수를 정할 수 없음. => 무한 반복 (while) 구조
        while(true){ //반복 조건이 true이기 때문에 무한 반복 구조
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            //프로그램 종료
            if (cmd.equals("exit")){ //숫자가 아닌 경우 같다라는 표현 사용 시, ==이 아닌 .equals를 사용
                System.out.println("프로그램을 종료합니다.");
                break; //반복문 탈출
            }
            else if (cmd.equals("add")){
                System.out.print("제목을 입력하세요 : "); //제목 입력받음
                String title = scan.nextLine();
                titleList.add(title);

                System.out.print("내용을 입력하세요 : "); //내용 입력받음
                String body = scan.nextLine();
                bodyList.add(body);

                System.out.println("게시물이 등록되었습니다.");
                count++; //고유번호 지정
                numList.add(count); //고유번호 리스트에 입력
            }
            else if (cmd.equals("list")){
                System.out.println("========================");
                for(int i = 0; i < titleList.size(); i++){
                    String title = titleList.get(i);
                    System.out.println("제목 : " + title);

                    String body = bodyList.get(i);
                    System.out.println("내용 : " + body);

                    System.out.println("========================");
                }
            }
            else if (cmd.equals("update")){
                int index = 0;
                int forcount = 0;
                System.out.print("수정할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());

                for(int i = 0; i < numList.size(); i++){
                    if (num == numList.get(i)){
                        index = i;
                        forcount++;
                    }
                }
                if (forcount > 0){
                    System.out.print("제목 : ");
                    String title = scan.nextLine();
                    titleList.set(index,title);

                    System.out.print("내용 : ");
                    String body = scan.nextLine();
                    bodyList.set(index,body);

                    System.out.println(num + "번 게시물이 수정되었습니다.");
                }
                else {
                    System.out.println("없는 게시물 번호입니다.");
                }
            }
            else if (cmd.equals("delete")){
                int index = 0;
                int forcount = 0;
                System.out.print("삭제할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());

                for(int i = 0; i < numList.size(); i++){
                    if (num == numList.get(i)){
                        index = i;
                        forcount++;
                    }
                }
                if(forcount > 0){
                    titleList.remove(index);
                    bodyList.remove(index);
                    numList.remove(index);
                    System.out.println(num + "번 게시물이 삭제되었습니다.");

                }
                else{
                    System.out.println("없는 게시물입니다.");
                }
            }
            else if (cmd.equals("detail")){
                int index = 0;
                int forcount = 0;
                System.out.print("상세보기 할 게시물 번호 : ");
                int num = Integer.parseInt(scan.nextLine());

                for(int i = 0; i < numList.size(); i++){
                    if (num == numList.get(i)){
                        index = i;
                        forcount++;
                    }
                }
                if (forcount > 0){
                    System.out.println("========================");
                    System.out.println("번호 : " + numList.get(index));
                    System.out.println("제목 : " + titleList.get(index));
                    System.out.println("내용 : " + bodyList.get(index));
                    System.out.println("========================");
                }
                else { //없으면 출력
                    System.out.println("존재하지 않는 게시물입니다.");
                }
            }
        }
    }
}
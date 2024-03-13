package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PersonManager personManager = new PersonManager();
        while(true){
            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();

            if(cmd.equals("signup")){
                System.out.println("==== 회원 가입을 진행합니 다. ====");
                System.out.print("아이디를 입력해주세요 : ");
                String id = scan.nextLine();
                System.out.print("비밀번호를 입력해주세요 : ");
                String password = scan.nextLine();
                System.out.print("닉네임을 입력해주세요 : ");
                String name = scan.nextLine();

                personManager.addPerson(id, password, name);
                System.out.println("=== 회원 가입이 완료되었습니다. ===");

            }
            else if(cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("올바른 명령어를 입력해주세요.");
            }

        }
//        BoardApp app = new BoardApp();
//        app.run();
    }
}
class personManager{
    private ArrayList<Person> personList;
    public personManager(){
        personList = new ArrayList<>();
    }
    public void addPerson(String id, String password, String name){
        personList.add(new Person(id, password, name));
    }
}
class Person{
    private String id;
    private String password;
    private String name;
    public Person(String id, String password, String name){
        this.id = id;
        this.password = password;
        this.name = name;
    }
}

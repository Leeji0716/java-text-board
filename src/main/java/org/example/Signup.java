package org.example;

import java.util.ArrayList;
import java.util.Scanner;

class SignManager {
    private ArrayList<Person> personList;

    public SignManager() {
        personList = new ArrayList<>();
    }

    public void addPerson(String id, String password, String name) {
        personList.add(new Person(id, password, name));
    }
    public void login(String id, String password) {
        boolean loggedIn = false; // 로그인 여부를 나타내는 변수
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.getId().equals(id) && person.getPassword().equals(password)) {
                System.out.println(person.getName() + "님 환영합니다!");
                loggedIn = true;
                person.setSign(loggedIn);
                BoardApp app = new BoardApp();
                app.run(person);
                break;
            }
        }
        if (!loggedIn) {
            System.out.println("잘못된 회원 정보입니다.");
        }
    }
}
class Person {
    private String id;
    private String password;
    private String name;

    private boolean sign = false;

    public Person(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
public class Signup {
    public void run() {
        Scanner scan = new Scanner(System.in);
        SignManager signManager = new SignManager();
        while (true) {
            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("signup")) {
                System.out.println("==== 회원 가입을 진행합니다. ====");
                System.out.print("아이디를 입력해주세요 : ");
                String id = scan.nextLine();
                System.out.print("비밀번호를 입력해주세요 : ");
                String password = scan.nextLine();
                System.out.print("닉네임을 입력해주세요 : ");
                String name = scan.nextLine();
                signManager.addPerson(id, password, name);
                System.out.println("=== 회원 가입이 완료되었습니다. ===");
            }
            else if (cmd.equals("login")) {
                System.out.print("아이디 : ");
                String id = scan.nextLine();
                System.out.print("비밀번호 : ");
                String password = scan.nextLine();
                signManager.login(id, password);
            }
            else if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else {
                System.out.println("올바른 명령어를 입력해주세요.");
            }
        }
    }

}

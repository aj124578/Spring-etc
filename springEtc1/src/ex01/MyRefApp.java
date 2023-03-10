package ex01;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MyRefApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String path = "/save";

        // 1. 컴퍼넌트 스캔
        // -> 특정 package를 읽어서 그 안에 특정 어노테이션을 찾아야함

        // 2. Dispatcher 매핑

        // path = "/login" -> uc.login()
        // path = "/join" -> uc.join();
        UserController uc = new UserController();
        
        BoardController bc = new BoardController();

        // if (path.equals("/login")) {
        //     uc.login();
        // }else if(path.equals("/join")){
        //     uc.join();
        // }else if(path.equals("/joinForm")){
        //     uc.joinForm();
        // }

        Method[] methods =  bc.getClass().getDeclaredMethods();
        System.out.println(methods.length);
        for (int i = 0; i < methods.length; i++) {
            Method mt = methods[i];
            // System.out.println(mt.getName());

            Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
            RequestMapping rm = (RequestMapping) anno; // Annotation 은 모든 어노테이션의 부모 -> 그러므로 다운캐스팅 가능
            System.out.println(rm.uri());
            if (rm.uri().equals(path)) {
                 mt.invoke(bc);
             }
             // if (anno == null) {
             //     System.out.println("테스트 : 못찾음");
             // }
        }
    }

}

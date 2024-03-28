package com.codingbox.core3.web.request;

import com.codingbox.core3.web.dto.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class RequestParamController {

    // 반환타입이 없으면서 응답값을 직접입력하면, view 조회하지 않는다.
    // void타입이면서 response에 값을 쓰면, 쓴 값이 화면에 보이게 된다.
    @RequestMapping("/request-param-v1")
    public void requestparamv1(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        response.getWriter().write("ok");
    }

    /*
    @responsebody
        - view조회를 무시하고, http messege body에 직접 해당 내용 입력
    @requestparam
        - 파라미터 이름으로 바인딩
     */

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestparamv2(@RequestParam("username") String username, @RequestParam("age") int age) {
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
    @requestparam 사용
    http 파라미터 이름이 변수 이름과 같으면
    @   requestparam(name="...") 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestparamv3(@RequestParam String username, @RequestParam int age) {
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
    @requestparam 생략가능
    -> String int 등과 같은 단순 타입이면 생략 가능
    --------------
    권장하진 않는다
    requestparam이 있으면 명확하게 요청 파라미터에서 데이터를 읽어온다는 것을 알 수 있다
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestparamv4(String username, int age) {
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
    @requestparam.required 옵션
        - 기본값은 true이다
        - /requset-param-requierd -> username이 없으므로 예외
        - /requset-param-requierd?username -> 빈 문자로 통과
        - /requset-param-requierd -> int age 주의
                                        null값에 대체 때문에 integer
                                        defalut value 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-requierd")
    public String requestparamvRequierd(@RequestParam(required = true) String username,
                                        @RequestParam(required = true) Integer age) {
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
        @requestparam
            - defaultvalue 옵션 사용시
                - 기본값을 세팅
                - 빈 문자열인 경우에도 적용
                - 이미 기본값이 세팅이 되었기 때문에 required옵션이 의미가 없다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestparamvDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                        @RequestParam(required = false, defaultValue = "-1") int age) {
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestparamvMap(@RequestParam Map<String, Object> paramMap) {
        System.out.println("username : " + paramMap.get("username"));
        System.out.println("age : " + paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        System.out.println("username : " + helloData.getUsername());
        System.out.println("age : " + helloData.getAge());
        return "ok";
    }

    /*
    @modelattribute 사용
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) {
        System.out.println("username : " + helloData.getUsername());
        System.out.println("age : " + helloData.getAge());
        System.out.println("hellodata : " + helloData.toString());
        return "ok";
    }

    /*
    @modelattribute, @requestparam 생략 가능
        - 혼란이 발생할 수 있다
        - String, int, integet, ... 단순 타입이면 => @requestparam이 생략되었다고 판단
        - dto객체가 파라미터에 있ㅇ면 -> @modelattribute이 생략 되었다고 판단
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(HelloData helloData) {
        System.out.println("username : " + helloData.getUsername());
        System.out.println("age : " + helloData.getAge());
        System.out.println("hellodata : " + helloData.toString());
        return "ok";
    }


}

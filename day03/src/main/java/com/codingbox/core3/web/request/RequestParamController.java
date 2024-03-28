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

@Controller
public class RequestParamController {
    // 반환 타입에 없으면서 응답값을 직접 일력하면, view를 조회하지 않는다.
    // void 타입이면서 response에 값을 쓰면, 쓴 값이 화면에 보이게 된다.
    @RequestMapping("/request-param-v1")
    public void requestparamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("usernaem : " + username);
        System.out.println("age : " + age);
        resp.getWriter().write("ok");
    }

    /* @ResponseBody
    *  - view 조회를 무시하고, http message body에 직접 해당 내용 입력
    * -------------------------------------------------------------------
    * @RequestParam
    * - 파라미터 이름으로 바인딩*/
    @RequestMapping("/request-param-v2")
    public String requestparamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge){
        System.out.println("usernaem : " + memberName);
        System.out.println("age : " + memberAge);
        return "ok";
    }

    /*@RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면
     * @RequestParam(name="...")생략가능*/
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestparamV3(@RequestParam String username, @RequestParam int age){
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
     * @RequestParam 생략가능
     * -> String, int등과 같은 단순 타입이면 생략 가능
     * -------------------------------------------
     * 권장하진 않는다
     * @RequestParam이 있으면 명확하게 요청 파라미터에서 데이터를 읽어온다는 것을 알 수 있다
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestparamV4(String username, int age){
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
     * @RequestParam.required 옵션
     * - /request-param=required - >username이 없으므로 예외
     * - /request-param=required - >username?username -> 빈문자로 통과
     * - /request-param=required - >username - int age 주의
                                    null 값에 대한 대체 때문에 Integer 로 작성
                                    defaultValue 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestparamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age){
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }

    /*
     * @RequestParam
     * - defaultValue 옵션 사용시
     *      -> 기본값을 세팅
     *      -> 빈 문자열인 경우에도 적용
     *      -> 이미 기본값이 세팅이 되었기 때문에 required옵션이 의미가 없다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestparamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") Integer age){
        System.out.println("username : " + username);
        System.out.println("age : " + age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestparamMap(@RequestParam Map<String, Object> paramMap){
        System.out.println("username : " + paramMap.get("username"));
        System.out.println("age : " + paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam Integer age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        System.out.println("username: " + helloData.getUsername());
        System.out.println("age: " + helloData.getAge());
        return "ok";
    }

    /*
     * @ModelAttribute 사용
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData){

        System.out.println("username: " + helloData.getUsername());
        System.out.println("age: " + helloData.getAge());
        System.out.println("helloData: " + helloData.toString());

        return "ok";
    }

    /*
     * @ModelAttribute, @RequestParam 생략 가능
     * - 혼란이 발생할 수 있다.
     * - String, int, Integer, ... 단순 타입이면 ->
     *      @RquestParam이 생략되었다고 판단
     * - DTO객체가 파라미터에 있으면 ->
     *      @ModelAttribute이 생략되었다고 판단
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(HelloData helloData){

        System.out.println("username: " + helloData.getUsername());
        System.out.println("age: " + helloData.getAge());
        System.out.println("helloData: " + helloData.toString());

        return "ok";
    }
}

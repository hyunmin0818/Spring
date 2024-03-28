package com.codingbox.core3.web.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello");
        return mav;
    }

    /*
    @ResponseBody가 없으면 response/hello로 뷰 리졸버가 실행되어 뷰를 찾고 렌더링한다
    그러나 @ResponseBody가 있으면 뷰 리졸버를 실행하지 않고, http 메시지 바디에 직접 response/hello라는 문자가 입력
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "modeldata");
        return "response/hello";
    }


}

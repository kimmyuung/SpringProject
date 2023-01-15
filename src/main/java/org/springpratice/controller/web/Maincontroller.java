package org.springpratice.controller.web;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springpratice.controller.web.dto.PostsResponseDto;
import org.springpratice.controller.web.dto.TestResponseDto;
import org.springpratice.service.posts.PostsService;

//@RestController
// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
// @ResponseBody를 한번에 사용해 줄 수 있게 된다고 보면 됨

@Controller
@RequiredArgsConstructor
public class Maincontroller {

    private final PostsService postService;

    // 테스트용
    @GetMapping("/test") // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/test/dto") // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    @ResponseBody
    public TestResponseDto testResponseDto(@RequestParam("name") String name,
                                           @RequestParam("amount") int amount
                                           ) {
        // RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        return new TestResponseDto(name, amount);
    }

    @GetMapping("/") // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    public String startApplication(Model model) {
        model.addAttribute("data", "start!!"); // 모델에 데이터를 넣음
        return "/index";
    }

    @GetMapping("/index2") // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    public String index2(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "/index2";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


    @GetMapping("/contextmodule")
    public String contextmodule() {
        return "";
    }

    @GetMapping("/excelmodule")
    public String excelmodule() {
        return "";
    }

    @GetMapping("/chatmodule")
    public String chatmodule() {
        return "";
    }
}

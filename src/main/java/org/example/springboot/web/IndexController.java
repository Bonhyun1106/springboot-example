package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.services.posts.PostsService;
import org.example.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping(value = "/")
    public String index(Model model){
        // Mustache pulgin으로 인해 pre/suffix가 자동 전환되어 ViewResolver가 처리한다.
        // -> src/main/resources/templates/index.mustache

        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    /**
     * 등록 화면으로 이동
     * @return
     */
    @GetMapping(value="/posts/save")
    public String postSave(){
        return "postsave";
    }

    /**
     * 수정 화면으로 이동
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value="/post/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto responseDto = postsService.findPostById(id);
        model.addAttribute("post", responseDto);

        return "postupdate";
    }


}

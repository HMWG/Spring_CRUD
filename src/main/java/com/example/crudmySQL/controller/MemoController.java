package com.example.crudmySQL.controller;

import com.example.crudmySQL.domain.Memo;
import com.example.crudmySQL.repository.MemoRepository;
import com.example.crudmySQL.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Memo> memos = memoService.findMemos();
        model.addAttribute("memos",memos);
        return "todo";
    }

    @PostMapping("/")
    public String create(MemoForm form){
        Memo memo = new Memo();
        memo.setMemoText(form.getName());

        memoService.add(memo);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String del(@PathVariable("id") Long id){

        memoService.delete(id);

        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String up(@PathVariable("id") Long id){

        memoService.update(id);

        return "redirect:/";
    }

}

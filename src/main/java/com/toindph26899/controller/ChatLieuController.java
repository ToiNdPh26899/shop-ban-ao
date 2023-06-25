package com.toindph26899.controller;

import com.toindph26899.request.ChatLieuRequest;
import com.toindph26899.response.ChatLieuResponse;
import com.toindph26899.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/chat-lieu")
public class ChatLieuController {

    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        Page<ChatLieuResponse> page = chatLieuService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).
                    boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", page);
        model.addAttribute("chatLieu", new ChatLieuRequest());
        model.addAttribute("page", pageNo);

        return "admin/chat-lieu/chat-lieu";
    }

    @GetMapping("/detail")
    public String detail(Model model,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo,
                         @RequestParam("chatLieuId") Long id) {

        Page<ChatLieuResponse> page = chatLieuService.findAll(PageRequest.of(pageNo - 1, 5));
        System.out.println("pageNO: " + pageNo);
        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).
                    boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", page);
        model.addAttribute("chatLieu", chatLieuService.getOne(id));
        model.addAttribute("page", pageNo);

        return "admin/chat-lieu/chat-lieu";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("chatLieu") ChatLieuRequest request) {

        chatLieuService.save(request);

        return "redirect:/admin/chat-lieu/hien-thi";
    }

}

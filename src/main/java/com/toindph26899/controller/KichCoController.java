package com.toindph26899.controller;

import com.toindph26899.request.KichCoRequest;
import com.toindph26899.response.KichCoResponse;
import com.toindph26899.service.KichCoService;
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
@RequestMapping("/admin/kich-co")
public class KichCoController {

    @Autowired
    private KichCoService kichCoService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        Page<KichCoResponse> findAll = kichCoService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("kichCo", new KichCoRequest());

        return "admin/kich-co/kich-co";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("kichCoId") Long id, Model model,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        Page<KichCoResponse> findAll = kichCoService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("kichCo", kichCoService.getOne(id));

        return "admin/kich-co/kich-co";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("kichCo") KichCoRequest request) {
        kichCoService.save(request);
        return "redirect:/admin/kich-co/hien-thi";
    }
}

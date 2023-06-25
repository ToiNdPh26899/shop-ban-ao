package com.toindph26899.controller;

import com.toindph26899.request.MauSacRequest;
import com.toindph26899.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/mau-sac")
public class MauSacController {

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String findAll(Model model) {

        model.addAttribute("data", mauSacService.findAll());
        model.addAttribute("mauSac", new MauSacRequest());

        return "/admin/mau-sac/mau-sac";
    }

    @GetMapping("/detail")
    public String detail(Model model,
                         @RequestParam("mauSacId") Long id) {
        model.addAttribute("mauSac", mauSacService.getOne(id));
        model.addAttribute("data", mauSacService.findAll());

        return "/admin/mau-sac/mau-sac";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("mauSac") MauSacRequest request) {
        mauSacService.addMauSac(request);

        return "redirect:/admin/mau-sac/hien-thi";
    }
}

package com.toindph26899.controller;

import com.toindph26899.request.DangKiTaiKhoan;
import com.toindph26899.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DangKiController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/form-dang-ki")
    private String formDangKi(Model model) {

        model.addAttribute("info", new DangKiTaiKhoan());

        return "pages/dang-ki";
    }

    @PostMapping("/dang-ki")
    public String dangKi(@ModelAttribute("info") DangKiTaiKhoan user) {

        taiKhoanService.saveUser(user);

        return "redirect:/login-form";
    }

}

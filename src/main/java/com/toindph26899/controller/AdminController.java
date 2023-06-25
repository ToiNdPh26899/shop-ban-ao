package com.toindph26899.controller;

import com.toindph26899.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/hien-thi")
    public String hienThi() {
        return "admin/index-admin";
    }

    @GetMapping("/dat-hang/hien-thi")
    public String lichSuDatHang(Model model) {

        model.addAttribute("data", donHangService.findAll());

        return "admin/don-hang/hien-thi";
    }

}

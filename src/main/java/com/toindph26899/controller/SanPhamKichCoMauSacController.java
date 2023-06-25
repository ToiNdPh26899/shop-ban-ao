package com.toindph26899.controller;

import com.toindph26899.request.SanPhamKichCoMauSacRequest;
import com.toindph26899.service.KichCoService;
import com.toindph26899.service.MauSacService;
import com.toindph26899.service.SanPhamKichCoMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/kich-co-san-pham")
public class SanPhamKichCoMauSacController {

    @Autowired
    private SanPhamKichCoMauSacService sanPhamKichCoMauSacService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @PostMapping("/save-kich-co")
    public String save(@ModelAttribute("sanPhamKichCo") SanPhamKichCoMauSacRequest request,
                       @RequestParam("idSanPham") Long id) {
//        sanPhamKichCoService.save(request);
        sanPhamKichCoMauSacService.save(request);

        return "redirect:/admin/kich-co-san-pham/hien-thi?sanPhamId=" + id;
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("sanPhamId") Long id) {
        SanPhamKichCoMauSacRequest sanPhamKichCoMauSacRequest = new SanPhamKichCoMauSacRequest();
        sanPhamKichCoMauSacRequest.setIdSanPham(id);
        model.addAttribute("sanPhamKichCo", sanPhamKichCoMauSacRequest);
        model.addAttribute("kichCos", kichCoService.findAll());
        model.addAttribute("mauSacs", mauSacService.findAll());
        model.addAttribute("idSanPham", id);

        return "admin/san-pham/san-pham-kich-co";
    }
}

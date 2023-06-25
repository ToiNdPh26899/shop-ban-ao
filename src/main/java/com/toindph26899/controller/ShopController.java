package com.toindph26899.controller;

import com.toindph26899.entity.LoaiAo;
import com.toindph26899.service.KichCoService;
import com.toindph26899.service.LoaiAoService;
import com.toindph26899.service.MauSacService;
import com.toindph26899.service.SanPhamChiTietService;
import com.toindph26899.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/t-shop/shops")
public class ShopController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private LoaiAoService loaiAoService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(value = "loaiAoId", defaultValue = "-1") Long id) {

        System.out.println(id);

        model.addAttribute("data", sanPhamChiTietService.newProducts());
        model.addAttribute("loaiAos", loaiAoService.findAll());
        model.addAttribute("thuongHieus", thuongHieuService.findAll());
        model.addAttribute("kichCos", kichCoService.findAll());
        model.addAttribute("mauSacs", mauSacService.findAll());

        return "/pages/shop";
    }
}

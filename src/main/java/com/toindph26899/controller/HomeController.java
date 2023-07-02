package com.toindph26899.controller;

import com.toindph26899.request.CartItemRequest;
import com.toindph26899.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/t-shop")
public class HomeController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/index")
    public String home(Model model) {

        model.addAttribute("data", sanPhamChiTietService.newProducts());

        model.addAttribute("account", taiKhoanService.checkLogin());

        return "index";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("sanPhamId") Long id, Model model) {

        model.addAttribute("sanPham", sanPhamChiTietService.getOne(id));
        model.addAttribute("item", new CartItemRequest());
        model.addAttribute("kichCos", sanPhamChiTietService.kichCoList(id));
        model.addAttribute("mauSacs", mauSacService.findMauSacByIdSanPham(id));
        model.addAttribute("account", taiKhoanService.checkLogin());

        return "/pages/product-detail";
    }

    @GetMapping("/tra-cuu-don-hang/result")
    public String traCuu(@RequestParam("soDienThoai") String soDienThoai,
                         Model model) {
        model.addAttribute("account", taiKhoanService.checkLogin());
        model.addAttribute("data", donHangService.traCuuDonHang(soDienThoai));

        return "/pages/tra-cuu-don-hang";
    }

    @GetMapping("/tra-cuu-don-hang")
    public String traCuu() {
        return "/pages/tra-cuu-don-hang";
    }

}

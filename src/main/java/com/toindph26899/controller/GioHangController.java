package com.toindph26899.controller;

import com.toindph26899.entity.TaiKhoan;
import com.toindph26899.request.DonHangRequest;
import com.toindph26899.response.GioHangItemResponse;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.service.GioHangService;
import com.toindph26899.service.SanPhamChiTietService;
import com.toindph26899.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/t-shop")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/cart")
    public String cart(Model model) {


        TaiKhoan taiKhoan = taiKhoanService.checkLogin();

        model.addAttribute("account", taiKhoan);

        if (taiKhoan == null) {
            List<SanPhamChiTietResponse> list = gioHangService.findAll();

            System.out.println("List1: " + list);

            if (list.isEmpty()) {
                model.addAttribute("display", false);
            } else {
                model.addAttribute("display", true);
                model.addAttribute("data", list);
                model.addAttribute("tongTien", gioHangService.tongTien());
                model.addAttribute("donHang", new DonHangRequest());
            }
        } else {
            List<SanPhamChiTietResponse> list = gioHangService.gioHangByUser(taiKhoan.getId());

            System.out.println("Listiiii: " + list);

            if (list.isEmpty()) {
                model.addAttribute("display", false);
            } else {
                model.addAttribute("display", true);
                model.addAttribute("data", list);
                model.addAttribute("tongTien", gioHangService.tongTien());
                model.addAttribute("donHang", new DonHangRequest());
            }
        }

        return "pages/gio-hang";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute("sanPham") SanPhamChiTietResponse response,
                            Model model) {

        SanPhamChiTietResponse sanPhamChiTietResponse = sanPhamChiTietService.getOne(response.getIdSanPham());
        sanPhamChiTietResponse.setSoLuong(response.getSoLuongMua());
        sanPhamChiTietResponse.setSoLuongMua(response.getSoLuongMua());
        sanPhamChiTietResponse.setKichCo(response.getKichCo());
        sanPhamChiTietResponse.setMauSac(response.getMauSac());
        sanPhamChiTietResponse.setIdSanPham(response.getIdSanPham());
        sanPhamChiTietResponse.setIdGioHangChiTiet(sanPhamChiTietService.idSanPhamChiTiet(response.getIdSanPham(), response.getMauSac(), response.getKichCo()));

        if (taiKhoanService.checkLogin() == null) {
            gioHangService.save(sanPhamChiTietResponse);
        } else {
            model.addAttribute("account", taiKhoanService.checkLogin());
            gioHangService.saveGioHangByUser(sanPhamChiTietResponse, taiKhoanService.checkLogin());
        }

        return "redirect:/t-shop/cart";
    }

    @GetMapping("/cart/remove")
    public String remove(@RequestParam(value = "idSanPham", defaultValue = "-1") Long idSanPham,
                         @RequestParam(value = "idKichCo", defaultValue = "-1") String idKichCo,
                         @RequestParam(value = "idMauSac", defaultValue = "-1") String idMauSac) {

        TaiKhoan taiKhoan = taiKhoanService.checkLogin();

        System.out.println("idSanPhamm: " + idSanPham);

        if (taiKhoan != null) {
            gioHangService.removeSanPhamToCartByUser(idSanPham);
        } else {
            gioHangService.removeSanPhamToCart(idSanPham);
        }

        return "redirect:/t-shop/cart";
    }

}

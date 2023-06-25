package com.toindph26899.controller;

import com.toindph26899.request.DonHangRequest;
import com.toindph26899.response.GioHangItemResponse;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.service.GioHangService;
import com.toindph26899.service.SanPhamChiTietService;
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

    @GetMapping("/cart")
    public String cart(Model model) {

        List<SanPhamChiTietResponse> list = gioHangService.findAll();

        if (list.isEmpty()) {
            model.addAttribute("display", false);
        } else {
            model.addAttribute("display", true);
            model.addAttribute("data", list);
            model.addAttribute("tongTien", gioHangService.tongTien());
            model.addAttribute("donHang", new DonHangRequest());
        }

        return "pages/gio-hang";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute("sanPham") SanPhamChiTietResponse response) {

        SanPhamChiTietResponse sanPhamChiTietResponse = sanPhamChiTietService.getOne(response.getIdSanPham());
        sanPhamChiTietResponse.setSoLuong(response.getSoLuongMua());
        sanPhamChiTietResponse.setKichCo(response.getKichCo());
        sanPhamChiTietResponse.setSoLuongMua(response.getSoLuongMua());
        sanPhamChiTietResponse.setMauSac(response.getMauSac());

        gioHangService.save(sanPhamChiTietResponse);

        return "redirect:/t-shop/cart";
    }

    @GetMapping("/cart/remove")
    public String remove(@RequestParam(value = "idSanPham", defaultValue = "-1") Long idSanPham,
                         @RequestParam(value = "idKichCo", defaultValue = "-1") String idKichCo,
                         @RequestParam(value = "idMauSac", defaultValue = "-1") String idMauSac) {

        gioHangService.removeSanPhamToCart(idSanPham, idKichCo, idMauSac);

        return "redirect:/t-shop/cart";
    }

}

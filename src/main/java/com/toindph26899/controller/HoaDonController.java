package com.toindph26899.controller;

import com.toindph26899.request.DonHangRequest;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.service.DonHangService;
import com.toindph26899.service.GioHangService;
import com.toindph26899.service.SanPhamChiTietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/t-shop/check-out")
public class HoaDonController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private DonHangService donHangService;

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("donHang") DonHangRequest request,
                       BindingResult bindingResult, Model model) {

        List<SanPhamChiTietResponse> list = sanPhamChiTietService.sanPhamCheckout(request.getIdSanPham());

        if (bindingResult.hasErrors()) {
            model.addAttribute("data", list);
            model.addAttribute("idSanPham", request.getIdSanPham());
            model.addAttribute("tongTien", sanPhamChiTietService.tongTien(list));

            return "pages/check-out";
        }

        donHangService.saveDonHang(request, list);

        if (request.getIdSanPham().size() > 1) {
            gioHangService.removeObjectByIdSanPham(request.getIdSanPham());
        } else {
            gioHangService.removeSanPhamToCart(
                    request.getIdSanPham().get(0),
                    request.getIdKichCo().get(0),
                    request.getIdMauSac().get(0));
        }


        return "redirect:/t-shop/cart";
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @ModelAttribute("donHang") DonHangRequest request,
                          @RequestParam("mauSac") List<String> mauSac,
                          @RequestParam("kichCo") List<String> kichCo) {

        List<SanPhamChiTietResponse> list = sanPhamChiTietService.sanPhamCheckout(request.getIdSanPham());

        if (list.isEmpty()) {
            model.addAttribute("data", gioHangService.findAll());
            model.addAttribute("display", true);
            model.addAttribute("tongTien", gioHangService.tongTien());
            model.addAttribute("errors", "Chọn 1 sản phẩm trước khi thanh toán");

            return "pages/gio-hang";
        }

        request.setIdKichCo(kichCo);
        request.setIdMauSac(mauSac);

        model.addAttribute("data", list);
        model.addAttribute("idSanPham", request.getIdSanPham());
        model.addAttribute("idKichCo", kichCo);
        model.addAttribute("idMauSac", mauSac);
        model.addAttribute("tongTien", sanPhamChiTietService.tongTien(list));

        return "/pages/check-out";
    }

}

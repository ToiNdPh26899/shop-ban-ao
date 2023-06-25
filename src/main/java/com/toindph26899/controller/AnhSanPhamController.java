package com.toindph26899.controller;

import com.toindph26899.request.AnhSanPhamRequest;
import com.toindph26899.service.AnhSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/anh-san-pham")
public class AnhSanPhamController {

    @Autowired
    private AnhSanPhamService anhSanPhamService;

    @PostMapping("/save-anh")
    public String save(@RequestParam("image") MultipartFile image,
                       @ModelAttribute("anhSanPham") AnhSanPhamRequest request,
                       @RequestParam("idSanPham") Long id) {

        anhSanPhamService.save(request, image);

        return "redirect:/admin/anh-san-pham/hien-thi?sanPhamId=" + id;
    }

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("sanPhamId") Long id) {
        AnhSanPhamRequest request = new AnhSanPhamRequest();
        request.setIdSanPham(id);
        model.addAttribute("idSanPham", id);
        model.addAttribute("anhSanPham", request);

        return "admin/san-pham/anh-san-pham";
    }
}

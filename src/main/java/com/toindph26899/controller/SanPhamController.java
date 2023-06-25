package com.toindph26899.controller;

import com.toindph26899.request.SanPhamRequest;
import com.toindph26899.response.SanPhamResponse;
import com.toindph26899.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    private SanPhamService sanPhamService;
    private ChatLieuService chatLieuService;
    private CoAoService coAoService;
    private KichCoService kichCoService;
    private LoaiAoService loaiAoService;
    private MauSacService mauSacService;
    private ThuongHieuService thuongHieuService;
    private TayAoService tayAoService;

    public SanPhamController(SanPhamService sanPhamService,
                             ChatLieuService chatLieuService,
                             CoAoService coAoService,
                             KichCoService kichCoService,
                             LoaiAoService loaiAoService,
                             MauSacService mauSacService,
                             ThuongHieuService thuongHieuService,
                             TayAoService tayAoService) {
        this.sanPhamService = sanPhamService;
        this.chatLieuService = chatLieuService;
        this.coAoService = coAoService;
        this.kichCoService = kichCoService;
        this.loaiAoService = loaiAoService;
        this.mauSacService = mauSacService;
        this.thuongHieuService = thuongHieuService;
        this.tayAoService = tayAoService;
    }


    @GetMapping("/hien-thi")
    public String findAll(Model model,
                          @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        Page<SanPhamResponse> findAll = sanPhamService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();
        List<Integer> pageNumbers = new ArrayList<>();
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }

        model.addAttribute("thuongHieus", thuongHieuService.findAll());
        model.addAttribute("chatLieus", chatLieuService.findAll());
        model.addAttribute("coAos", coAoService.findAll());
        model.addAttribute("kichCos", kichCoService.findAll());
        model.addAttribute("loaiAos", loaiAoService.findAll());
        model.addAttribute("mauSacs", mauSacService.findAll());
        model.addAttribute("tayAos", tayAoService.findAll());
        model.addAttribute("pageNumbers", pageNumbers);

        System.out.println(pageNumbers);

        model.addAttribute("data", findAll);
        model.addAttribute("sanPhamChiTiets", sanPhamService.sanPhamChiTiets());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("sanPham", new SanPhamRequest());

        return "admin/san-pham/san-pham";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("sanPhamId") Long id,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        Page<SanPhamResponse> findAll = sanPhamService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("thuongHieus", thuongHieuService.findAll());
        model.addAttribute("chatLieus", chatLieuService.findAll());
        model.addAttribute("coAos", coAoService.findAll());
        model.addAttribute("kichCos", kichCoService.findAll());
        model.addAttribute("loaiAos", loaiAoService.findAll());
        model.addAttribute("mauSacs", mauSacService.findAll());
        model.addAttribute("tayAos", tayAoService.findAll());

        model.addAttribute("data", findAll);
        model.addAttribute("sanPhamChiTiets", sanPhamService.sanPhamChiTiets());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("sanPham", sanPhamService.getOne(id));

        System.out.println("SanPham: " + sanPhamService.getOne(id));

        return "admin/san-pham/san-pham";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("sanPham") SanPhamRequest request) {

        sanPhamService.save(request);

        return "redirect:/admin/san-pham/hien-thi";
    }
}

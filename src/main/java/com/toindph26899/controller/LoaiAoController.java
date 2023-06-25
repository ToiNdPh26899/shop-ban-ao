package com.toindph26899.controller;

import com.toindph26899.request.LoaiAoRequest;
import com.toindph26899.response.LoaiAoResponse;
import com.toindph26899.service.LoaiAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/loai-ao")
public class LoaiAoController {

    @Autowired
    private LoaiAoService loaiAoService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        Page<LoaiAoResponse> findAll = loaiAoService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("loaiAo", new LoaiAoRequest());
        model.addAttribute("pageNo", pageNo);

        return "admin/loai-ao/loai-ao";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("loaiAoId") Long id,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        Page<LoaiAoResponse> findAll = loaiAoService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("loaiAo", loaiAoService.getOne(id));
        model.addAttribute("pageNo", pageNo);

        return "admin/loai-ao/loai-ao";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("loaiAo") LoaiAoRequest request) {
        loaiAoService.save(request);
        return "redirect:/admin/loai-ao/hien-thi";
    }

}

package com.toindph26899.controller;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import com.toindph26899.request.ThuongHieuRequest;
import com.toindph26899.response.ThuongHieuResponse;
import com.toindph26899.service.ThuongHieuService;
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
@RequestMapping("/admin/thuong-hieu")
public class ThuongHieuController {

    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        Page<ThuongHieuResponse> findAll = thuongHieuService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("thuongHieu", new ThuongHieuRequest());

        return "admin/thuong-hieu/thuong-hieu";
    }

    @GetMapping("/detail")
    public String detail(Model model,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo,
                         @RequestParam("thuongHieuId") Long id) {
        Page<ThuongHieuResponse> findAll = thuongHieuService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("thuongHieu", thuongHieuService.getOne(id));

        return "admin/thuong-hieu/thuong-hieu";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("thuongHieu") ThuongHieuRequest request) {

        thuongHieuService.save(request);

        return "redirect:/admin/thuong-hieu/hien-thi";
    }

}

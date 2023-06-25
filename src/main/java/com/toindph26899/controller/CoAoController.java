package com.toindph26899.controller;

import com.toindph26899.request.CoAoRequest;
import com.toindph26899.response.CoAoResponse;
import com.toindph26899.service.CoAoService;
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
@RequestMapping("/admin/co-ao")
public class CoAoController {

    @Autowired
    private CoAoService coAoService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        Page<CoAoResponse> findAll = coAoService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("coAo", new CoAoRequest());

        return "admin/co-ao/co-ao";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("coAoId") Long id,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        Page<CoAoResponse> findAll = coAoService.findAll(PageRequest.of(pageNo - 1, 5));

        int totalPages = findAll.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("data", findAll);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("coAo", coAoService.getOne(id));

        return "admin/co-ao/co-ao";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("coAo") CoAoRequest request) {

        coAoService.save(request);

        return "redirect:/admin/co-ao/hien-thi";
    }

}

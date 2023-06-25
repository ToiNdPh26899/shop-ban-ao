package com.toindph26899.service;

import com.toindph26899.request.AnhSanPhamRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnhSanPhamService {

    void save(AnhSanPhamRequest request, MultipartFile file);

    AnhSanPhamRequest getOne(Long id);

}

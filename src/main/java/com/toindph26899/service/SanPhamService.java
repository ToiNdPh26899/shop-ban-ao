package com.toindph26899.service;

import com.toindph26899.entity.SanPham;
import com.toindph26899.request.SanPhamRequest;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamService {

    Page<SanPhamResponse> findAll(Pageable pageable);

    List<SanPhamChiTietResponse> sanPhamChiTiets();

    SanPham save(SanPhamRequest request);

    SanPhamRequest getOne(Long id);
}

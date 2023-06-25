package com.toindph26899.service;

import com.toindph26899.request.ThuongHieuRequest;
import com.toindph26899.response.ThuongHieuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThuongHieuService {

    Page<ThuongHieuResponse> findAll(Pageable pageable);

    List<ThuongHieuResponse> findAll();

    void save(ThuongHieuRequest request);

    ThuongHieuResponse getOne(Long id);

}

package com.toindph26899.service;

import com.toindph26899.request.LoaiAoRequest;
import com.toindph26899.response.LoaiAoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoaiAoService {

    Page<LoaiAoResponse> findAll(Pageable pageable);

    List<LoaiAoResponse> findAll();

    void save(LoaiAoRequest request);

    LoaiAoResponse getOne(Long id);

}

package com.toindph26899.service;

import com.toindph26899.request.TayAoRequest;
import com.toindph26899.response.TayAoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TayAoService {

    Page<TayAoResponse> findAll(Pageable pageable);

    List<TayAoResponse> findAll();

    void save(TayAoRequest request);

    TayAoResponse getOne(Long id);

}

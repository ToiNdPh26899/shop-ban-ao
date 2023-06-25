package com.toindph26899.service;

import com.toindph26899.request.CoAoRequest;
import com.toindph26899.response.CoAoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CoAoService {

    Page<CoAoResponse> findAll(Pageable pageable);

    List<CoAoResponse> findAll();

    void save(CoAoRequest request);

    CoAoResponse getOne(Long id);

}

package com.toindph26899.service;

import com.toindph26899.entity.KichCo;
import com.toindph26899.request.KichCoRequest;
import com.toindph26899.response.KichCoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KichCoService {

    Page<KichCoResponse> findAll(Pageable pageable);

    List<KichCoResponse> findAll();

    void save(KichCoRequest request);

    KichCoResponse getOne(Long id);

}

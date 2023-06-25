package com.toindph26899.service;

import com.toindph26899.request.MauSacRequest;
import com.toindph26899.response.MauSacResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MauSacService {

    List<MauSacResponse> findAll();

    List<MauSacResponse> findMauSacByIdSanPham(Long id);

    void addMauSac(MauSacRequest repquest);

    MauSacResponse getOne(Long id);

}

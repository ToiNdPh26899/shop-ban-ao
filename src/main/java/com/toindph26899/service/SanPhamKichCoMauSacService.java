package com.toindph26899.service;

import com.toindph26899.request.SanPhamKichCoMauSacRequest;

public interface SanPhamKichCoMauSacService {

    void save(SanPhamKichCoMauSacRequest request);

    Long tongSoLuong(Long idKichCo, Long idMauSac);

}

package com.toindph26899.service;

import com.toindph26899.response.GioHangItemResponse;
import com.toindph26899.response.SanPhamChiTietResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;

public interface GioHangService {

    List<SanPhamChiTietResponse> findAll();

    List<SanPhamChiTietResponse> removeObjectByIdSanPham(List<Long> list);

    List<SanPhamChiTietResponse> removeSanPhamToCart(Long idSanPham, String idKichCo, String idMauSac);

    void save(SanPhamChiTietResponse sanPhamChiTietResponse);

    Double tongTien();

    String checkListCheckout(List<SanPhamChiTietResponse> list, Model model);

}

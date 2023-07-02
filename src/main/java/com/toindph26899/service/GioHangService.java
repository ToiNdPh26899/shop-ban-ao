package com.toindph26899.service;

import com.toindph26899.entity.TaiKhoan;
import com.toindph26899.response.GioHangItemResponse;
import com.toindph26899.response.SanPhamChiTietResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface GioHangService {

    List<SanPhamChiTietResponse> findAll();

    List<SanPhamChiTietResponse> findAllByIdKhachHang(String id);

    List<SanPhamChiTietResponse> removeObjectByIdSanPham(List<Long> list);

    List<SanPhamChiTietResponse> removeSanPhamToCart(Long idSanPham);

    List<SanPhamChiTietResponse> removeSanPhamToCartByUserDone(List<Long> sanPham);

    void removeSanPhamToCartByUser(Long idSanPham);

    void save(SanPhamChiTietResponse sanPhamChiTietResponse);

    void saveGioHangByUser(SanPhamChiTietResponse sanPhamChiTietResponse, TaiKhoan taiKhoan);

    List<SanPhamChiTietResponse> gioHangByUser(UUID id);

    Double tongTien();

    String checkListCheckout(List<SanPhamChiTietResponse> list, Model model);

}

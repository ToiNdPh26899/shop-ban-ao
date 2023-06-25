package com.toindph26899.service;

import com.toindph26899.request.DonHangRequest;
import com.toindph26899.response.*;

import java.util.List;

public interface DonHangService {

    List<TraCuuDonHang> traCuuDonHang(String soDienThoai);

    List<DonHangXacNhanResponse> xacNhanDon();

    void saveDonHang(DonHangRequest request, List<SanPhamChiTietResponse> list);

    List<LichSuDonHang> findAll();

}

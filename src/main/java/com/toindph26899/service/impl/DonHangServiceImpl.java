package com.toindph26899.service.impl;

import com.toindph26899.entity.DonHang;
import com.toindph26899.entity.DonHangChiTiet;
import com.toindph26899.entity.SanPham;
import com.toindph26899.entity.SanPhamKichCoMauSac;
import com.toindph26899.repository.DonHangChiTietRepository;
import com.toindph26899.repository.DonHangRepository;
import com.toindph26899.repository.SanPhamKichCoMauSacRepository;
import com.toindph26899.repository.SanPhamRepository;
import com.toindph26899.request.DonHangChiTietRequest;
import com.toindph26899.request.DonHangRequest;
import com.toindph26899.response.*;
import com.toindph26899.service.DonHangService;
import com.toindph26899.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private DonHangChiTietRepository donHangChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private SanPhamKichCoMauSacRepository sanPhamKichCoMauSacRepository;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    private DonHang donHang(DonHangRequest request, Double tongTien) {

        long random = (long) (Math.random() * 999999999);
        long random1 = (long) (Math.random() * 999999999);

        return DonHang.builder()
                .id(request.getIdDonHang())
                .maHoaDon("HD_" + random)
                .maKhachHang("CLIENT_" + random1)
                .diaChiNhanHang(request.getDiaChiNguoiNhan())
                .tenNguoiNhan(request.getTenNguoiNhan())
                .soDienThoaiNguoiNhan(request.getSoDienThoaiNguoiNhan())
                .tongTien(BigDecimal.valueOf(tongTien))
                .ngayTao(Date.valueOf(LocalDate.now()))
                .ngayThanhToan(Date.valueOf(LocalDate.now()))
                .hinhThucThanhToan(request.getHinhThucThanhToan())
                .ghiChu(request.getGhiChu())
                .email(request.getEmail())
                .build();
    }

    private DonHangChiTiet donHangChiTiet(DonHang donHang, DonHangChiTietRequest request) {

        Optional<SanPham> sanPhamOptional = sanPhamRepository.findById(request.getIdSanPham());

        SanPham sanPham = null;

        if (sanPhamOptional.isPresent()) {
            sanPham = sanPhamOptional.get();
        } else {
            return null;
        }

        return DonHangChiTiet.builder()
                .id(request.getId())
                .soLuong(request.getSoLuong())
                .idDonHang(donHang)
                .idSanPham(sanPham)
                .donGia(request.getDonGia())
                .trangThai(1)
                .ghiChu(request.getGhiChu())
                .build();
    }

    @Override
    public List<TraCuuDonHang> traCuuDonHang(String soDienThoai) {
        return donHangChiTietRepository.traCuuDonHangs(soDienThoai);
    }

    @Override
    public List<DonHangXacNhanResponse> xacNhanDon() {
        return null;
    }

    @Override
    public void saveDonHang(DonHangRequest request, List<SanPhamChiTietResponse> list) {

        Integer soLuong = -1;

        SanPhamKichCoMauSac sanPhamKichCoMauSac = null;

        DonHang donHang =
                donHangRepository.save(donHang(request, sanPhamChiTietService.tongTien(list)));

        for (SanPhamChiTietResponse spct : list) {
            DonHangChiTietRequest donHangChiTietRequest = new DonHangChiTietRequest();
            donHangChiTietRequest.setIdSanPham(spct.getIdSanPham());
            donHangChiTietRequest.setSoLuong(spct.getSoLuong());
            donHangChiTietRequest.setDonGia(spct.getGiaGiaoBan());

            donHangChiTietRepository.save(Objects.requireNonNull(donHangChiTiet(donHang,
                    donHangChiTietRequest)));
        }


    }

    @Override
    public List<LichSuDonHang> findAll() {
        return donHangRepository.findAllLichSuDonHang();
    }
}

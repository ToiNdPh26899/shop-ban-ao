package com.toindph26899.service.impl;

import com.toindph26899.entity.AnhSanPham;
import com.toindph26899.entity.SanPham;
import com.toindph26899.entity.SanPhamKichCoMauSac;
import com.toindph26899.repository.AnhSanPhamRepository;
import com.toindph26899.repository.KichCoRepository;
import com.toindph26899.repository.SanPhamKichCoMauSacRepository;
import com.toindph26899.repository.SanPhamRepository;
import com.toindph26899.response.KichCoResponse;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.response.SanPhamView;
import com.toindph26899.service.GioHangService;
import com.toindph26899.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    private SanPhamKichCoMauSacRepository sanPhamKichCoMauSacRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private AnhSanPhamRepository anhSanPhamRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private GioHangService gioHangService;

    private Page<SanPhamChiTietResponse> page(Pageable pageable, List<SanPhamChiTietResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<SanPhamChiTietResponse> pageList;

        if (list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    @Override
    public Page<SanPhamChiTietResponse> findAll(Pageable pageable) {
        return page(pageable, sanPhamRepository.sanPhamChiTiets());
    }

    @Override
    public List<SanPhamView> newProducts() {
        return sanPhamRepository.listHienThi();
    }

    @Override
    public List<KichCoResponse> kichCoList(Long id) {
        return kichCoRepository.findAllKichCo(id);
    }

    @Override
    public SanPhamChiTietResponse getOne(Long id) {

        Optional<SanPhamKichCoMauSac> sanPhamKichCoMauSacOptional = sanPhamKichCoMauSacRepository.findById(id);
        AnhSanPham anhSanPham = anhSanPhamRepository.anhSanPham(id);

        SanPhamKichCoMauSac sanPhamKichCoMauSac = null;

        if (sanPhamKichCoMauSacOptional.isPresent()) {
            sanPhamKichCoMauSac = sanPhamKichCoMauSacOptional.get();
        } else {
            return null;
        }

        return SanPhamChiTietResponse.builder()
                .idGioHangChiTiet(sanPhamKichCoMauSac.getId())
                .idSanPham(sanPhamKichCoMauSac.getId())
                .tenSanPham(sanPhamKichCoMauSac.getIdSanPham().getTenSanPham())
                .giaGiaoBan(sanPhamKichCoMauSac.getIdSanPham().getGiaGiaoBan())
                .duongDanAnh(anhSanPham.getDuongDanAnh())
                .moTa(sanPhamKichCoMauSac.getIdSanPham().getMoTa())
                .tenChatLieu(sanPhamKichCoMauSac.getIdSanPham().getIdChatLieu().getTenChatLieu())
                .loaiAo(sanPhamKichCoMauSac.getIdSanPham().getIdLoaiAo().getTenLoaiAo())
                .loaiCoAo(sanPhamKichCoMauSac.getIdSanPham().getIdLoaiAo().getTenLoaiAo())
                .loaiTayAo(sanPhamKichCoMauSac.getIdSanPham().getIdLoaiAo().getTenLoaiAo())
                .tenThuongHieu(sanPhamKichCoMauSac.getIdSanPham().getIdThuongHieu().getTenThuongHieu())
                .soLuong(sanPhamKichCoMauSac.getIdSanPham().getSoLuongTon())
                .build();
    }

    @Override
    public SanPhamKichCoMauSac getOne(Long idSanPham, String mauSac, String kichCo) {
        return
                null;
    }

    @Override
    public List<SanPhamChiTietResponse> sanPhamCheckout(List<Long> id) {

        List<SanPhamChiTietResponse> list = new ArrayList<>();

        for (SanPhamChiTietResponse spct : gioHangService.findAll()) {
            for (Long idGioHangChiTiet : id) {
                if (spct.getIdGioHangChiTiet().equals(idGioHangChiTiet)) {
                    list.add(spct);
                }
            }
        }

        return list;
    }

    @Override
    public Double tongTien(List<SanPhamChiTietResponse> list) {

        Double tongTien = 0d;

        for (SanPhamChiTietResponse spct : list) {
            tongTien += spct.getSoLuong() * Double.valueOf(String.valueOf(spct.getGiaGiaoBan()));
        }

        return tongTien;
    }

    @Override
    public Long idSanPhamChiTiet(Long idSanPham, String mauSac, String kichCo) {
        return sanPhamKichCoMauSacRepository.idSanPhamChiTiet(idSanPham, mauSac, kichCo);
    }
}

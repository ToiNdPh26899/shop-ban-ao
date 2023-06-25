package com.toindph26899.service.impl;

import com.toindph26899.entity.AnhSanPham;
import com.toindph26899.entity.SanPham;
import com.toindph26899.repository.AnhSanPhamRepository;
import com.toindph26899.repository.KichCoRepository;
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

        Optional<SanPham> sanPhamOptional = sanPhamRepository.findById(id);
        AnhSanPham anhSanPham = anhSanPhamRepository.anhSanPham(id);

        SanPham sanPham = null;

        if (sanPhamOptional.isPresent()) {
            sanPham = sanPhamOptional.get();
        } else {
            return null;
        }

        return SanPhamChiTietResponse.builder()
                .idSanPham(sanPham.getId())
                .tenSanPham(sanPham.getTenSanPham())
                .giaGiaoBan(sanPham.getGiaGiaoBan())
                .duongDanAnh(anhSanPham.getDuongDanAnh())
                .moTa(sanPham.getMoTa())
                .tenChatLieu(sanPham.getIdChatLieu().getTenChatLieu())
                .loaiAo(sanPham.getIdLoaiAo().getTenLoaiAo())
                .loaiCoAo(sanPham.getIdLoaiAo().getTenLoaiAo())
                .loaiTayAo(sanPham.getIdLoaiAo().getTenLoaiAo())
                .tenThuongHieu(sanPham.getIdThuongHieu().getTenThuongHieu())
                .soLuong(sanPham.getSoLuongTon())
                .build();
    }

    @Override
    public List<SanPhamChiTietResponse> sanPhamCheckout(List<Long> id) {
        List<SanPhamChiTietResponse> list = new ArrayList<>();
        for (SanPhamChiTietResponse item : gioHangService.findAll()) {
            for (Long l : id) {
                if (Objects.equals(item.getIdSanPham(), l)) {
                    list.add(item);
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
}

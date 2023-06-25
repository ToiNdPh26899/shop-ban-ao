package com.toindph26899.service.impl;

import com.toindph26899.entity.*;
import com.toindph26899.repository.*;
import com.toindph26899.request.SanPhamRequest;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.response.SanPhamResponse;
import com.toindph26899.service.SanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository sanPhamRepository;

    private ChatLieuRepository chatLieuRepository;

    private TayAoRepository tayAoRepository;

    private LoaiAoRepository loaiAoRepository;

    private CoAoRepository coAoRepository;

    private ThuongHieuRepository thuongHieuRepository;

    public SanPhamServiceImpl(SanPhamRepository sanPhamRepository,
                              ChatLieuRepository chatLieuRepository,
                              TayAoRepository tayAoRepository,
                              LoaiAoRepository loaiAoRepository,
                              CoAoRepository coAoRepository,
                              ThuongHieuRepository thuongHieuRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.chatLieuRepository = chatLieuRepository;
        this.tayAoRepository = tayAoRepository;
        this.loaiAoRepository = loaiAoRepository;
        this.coAoRepository = coAoRepository;
        this.thuongHieuRepository = thuongHieuRepository;
    }


    private Page<SanPhamResponse> page(Pageable pageable, List<SanPhamResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<SanPhamResponse> pageList;

        if (list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private SanPham sanPham(SanPhamRequest request) {

        Optional<ChatLieu> chatLieuOptional = chatLieuRepository.findById(request.getIdChatLieu());
        Optional<LoaiAo> loaiAoOptional = loaiAoRepository.findById(request.getIdLoaiAo());
        Optional<TayAo> tayAoOptional = tayAoRepository.findById(request.getIdTayAo());
        Optional<CoAo> coAoOptional = coAoRepository.findById(request.getIdCoAo());
        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepository.findById(request.getIdThuongHieu());

        ChatLieu chatLieu = null;
        LoaiAo loaiAo = null;
        TayAo tayAo = null;
        CoAo coAo = null;
        ThuongHieu thuongHieu = null;

        if (chatLieuOptional.isPresent() && loaiAoOptional.isPresent() &&
                tayAoOptional.isPresent() && coAoOptional.isPresent() && thuongHieuOptional.isPresent()) {
            chatLieu = chatLieuOptional.get();
            loaiAo = loaiAoOptional.get();
            tayAo = tayAoOptional.get();
            coAo = coAoOptional.get();
            thuongHieu = thuongHieuOptional.get();
        } else {
            return null;
        }

        return SanPham.builder()
                .id(request.getIdSanPham())
                .maSanPham(request.getMaSanPham())
                .tenSanPham(request.getTenSanPham())
                .giaGiaoBan(request.getGiaGiaoBan())
                .moTa(request.getMoTa())
                .trangThai(1)
                .idChatLieu(chatLieu)
                .idLoaiAo(loaiAo)
                .idTayAo(tayAo)
                .idCoAo(coAo)
                .idThuongHieu(thuongHieu)
                .soLuongTon(request.getSoLuongTon())
                .build();
    }

    private SanPhamRequest sanPhamRequest(Long id) {

        Optional<SanPham> sanPhamOptional = sanPhamRepository.findById(id);

        SanPham sanPham = null;

        if (sanPhamOptional.isPresent()) {
            sanPham = sanPhamOptional.get();
        } else {
            return null;
        }

        return SanPhamRequest.builder()
                .idSanPham(sanPham.getId())
                .maSanPham(sanPham.getMaSanPham())
                .tenSanPham(sanPham.getTenSanPham())
                .giaGiaoBan(sanPham.getGiaGiaoBan())
                .soLuongTon(sanPham.getSoLuongTon())
                .moTa(sanPham.getMoTa())
                .trangThai(sanPham.getTrangThai())
                .idChatLieu(sanPham.getIdChatLieu().getId())
                .idCoAo(sanPham.getIdCoAo().getId())
                .idThuongHieu(sanPham.getIdThuongHieu().getId())
                .idTayAo(sanPham.getIdTayAo().getId())
                .soLuongTon(sanPham.getSoLuongTon())
                .build();
    }

    @Override
    public Page<SanPhamResponse> findAll(Pageable pageable) {
        return page(pageable, sanPhamRepository.findAllSanPhamCustom());
    }

    @Override
    public List<SanPhamChiTietResponse> sanPhamChiTiets() {
        return sanPhamRepository.sanPhamChiTiets();
    }

    @Override
    public SanPham save(SanPhamRequest request) {

        SanPham sanPhamDB = sanPhamRepository.save(sanPham(request));

        return sanPhamDB;
    }

    @Override
    public SanPhamRequest getOne(Long id) {
        return sanPhamRequest(id);
    }
}

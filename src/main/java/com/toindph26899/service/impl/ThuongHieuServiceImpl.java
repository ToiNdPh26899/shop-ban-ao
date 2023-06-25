package com.toindph26899.service.impl;

import com.toindph26899.entity.ThuongHieu;
import com.toindph26899.repository.ThuongHieuRepository;
import com.toindph26899.request.ThuongHieuRequest;
import com.toindph26899.response.ThuongHieuResponse;
import com.toindph26899.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    private Page<ThuongHieuResponse> page(Pageable pageable, List<ThuongHieuResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<ThuongHieuResponse> pageList;

        if(list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private ThuongHieu thuongHieu(ThuongHieuRequest request) {
        return ThuongHieu.builder()
                .id(request.getIdThuongHieu())
                .tenThuongHieu(request.getTenThuongHieu())
                .trangThai(request.getTrangThai())
                .build();
    }

    private ThuongHieuResponse thuongHieuResponse(Long id) {

        Optional<ThuongHieu> thuongHieuOptional = thuongHieuRepository.findById(id);

        ThuongHieu thuongHieu = null;

        if(thuongHieuOptional.isPresent()) {
            thuongHieu = thuongHieuOptional.get();
        } else {
            return null;
        }

        return ThuongHieuResponse.builder()
                .idThuongHieu(thuongHieu.getId())
                .tenThuongHieu(thuongHieu.getTenThuongHieu())
                .trangThai(thuongHieu.getTrangThai())
                .build();
    }

    @Override
    public Page<ThuongHieuResponse> findAll(Pageable pageable) {
        return page(pageable, thuongHieuRepository.findAllCustom());
    }

    @Override
    public List<ThuongHieuResponse> findAll() {
        return thuongHieuRepository.findAllCustom();
    }

    @Override
    public void save(ThuongHieuRequest request) {
        thuongHieuRepository.save(thuongHieu(request));
    }

    @Override
    public ThuongHieuResponse getOne(Long id) {
        return thuongHieuResponse(id);
    }
}

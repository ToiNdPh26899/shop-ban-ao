package com.toindph26899.service.impl;

import com.toindph26899.entity.LoaiAo;
import com.toindph26899.repository.LoaiAoRepository;
import com.toindph26899.request.LoaiAoRequest;
import com.toindph26899.response.LoaiAoResponse;
import com.toindph26899.service.LoaiAoService;
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
public class LoaiAoServiceImpl implements LoaiAoService {

    @Autowired
    private LoaiAoRepository loaiAoRepository;

    private Page<LoaiAoResponse> page(Pageable pageable, List<LoaiAoResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<LoaiAoResponse> pageList;

        if (list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private LoaiAo loaiAo(LoaiAoRequest request) {
        return LoaiAo.builder()
                .id(request.getIdLoaiAo())
                .tenLoaiAo(request.getTenLoaiAo())
                .trangThai(request.getTrangThai())
                .build();
    }

    private LoaiAoResponse loaiAoResponse(Long id) {

        Optional<LoaiAo> loaiAoOptional = loaiAoRepository.findById(id);

        LoaiAo loaiAo = null;

        if (loaiAoOptional.isPresent()) {
            loaiAo = loaiAoOptional.get();
        } else {
            return null;
        }

        return LoaiAoResponse.builder()
                .idLoaiAo(loaiAo.getId())
                .tenLoaiAo(loaiAo.getTenLoaiAo())
                .trangThai(loaiAo.getTrangThai())
                .build();
    }

    @Override
    public Page<LoaiAoResponse> findAll(Pageable pageable) {
        return page(pageable, loaiAoRepository.findAllCustom());
    }

    @Override
    public List<LoaiAoResponse> findAll() {
        return loaiAoRepository.findAllCustom();
    }

    @Override
    public void save(LoaiAoRequest request) {
        loaiAoRepository.save(loaiAo(request));
    }

    @Override
    public LoaiAoResponse getOne(Long id) {
        return loaiAoResponse(id);
    }
}

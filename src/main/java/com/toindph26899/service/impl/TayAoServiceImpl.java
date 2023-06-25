package com.toindph26899.service.impl;

import com.toindph26899.entity.ChatLieu;
import com.toindph26899.entity.TayAo;
import com.toindph26899.repository.TayAoRepository;
import com.toindph26899.request.ChatLieuRequest;
import com.toindph26899.request.TayAoRequest;
import com.toindph26899.response.ChatLieuResponse;
import com.toindph26899.response.TayAoResponse;
import com.toindph26899.service.TayAoService;
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
public class TayAoServiceImpl implements TayAoService {

    @Autowired
    private TayAoRepository tayAoRepository;

    private Page<TayAoResponse> page(Pageable pageable, List<TayAoResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<TayAoResponse> pageList;

        if (list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private TayAo tayAo(TayAoRequest request) {
        return TayAo.builder()
                .id(request.getIdTayAo())
                .loaiTayAo(request.getLoaiTayAo())
                .trangThai(request.getTrangThai())
                .build();
    }

    private TayAoResponse tayAoResponse(Long id) {

        Optional<TayAo> tayAoOptional = tayAoRepository.findById(id);

        TayAo tayAo = null;

        if (tayAoOptional.isPresent()) {
            tayAo = tayAoOptional.get();
        } else {
            return null;
        }

        return TayAoResponse.builder()
                .idTayAo(tayAo.getId())
                .loaiTayAo(tayAo.getLoaiTayAo())
                .trangThai(tayAo.getTrangThai())
                .build();
    }

    @Override
    public Page<TayAoResponse> findAll(Pageable pageable) {
        return page(pageable, tayAoRepository.findAllCustom());
    }

    @Override
    public List<TayAoResponse> findAll() {
        return tayAoRepository.findAllCustom();
    }

    @Override
    public void save(TayAoRequest request) {
        tayAoRepository.save(tayAo(request));
    }

    @Override
    public TayAoResponse getOne(Long id) {
        return tayAoResponse(id);
    }
}

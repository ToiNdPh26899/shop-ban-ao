package com.toindph26899.service.impl;

import com.toindph26899.entity.ChatLieu;
import com.toindph26899.entity.CoAo;
import com.toindph26899.repository.CoAoRepository;
import com.toindph26899.request.ChatLieuRequest;
import com.toindph26899.request.CoAoRequest;
import com.toindph26899.response.ChatLieuResponse;
import com.toindph26899.response.CoAoResponse;
import com.toindph26899.service.CoAoService;
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
public class CoAoServiceImpl implements CoAoService {

    @Autowired
    private CoAoRepository coAoRepository;

    private Page<CoAoResponse> page(Pageable pageable, List<CoAoResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<CoAoResponse> pageList;

        if (list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private CoAo coAo(CoAoRequest request) {
        return CoAo.builder()
                .id(request.getIdCoAo())
                .tenCoAo(request.getTenCoAo())
                .trangThai(request.getTrangThai())
                .build();
    }

    private CoAoResponse coAoResponse(Long id) {

        Optional<CoAo> coAoOptional = coAoRepository.findById(id);

        CoAo coAo = null;

        if (coAoOptional.isPresent()) {
            coAo = coAoOptional.get();
        } else {
            return null;
        }

        return CoAoResponse.builder()
                .idCoAo(coAo.getId())
                .tenCoAo(coAo.getTenCoAo())
                .trangThai(coAo.getTrangThai())
                .build();
    }

    @Override
    public Page<CoAoResponse> findAll(Pageable pageable) {
        return page(pageable, coAoRepository.findAllCustom());
    }

    @Override
    public List<CoAoResponse> findAll() {
        return coAoRepository.findAllCustom();
    }

    @Override
    public void save(CoAoRequest request) {
        coAoRepository.save(coAo(request));
    }

    @Override
    public CoAoResponse getOne(Long id) {
        return coAoResponse(id);
    }
}

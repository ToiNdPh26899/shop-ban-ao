package com.toindph26899.service.impl;

import com.toindph26899.entity.KichCo;
import com.toindph26899.repository.KichCoRepository;
import com.toindph26899.request.KichCoRequest;
import com.toindph26899.response.KichCoResponse;
import com.toindph26899.service.KichCoService;
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
public class KichCoServiceImpl implements KichCoService {

    @Autowired
    private KichCoRepository kichCoRepository;

    private Page<KichCoResponse> page(Pageable pageable, List<KichCoResponse> list) {

        int pageNo = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startItem = pageNo * pageSize;

        List<KichCoResponse> pageList;

        if(list.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            pageList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageList, PageRequest.of(pageNo, pageSize), list.size());
    }

    private KichCo kichCo(KichCoRequest request) {
        return KichCo.builder()
                .id(request.getIdKichCo())
                .loaiKichCo(request.getKichCo())
                .trangThai(request.getTrangThai())
                .build();
    }

    private KichCoResponse kichCoResponse(Long id) {
        Optional<KichCo> kichCoOptional = kichCoRepository.findById(id);

        KichCo kichCo = null;

        if(kichCoOptional.isPresent()) {
            kichCo = kichCoOptional.get();
        } else {
            return null;
        }

        return KichCoResponse.builder()
                .idKichCo(kichCo.getId())
                .kichCo(kichCo.getLoaiKichCo())
                .trangThai(kichCo.getTrangThai())
                .build();
    }

    @Override
    public Page<KichCoResponse> findAll(Pageable pageable) {
        return page(pageable, kichCoRepository.findAllCustom());
    }

    @Override
    public List<KichCoResponse> findAll() {
        return kichCoRepository.findAllCustom();
    }

    @Override
    public void save(KichCoRequest request) {
        kichCoRepository.save(kichCo(request));
    }

    @Override
    public KichCoResponse getOne(Long id) {
        return kichCoResponse(id);
    }
}

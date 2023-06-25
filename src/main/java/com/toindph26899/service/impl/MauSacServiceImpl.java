package com.toindph26899.service.impl;

import com.toindph26899.entity.MauSac;
import com.toindph26899.repository.MauSacRepository;
import com.toindph26899.request.MauSacRequest;
import com.toindph26899.response.MauSacResponse;
import com.toindph26899.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSacResponse> findAll() {
        return mauSacRepository.findAllCustom();
    }

    @Override
    public List<MauSacResponse> findMauSacByIdSanPham(Long id) {
        return mauSacRepository.findAllMauSacByIdSanPham(id);
    }

    @Override
    public void addMauSac(MauSacRequest repquest) {
        MauSac mauSac = MauSac.builder()
                .id(repquest.getIdMauSac())
                .tenMauSac(repquest.getTenMauSac())
                .trangThai(repquest.getTrangThai())
                .build();

        mauSacRepository.save(mauSac);
    }

    @Override
    public MauSacResponse getOne(Long id) {
        Optional<MauSac> mauSacOptional = mauSacRepository.findById(id);
        MauSac mauSac = null;

        if (mauSacOptional.isPresent()) {
            mauSac = mauSacOptional.get();
        } else {
            return null;
        }

        return MauSacResponse.builder()
                .idMauSac(mauSac.getId())
                .tenMauSac(mauSac.getTenMauSac())
                .trangThai(mauSac.getTrangThai())
                .build();
    }
}

package com.toindph26899.service.impl;

import com.toindph26899.entity.KichCo;
import com.toindph26899.entity.MauSac;
import com.toindph26899.entity.SanPham;
import com.toindph26899.entity.SanPhamKichCoMauSac;
import com.toindph26899.repository.KichCoRepository;
import com.toindph26899.repository.MauSacRepository;
import com.toindph26899.repository.SanPhamKichCoMauSacRepository;
import com.toindph26899.repository.SanPhamRepository;
import com.toindph26899.request.SanPhamKichCoMauSacRequest;
import com.toindph26899.service.SanPhamKichCoMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamKichCoMauSacServiceImpl implements SanPhamKichCoMauSacService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private SanPhamKichCoMauSacRepository sanPhamKichCoMauSacRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public void save(SanPhamKichCoMauSacRequest request) {

        Optional<SanPham> sanPhamOptional = sanPhamRepository.findById(request.getIdSanPham());

        SanPham sanPham = null;

        Integer soLuongSanPhamUpdate = 0;

        if (sanPhamOptional.isPresent()) {
            sanPham = sanPhamOptional.get();
        } else {
            return;
        }

        List<MauSac> mauSacs = new ArrayList<>();
        List<KichCo> kichCos = new ArrayList<>();

        for (Long idMauSac : request.getIdMauSac()) {
            MauSac mauSac = mauSacRepository.findMauSacById(idMauSac);
            mauSacs.add(mauSac);
        }

        for (Long idKichCo : request.getIdKichCo()) {
            KichCo kichCo = kichCoRepository.findKichCoById(idKichCo);
            kichCos.add(kichCo);
        }

        for (MauSac mauSac : mauSacs) {
            for (KichCo kichCo : kichCos) {
                SanPhamKichCoMauSac sanPhamKichCoMauSac = new SanPhamKichCoMauSac(null,
                        sanPham, kichCo, mauSac, request.getSoLuong(), 1);

                soLuongSanPhamUpdate += request.getSoLuong();

                sanPhamKichCoMauSacRepository.save(sanPhamKichCoMauSac);
            }
        }

        sanPham.setSoLuongTon(soLuongSanPhamUpdate);
        sanPhamRepository.save(sanPham);
    }

    @Override
    public Long tongSoLuong(Long idKichCo, Long idMauSac) {
        return null;
    }

}

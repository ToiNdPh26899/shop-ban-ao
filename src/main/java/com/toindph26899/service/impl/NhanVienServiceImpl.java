package com.toindph26899.service.impl;

import com.toindph26899.entity.NhanVien;
import com.toindph26899.repository.NhanVienRepository;
import com.toindph26899.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }
}

package com.toindph26899.service;

import com.toindph26899.entity.NhanVien;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NhanVienService {

    List<NhanVien> findAll();

    void save(NhanVien nhanVien);

}

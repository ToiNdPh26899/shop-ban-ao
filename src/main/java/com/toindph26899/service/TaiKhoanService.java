package com.toindph26899.service;

import com.toindph26899.entity.TaiKhoan;
import com.toindph26899.request.DangKiTaiKhoan;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TaiKhoanService extends UserDetailsService {

    TaiKhoan findByUser(String username);

    TaiKhoan checkLogin();

    void saveUser(DangKiTaiKhoan dangKiTaiKhoan);

}

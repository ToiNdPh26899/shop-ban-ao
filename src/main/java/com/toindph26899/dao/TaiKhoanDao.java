package com.toindph26899.dao;

import com.toindph26899.entity.KhachHang;
import com.toindph26899.entity.TaiKhoan;

public interface TaiKhoanDao {

    TaiKhoan findByUser(String uname);

    KhachHang findByCustomer(String uname);

}

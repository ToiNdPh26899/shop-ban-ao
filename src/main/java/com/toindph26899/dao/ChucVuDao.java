package com.toindph26899.dao;

import com.toindph26899.entity.ChucVu;

import java.util.Collection;

public interface ChucVuDao {

    Collection<ChucVu> findAllWithUname(String uname);

    ChucVu findRoleByName(String roleEmployee);
}

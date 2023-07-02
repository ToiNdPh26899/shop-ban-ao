package com.toindph26899.repository;

import com.toindph26899.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, UUID> {

    TaiKhoan findTaiKhoanByUsername(String uname);

}

package com.toindph26899.repository;

import com.toindph26899.entity.DonHangChiTiet;
import com.toindph26899.response.TraCuuDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangChiTietRepository extends JpaRepository<DonHangChiTiet, Long> {

    @Query(nativeQuery = true)
    List<TraCuuDonHang> traCuuDonHangs(String soDienThoai);
}

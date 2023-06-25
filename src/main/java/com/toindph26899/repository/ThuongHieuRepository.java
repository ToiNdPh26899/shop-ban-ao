package com.toindph26899.repository;

import com.toindph26899.entity.ThuongHieu;
import com.toindph26899.response.ThuongHieuResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {

    @Query("select new com.toindph26899.response.ThuongHieuResponse(th.id, th.tenThuongHieu, th.trangThai) from ThuongHieu th")
    List<ThuongHieuResponse> findAllCustom();
}

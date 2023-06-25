package com.toindph26899.repository;

import com.toindph26899.entity.AnhSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham, Long> {

    @Query(value = "select * from anh_san_pham where id_san_pham = ?", nativeQuery = true)
    AnhSanPham anhSanPham(Long id);

}

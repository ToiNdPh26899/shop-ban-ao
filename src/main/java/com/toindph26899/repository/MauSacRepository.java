package com.toindph26899.repository;

import com.toindph26899.entity.MauSac;
import com.toindph26899.response.MauSacResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Long> {

    @Query("select new com.toindph26899.response.MauSacResponse(ms.id, ms.tenMauSac, ms.trangThai) from MauSac ms")
    List<MauSacResponse> findAllCustom();

    MauSac findMauSacById(Long idMauSac);

    @Query(nativeQuery = true)
    List<MauSacResponse> findAllMauSacByIdSanPham(Long idSanPham);

}

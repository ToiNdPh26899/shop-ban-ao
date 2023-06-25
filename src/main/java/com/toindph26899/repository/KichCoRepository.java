package com.toindph26899.repository;

import com.toindph26899.entity.KichCo;
import com.toindph26899.response.KichCoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo, Long> {

    @Query("select new com.toindph26899.response.KichCoResponse(kc.id, kc.loaiKichCo, kc.trangThai) from KichCo kc")
    List<KichCoResponse> findAllCustom();

    @Query(nativeQuery = true)
    List<KichCoResponse> findAllKichCo(Long id);

    KichCo findKichCoById(Long idKichCo);
}

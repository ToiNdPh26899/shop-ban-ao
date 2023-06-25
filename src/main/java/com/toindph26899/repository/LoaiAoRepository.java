package com.toindph26899.repository;

import com.toindph26899.entity.LoaiAo;
import com.toindph26899.response.LoaiAoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiAoRepository extends JpaRepository<LoaiAo, Long> {

    @Query("select new com.toindph26899.response.LoaiAoResponse(la.id, la.tenLoaiAo, la.trangThai) from LoaiAo la")
    List<LoaiAoResponse> findAllCustom();
}

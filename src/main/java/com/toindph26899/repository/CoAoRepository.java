package com.toindph26899.repository;

import com.toindph26899.entity.CoAo;
import com.toindph26899.response.CoAoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoAoRepository extends JpaRepository<CoAo, Long> {

    @Query("select new com.toindph26899.response.CoAoResponse(ca.id, ca.tenCoAo, ca.trangThai) from CoAo ca")
    List<CoAoResponse> findAllCustom();

}

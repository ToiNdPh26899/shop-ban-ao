package com.toindph26899.repository;

import com.toindph26899.entity.TayAo;
import com.toindph26899.response.TayAoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TayAoRepository extends JpaRepository<TayAo, Long> {

    @Query("select new com.toindph26899.response.TayAoResponse(ta.id, ta.loaiTayAo, ta.trangThai) from TayAo ta")
    List<TayAoResponse> findAllCustom();

}

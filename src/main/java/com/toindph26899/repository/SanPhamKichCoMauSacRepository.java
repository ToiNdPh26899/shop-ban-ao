package com.toindph26899.repository;

import com.toindph26899.entity.SanPhamKichCoMauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamKichCoMauSacRepository extends JpaRepository<SanPhamKichCoMauSac, Long> {

}

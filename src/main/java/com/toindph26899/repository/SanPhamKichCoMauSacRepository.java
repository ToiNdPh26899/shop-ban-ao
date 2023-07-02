package com.toindph26899.repository;

import com.toindph26899.entity.SanPhamKichCoMauSac;
import com.toindph26899.request.SanPhamChiTietRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamKichCoMauSacRepository extends JpaRepository<SanPhamKichCoMauSac, Long> {

    SanPhamKichCoMauSac findSanPhamKichCoMauSacById(Long id);

    @Query("select new com.toindph26899.request.SanPhamChiTietRequest(spkcms.id, " +
            "spkcms.idSanPham, spkcms.idKichCo, spkcms.idMauSac, spkcms.soLuong, spkcms.trangThai) " +
            "from SanPhamKichCoMauSac spkcms join SanPham sp on spkcms.idSanPham.id = sp.id " +
            "join KichCo kc on spkcms.idKichCo.id = kc.id " +
            "join MauSac ms on spkcms.idMauSac.id = ms.id " +
            "where sp.id =:idSanPham and kc.loaiKichCo =:kichCo and ms.tenMauSac =:mauSac")
    SanPhamChiTietRequest getOne(@Param("idSanPham") Long idSanPham,
                                 @Param("kichCo") String mauSac,
                                 @Param("mauSac") String kichCo);

    @Query(value = "select spkcms.Id\n" +
            "from san_pham_kich_co_mau_sac spkcms \n" +
            "join san_pham sp on spkcms.id_san_pham = sp.id\n" +
            "join kich_co kc on spkcms.id_kich_co = kc.Id\n" +
            "join mau_sac ms on spkcms.id_mau_sac = ms.id\n" +
            "where sp.Id=? and ms.ten_mau_sac=? and kc.size=?", nativeQuery = true)
    Long idSanPhamChiTiet(Long idSanPham, String mauSac, String kichCo);

}

package com.toindph26899.repository;

import com.toindph26899.entity.SanPham;
import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.response.SanPhamResponse;
import com.toindph26899.response.SanPhamView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("select new com.toindph26899.response.SanPhamResponse(sp.id, sp.maSanPham, " +
            "sp.tenSanPham, sp.soLuongTon, " +
            "sp.giaGiaoBan, sp.tenSanPham, " +
            "sp.idCoAo.tenCoAo, sp.idChatLieu.tenChatLieu, sp.idThuongHieu.tenThuongHieu, " +
            "sp.idLoaiAo.tenLoaiAo) " +
            "from SanPham sp ")
    List<SanPhamResponse> findAllSanPhamCustom();

    @Query("select new com.toindph26899.response.SanPhamChiTietResponse(spkc.id, sp.id, " +
            "sp.tenSanPham, sp.giaGiaoBan, asp.duongDanAnh, sp.moTa, " +
            "sp.idChatLieu.tenChatLieu, spkc.idMauSac.tenMauSac, sp.idLoaiAo.tenLoaiAo, " +
            "sp.idCoAo.tenCoAo, sp.idTayAo.loaiTayAo, sp.idThuongHieu.tenThuongHieu, " +
            "spkc.idKichCo.loaiKichCo, spkc.soLuong, sp.soLuongTon, spkc.idKichCo.id, spkc.idMauSac.id) " +
            "from SanPham sp join AnhSanPham asp on sp.id = asp.idSanPham.id " +
            "join SanPhamKichCoMauSac spkc on spkc.idSanPham.id = sp.id " +
            "join KichCo kc on spkc.idKichCo.id = kc.id")
    List<SanPhamChiTietResponse> sanPhamChiTiets();

    @Query(nativeQuery = true)
    List<SanPhamView> listHienThi();

    SanPham findSanPhamById(Long idSanPham);
}

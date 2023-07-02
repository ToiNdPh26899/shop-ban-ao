package com.toindph26899.repository;

import com.toindph26899.entity.GioHangChiTiet;
import com.toindph26899.response.SanPhamChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

    @Query("select new com.toindph26899.response.SanPhamChiTietResponse(ghct.id, ghct.idSanPham.id, " +
            "ghct.idSanPham.idSanPham.tenSanPham, ghct.idSanPham.idSanPham.giaGiaoBan," +
            " asp.duongDanAnh, ghct.idSanPham.idSanPham.moTa, ghct.idSanPham.idSanPham.idChatLieu.tenChatLieu, " +
            "spkcms.idMauSac.tenMauSac, ghct.idSanPham.idSanPham.idLoaiAo.tenLoaiAo, ghct.idSanPham.idSanPham.idCoAo.tenCoAo, " +
            "ghct.idSanPham.idSanPham.idTayAo.loaiTayAo, ghct.idSanPham.idSanPham.idThuongHieu.tenThuongHieu, " +
            "spkcms.idKichCo.loaiKichCo, ghct.soLuong, ghct.soLuong, spkcms.idKichCo.id, " +
            "spkcms.idMauSac.id) " +
            "from GioHang gh join KhachHang kh on kh.id = gh.nguoiSoHuu.id " +
            "join GioHangChiTiet ghct on ghct.idGioHang.id = gh.id " +
            "join SanPhamKichCoMauSac spkcms on spkcms.id = ghct.idSanPham.id " +
            "join AnhSanPham asp on spkcms.idSanPham.id = asp.idSanPham.id " +
            "join TaiKhoan tk on tk.idKhachHang.id = kh.id " +
            "where tk.id =:id")
    List<SanPhamChiTietResponse> gioHangByIdKhachHang(@Param("id") UUID id);

}

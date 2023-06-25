package com.toindph26899.repository;

import com.toindph26899.entity.DonHang;
import com.toindph26899.response.LichSuDonHang;
import com.toindph26899.response.TraCuuDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long> {

    @Query("select new com.toindph26899.response.LichSuDonHang(dh.id, asp.duongDanAnh, " +
            "sp.tenSanPham, spkcms.idKichCo.loaiKichCo, spkcms.idMauSac.tenMauSac, " +
            "dhct.soLuong, dhct.donGia, dh.tenNguoiNhan, dh.diaChiNhanHang, dh.soDienThoaiNguoiNhan, " +
            "dh.ngayTao" +
            ") " +
            "from DonHang dh join DonHangChiTiet dhct on dh.id = dhct.idDonHang.id " +
            "join SanPham sp on sp.id = dhct.idSanPham.id " +
            "join AnhSanPham  asp on asp.idSanPham.id = sp.id " +
            "join SanPhamKichCoMauSac spkcms on spkcms.idSanPham.id = sp.id")
    List<LichSuDonHang> findAllLichSuDonHang();

}

package com.toindph26899.response;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DonHangResponse {

    private Long idDonHang;

    private String idSanPham;

    private String tenSanPham;

    private Integer soLuong;

    private String size;

    private String mauSac;

    private String duongDanAnh;

    private String chatLieu;

    private Date ngayMua;

    private Date ngayThanhToan;

    private Double giaGiaoBan;

    private String tenKhachHang;

    private String diaChiNhanHang;

    private String soDienThoaiNhanHang;

    private String maNhanVien;

    private String tenNhanVien;

    private Integer trangThai;

}

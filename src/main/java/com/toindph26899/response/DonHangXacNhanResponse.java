package com.toindph26899.response;

import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DonHangXacNhanResponse {

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

    private Integer trangThaiDonHang;

}

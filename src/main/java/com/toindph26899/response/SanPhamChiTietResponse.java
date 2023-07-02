package com.toindph26899.response;

import com.toindph26899.entity.KichCo;
import com.toindph26899.entity.MauSac;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanPhamChiTietResponse {

    private Long idGioHangChiTiet;

    private Long idSanPham;

    private String tenSanPham;

    private BigDecimal giaGiaoBan;

    private String duongDanAnh;

    private String moTa;

    private String tenChatLieu;

    private String mauSac;

    private String loaiAo;

    private String loaiCoAo;

    private String loaiTayAo;

    private String tenThuongHieu;

    private String kichCo;

    private Integer soLuong;

    private Integer soLuongMua;

    private Long idKichCo;

    private Long idMauSac;
}

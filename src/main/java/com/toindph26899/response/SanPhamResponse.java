package com.toindph26899.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanPhamResponse {

    private Long idSanPham;

    private String maSanPham;

    private String tenSanPham;

    private Integer soLuong;

    private BigDecimal giaGiaoBan;

    private String mauSac;

    private String tenCoAo;

    private String chatLieu;

    private String tenThuongHieu;

    private String loaiTayAo;

}

package com.toindph26899.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanPhamRequest {

    private Long idSanPham;

    private String maSanPham;

    private String tenSanPham;

    private BigDecimal giaGiaoBan;

    private Integer soLuongTon;

    private String moTa;

    private Integer trangThai;

    private Long idChatLieu;

    private Long idCoAo;

    private Long idLoaiAo;

    private Long idThuongHieu;

    private Long idTayAo;

}

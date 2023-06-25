package com.toindph26899.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TraCuuDonHang {

    private Long idDonHang;

    private String tenNguoiMua;

    private String soDienThoaiNguoiMua;

    private String diaChiNguoiMua;

    private String tenSanPham;

    private Integer soLuong;

    private String size;

    private String mauSac;

    private String duongDanAnh;

    private Double giaGiaBan;

}

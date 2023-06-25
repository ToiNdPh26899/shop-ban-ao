package com.toindph26899.request;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartItemRequest {

    private Long idSanPham;

    private String tenSanPham;

    private BigDecimal giaGiaoBan;

    private String moTa;

    private String mauSac;

    private String kichCo;

    private Long soLuong;

    private String duongDanAnh;

}

package com.toindph26899.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanPhamView {

    private Long idSanPham;

    private String tenSanPham;

    private BigDecimal giaGiaoBan;

    private String duongDanAnh;

}

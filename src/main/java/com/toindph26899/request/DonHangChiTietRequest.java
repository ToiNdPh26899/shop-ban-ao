package com.toindph26899.request;

import com.toindph26899.entity.DonHang;
import com.toindph26899.entity.SanPham;
import com.toindph26899.response.SanPhamChiTietResponse;
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
public class DonHangChiTietRequest {

    private Long id;

    private Long idSanPham;

    private Long idDonHang;

    private Integer soLuong;

    private BigDecimal donGia;

    private String ghiChu;

    private Integer trangThai;

}

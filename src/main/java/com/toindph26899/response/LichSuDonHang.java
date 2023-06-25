package com.toindph26899.response;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class LichSuDonHang {

    private Long idDonHang;

    private String anhSanPham;

    private String tenSanPham;

    private String kichCo;

    private String mauSac;

    private Integer soLuong;

    private Double giaSanPham;

    private String nguoiDat;

    private String diaChi;

    private String soDienThoai;

    private Date ngayDat;

    public LichSuDonHang(Long idDonHang, String anhSanPham, String tenSanPham,
                         String kichCo, String mauSac, Integer soLuong,
                         BigDecimal giaSanPham, String nguoiDat, String diaChi,
                         String soDienThoai, Date ngayDat) {
        this.idDonHang = idDonHang;
        this.anhSanPham = anhSanPham;
        this.tenSanPham = tenSanPham;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.soLuong = soLuong;
        this.giaSanPham = Double.parseDouble(String.valueOf(giaSanPham));
        this.nguoiDat = nguoiDat;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.ngayDat = ngayDat;
    }
}

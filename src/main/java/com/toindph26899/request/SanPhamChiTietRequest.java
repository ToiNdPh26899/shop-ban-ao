package com.toindph26899.request;

import com.toindph26899.entity.KichCo;
import com.toindph26899.entity.MauSac;
import com.toindph26899.entity.SanPham;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SanPhamChiTietRequest {

    private Long id;

    private SanPham idSanPham;

    private KichCo idKichCo;

    private MauSac idMauSac;

    private Integer soLuong;

    private Integer trangThai;

}

package com.toindph26899.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanPhamKichCoMauSacRequest {

    private Long idSanPhamKichCo;

    private Long idSanPham;

    private List<Long> idKichCo;

    private List<Long> idMauSac;

    private Integer soLuong;

    private Integer trangThai;

}

package com.toindph26899.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AnhSanPhamRequest {

    private Long idAnh;

    private Long idSanPham;

    private String duongDanAnh;

    private Integer trangThai;

}

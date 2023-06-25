package com.toindph26899.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ThuongHieuResponse {

    private Long idThuongHieu;

    private String tenThuongHieu;

    private Integer trangThai;

}

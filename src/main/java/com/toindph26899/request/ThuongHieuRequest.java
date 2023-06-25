package com.toindph26899.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ThuongHieuRequest {

    private Long idThuongHieu;

    private String tenThuongHieu;

    private Integer trangThai;

}

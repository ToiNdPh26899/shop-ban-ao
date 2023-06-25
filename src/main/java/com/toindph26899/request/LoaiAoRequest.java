package com.toindph26899.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoaiAoRequest {

    private Long idLoaiAo;

    private String tenLoaiAo;

    private Integer trangThai;

}

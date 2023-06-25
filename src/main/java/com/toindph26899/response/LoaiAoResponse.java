package com.toindph26899.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoaiAoResponse {

    private Long idLoaiAo;

    private String tenLoaiAo;

    private Integer trangThai;

}

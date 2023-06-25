package com.toindph26899.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TayAoResponse {

    private Long idTayAo;

    private String loaiTayAo;

    private Integer trangThai;

}

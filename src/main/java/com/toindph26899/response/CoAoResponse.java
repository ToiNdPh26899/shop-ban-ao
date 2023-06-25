package com.toindph26899.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CoAoResponse {

    private Long idCoAo;

    private String tenCoAo;

    private Integer trangThai;

}

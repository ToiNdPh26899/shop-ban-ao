package com.toindph26899.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class KichCoResponse {

    private Long idKichCo;

    private String kichCo;

    private Integer trangThai;

}

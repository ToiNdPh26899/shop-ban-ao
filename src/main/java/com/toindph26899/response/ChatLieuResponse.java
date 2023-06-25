package com.toindph26899.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChatLieuResponse {

    private Long idChatLieu;

    private String tenChatLieu;

    private Integer trangThai;

}

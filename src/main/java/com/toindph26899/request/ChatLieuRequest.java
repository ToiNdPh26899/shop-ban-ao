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
public class ChatLieuRequest {

    private Long idChatLieu;

    private String tenChatLieu;

    private Integer trangThai;

}

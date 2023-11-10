package com.channeltalk.teamten.member.dto;


import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberChargeDto {

    private Long memberKeyId;
    private Long addPoint;
    private String type;
}

package com.modong.boardservice.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PurchaseReqDTO {

    // 글 id
    private Long id;

    private String url;

    private String price;

    private String productName;

    private String pickupLocation;

    private LocalDateTime closeTime;

    private Long userId;

}

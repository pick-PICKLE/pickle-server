package com.pickle.server.dress.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pickle.server.dress.domain.Dress;
import com.pickle.server.dress.domain.DressReservation;
import com.pickle.server.dress.domain.ReservedDress;
import com.pickle.server.store.domain.StoreOpenDay;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter

@NoArgsConstructor
@Setter
public class DressOrderDto {

    @ApiModelProperty(example = "예약내역 id")
    @JsonProperty("reserved_dress_id")
    private Long reservedDressId;
    @ApiModelProperty(example = "스토어 id")
    @JsonProperty("store_id")
    private Long storeId;

    @ApiModelProperty(example = "스토어 이름")
    @JsonProperty("store_name")
    private String storeName;

    @ApiModelProperty(example = "스토어 주소")
    @JsonProperty("store_address")
    private String storeAddress;

    @ApiModelProperty(example = "운영 시간")
    @JsonProperty("hours_of_operation")
    private String hoursOfOperation;

    @ApiModelProperty(example = "문 여는 요일")
    @JsonProperty("store_open_day")
    private String storeOpenDay;

    @ApiModelProperty(example = "의상 이름")
    @JsonProperty("dress_name")
    private String dressName;
    @ApiModelProperty(example = "의상 이미지 url")
    @JsonProperty("dress_image_url")
    private String dressImageUrl;

    @ApiModelProperty(example = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("pickup_datetime")
    private String pickUpDateTime;

    @JsonProperty("reserved_dress_list")
    private List<ReservedDress> reservedDressList = new ArrayList<>();

    @ApiModelProperty(example = "드레스 픽업 요청사항")
    @JsonProperty("comment")
    private String comment;

    @ApiModelProperty(example = "합계 가격")
    @JsonProperty("price")
    private String price;


    @QueryProjection
    public DressOrderDto(DressReservation dressReservation, ReservedDress reservedDress, String dressImgUrl) {
        DecimalFormat priceFormat = new DecimalFormat("###,###");

        this.reservedDressId = reservedDress.getId();
        this.storeId = dressReservation.getStore().getId();
        this.storeName = dressReservation.getStore().getName();
        this.storeAddress = dressReservation.getStore().getAddress();
        this.dressName = reservedDress.getDress().getName();
        this.dressImageUrl = reservedDress.getDress().getImageList().toString();
        this.hoursOfOperation = dressReservation.getStore().getOpenTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "~"
                + dressReservation.getStore().getCloseTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.storeOpenDay = makeStoreOpenDayIntroduction(dressReservation.getStore().getStoreOpenDay());
        this.pickUpDateTime = dressReservation.getPickUpDateTime().toString();
//        this.pickUpDateTime = reservedDress.getDressReservation().getPickUpDateTime().toString();

        this.reservedDressList = dressReservation.getReservedDressList();
        this.comment =dressReservation.getComment();
        this.price = priceFormat.format(dressReservation.getPrice())+"원";
    }

    private String makeStoreOpenDayIntroduction(StoreOpenDay storeOpenDay){
        String message = "";
        if(storeOpenDay.getMonday()) message = message.concat("월 ");
        if(storeOpenDay.getTuesday()) message = message.concat("화 ");
        if(storeOpenDay.getWednesday()) message = message.concat("수 ");
        if(storeOpenDay.getThursday()) message = message.concat("목 ");
        if(storeOpenDay.getFriday()) message = message.concat("금 ");
        if(storeOpenDay.getSaturday()) message = message.concat("토 ");
        if(storeOpenDay.getSunday()) message = message.concat("일 ");
        return message + "영업";
    }
}
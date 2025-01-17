package com.lotdiz.memberservice.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.lotdiz.memberservice.dto.request.DeliveryAddressInfoForChangeRequestDto;
import com.lotdiz.memberservice.dto.request.DeliveryAddressInfoForCreateRequestDto;
import com.lotdiz.memberservice.entity.common.BaseEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAddress extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long deliveryAddressId;

  @Column(name = "delivery_address_recipient_name", nullable = false)
  private String deliveryAddressRecipientName;

  @Column(name = "delivery_address_recipient_phone_number", nullable = false)
  private String deliveryAddressRecipientPhoneNumber;

  @Column(name = "delivery_address_recipient_email")
  private String deliveryAddressRecipientEmail;

  @Column(name = "delivery_address_request")
  private String deliveryAddressRequest;

  @Column(name = "delivery_address_road_name", nullable = false)
  private String deliveryAddressRoadName;

  @Column(name = "delivery_address_detail", nullable = false)
  private String deliveryAddressDetail;

  @Column(name = "delivery_address_zip_code", nullable = false)
  private String deliveryAddressZipCode;

  @Column(name = "delivery_address_is_default", nullable = false)
  @Builder.Default
  private Boolean deliveryAddressIsDefault = false;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  public static DeliveryAddress create(Member member, DeliveryAddressInfoForCreateRequestDto deliveryAddressInfoCreateDto) {
    DeliveryAddress deliveryAddress = DeliveryAddress.builder()
        .deliveryAddressRecipientName(
            deliveryAddressInfoCreateDto.getDeliveryAddressRecipientName())
        .deliveryAddressRecipientPhoneNumber(
            deliveryAddressInfoCreateDto.getDeliveryAddressRecipientPhoneNumber())
        .deliveryAddressRecipientEmail(
            deliveryAddressInfoCreateDto.getDeliveryAddressRecipientEmail())
        .deliveryAddressRoadName(deliveryAddressInfoCreateDto.getDeliveryAddressRoadName())
        .deliveryAddressDetail(deliveryAddressInfoCreateDto.getDeliveryAddressDetail())
        .deliveryAddressZipCode(deliveryAddressInfoCreateDto.getDeliveryAddressZipCode())
        .member(member)
        .build();

    if(deliveryAddressInfoCreateDto.getDeliveryAddressRecipientEmail() != null) {
      deliveryAddress.assignDeliveryAddressRecipientEmail(deliveryAddressInfoCreateDto.getDeliveryAddressRecipientEmail());
    }

    if(deliveryAddressInfoCreateDto.getDeliveryAddressRequest() != null) {
      deliveryAddress.assignDeliveryAddressRequest(deliveryAddressInfoCreateDto.getDeliveryAddressRequest());
    }

    return deliveryAddress;
  }

  public static DeliveryAddress renew(DeliveryAddress deliveryAddress, DeliveryAddressInfoForChangeRequestDto deliveryAddressInfoDto) {
    deliveryAddress.assignDeliveryAddressRecipientName(deliveryAddressInfoDto.getDeliveryAddressRecipientName());
    deliveryAddress.assignDeliveryAddressRecipientPhoneNumber(deliveryAddressInfoDto.getDeliveryAddressRecipientPhoneNumber());
    deliveryAddress.assignDeliveryAddressRoadName(deliveryAddressInfoDto.getDeliveryAddressRoadName());
    deliveryAddress.assignDeliveryAddressDetail(deliveryAddressInfoDto.getDeliveryAddressDetail());
    deliveryAddress.assignDeliveryAddressZipCode(deliveryAddressInfoDto.getDeliveryAddressZipCode());

    if(deliveryAddressInfoDto.getDeliveryAddressRecipientEmail() != null) {
      deliveryAddress.assignDeliveryAddressRecipientEmail(deliveryAddressInfoDto.getDeliveryAddressRecipientEmail());
    }

    if(deliveryAddressInfoDto.getDeliveryAddressRequest() != null) {
      deliveryAddress.assignDeliveryAddressRequest(deliveryAddressInfoDto.getDeliveryAddressRequest());
    }
    return deliveryAddress;
  }

  private void assignDeliveryAddressZipCode(String deliveryAddressZipCode) {
    this.deliveryAddressZipCode = deliveryAddressZipCode;
  }

  private void assignDeliveryAddressDetail(String deliveryAddressDetail) {
    this.deliveryAddressDetail = deliveryAddressDetail;
  }

  private void assignDeliveryAddressRoadName(String deliveryAddressRoadName) {
    this.deliveryAddressRoadName = deliveryAddressRoadName;
  }

  private void assignDeliveryAddressRecipientPhoneNumber(String deliveryAddressRecipientPhoneNumber) {
    this.deliveryAddressRecipientPhoneNumber = deliveryAddressRecipientPhoneNumber;
  }

  private void assignDeliveryAddressRecipientName(String deliveryAddressRecipientName) {
    this.deliveryAddressRecipientName = deliveryAddressRecipientName;
  }

  private void assignDeliveryAddressRequest(String deliveryAddressRequest) {
    this.deliveryAddressRequest = deliveryAddressRequest;
  }

  private void assignDeliveryAddressRecipientEmail(String deliveryAddressRecipientEmail) {
    this.deliveryAddressRecipientEmail = deliveryAddressRecipientEmail;
  }
}

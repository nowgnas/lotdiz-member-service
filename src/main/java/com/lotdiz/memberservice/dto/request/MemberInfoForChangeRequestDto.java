package com.lotdiz.memberservice.dto.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoForChangeRequestDto {
  @Pattern(
      regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$",
      message = "영문, 숫자, 특수문자 모두 포함하여 작성해주세요.")
  @Size(min = 8, max = 16, message = "8 ~ 16자로 작성해주세요.")
  private String originPassword;

  @Pattern(
      regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$",
      message = "영문, 숫자, 특수문자 모두 포함하여 작성해주세요.")
  @Size(min = 8, max = 16, message = "8 ~ 16자로 작성해주세요.")
  private String newPassword;

  @NotNull
  @Size(max = 10)
  private String memberName;

  @NotNull
  @Pattern(regexp = "[0-9]{10,11}$", message = "-없이 10 ~ 11자리 숫자를 입력해주세요.")
  private String memberPhoneNumber;

  @NotNull private String memberProfileImageUrl;
}

package com.archivsoft.nobelWill.payload;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignRequest {

    @NonNull
    private String username;

    @NonNull
    private String password;

}
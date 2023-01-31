package com.bit.springApp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest { //Yeni kayıt olan kullanıcı için oluşturulan nesnedir -> AuthenticationService deki token oluşturmada methodunda kullanılır.

  private String firstname;
  private String lastname;
  private String email;
  private String password;
}

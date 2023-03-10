package com.bit.springApp.security.auth;

import java.util.List;

import com.bit.springApp.enums.Role;

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
	private List<Role> roles;
	}
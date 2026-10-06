package com.senai.acesso.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {

    public static String encode(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(senha);
        System.out.println(senhaCriptografada);
        return senhaCriptografada;
    }
    public static boolean matches(String senha, String senhaCriptografada) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senha, senhaCriptografada);
    }

}
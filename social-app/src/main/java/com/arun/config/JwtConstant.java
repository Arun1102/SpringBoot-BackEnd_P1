package com.arun.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtConstant{

    public static String JWT_HEADER="Authorization";
    public static String SECRET_KEY = "juifdhefhwfiehnfoanfqoufnqefioeqnfqeofnoanfqjonqfwjan";
}

/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package net.dualsoft.Blockbuster;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author teodora
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

    private final List<String> allowedOrigins
            = Arrays.asList(
                    "http://109.94.109.1",
                    "http://localhost:4200",
                    "http://localhost:4300",
                    "http://localhost:8100",
                    "http://localhost:8101",
                    "http://localhost:8102",
                    "http://localhost:8103",
                    "http://localhost:8080",
                    "http://192.168.0.197:8100",
                    "http://192.168.0.84:8100",
                    "http://192.168.0.75:8100",
                    "https://192.168.0.197:8100",
                    "https://192.168.0.81:8100",
                    "https://192.168.0.*:8100",
                    "http://praktikanti.dualsoft.net",
                    "http://praktikanti.dualsoft.net:81"
            );
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try{
        String origin = request.getHeader("Origin");

        response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
        //response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token, set-cookie");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    
    } catch(Exception e) {}
}

}

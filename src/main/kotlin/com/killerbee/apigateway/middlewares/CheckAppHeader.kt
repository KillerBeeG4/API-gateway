package com.killerbee.apigateway.middlewares

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(1)
class CheckAppHeader : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("X-KILLERBEE-APP")
        if(header != null && header == "ERP")
        {
            filterChain.doFilter(request, response)
        }
        else {
            response.sendError(503, "Error: request not comming from the ERP app")
        }
    }
}
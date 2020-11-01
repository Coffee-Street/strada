package com.wnsgml972.strada.security.service

import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

class HeaderFilter : Filter {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val res = response as HttpServletResponse
        res.setHeader("Access-Control-Allow-Origin", "*")
        res.setHeader("Access-Control-Allow-Methods", "GET, POST")
        res.setHeader("Access-Control-Max-Age", "3600")
        res.setHeader(
            "Access-Control-Allow-Headers",
            "X-Requested-With, Content-Type, Authorization, X-XSRF-token"
        )
        res.setHeader("Access-Control-Allow-Credentials", "false")
        chain.doFilter(request, response)
    }
}
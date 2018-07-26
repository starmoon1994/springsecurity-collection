package com.company.testss13.support.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * 封装分页参数的filter
 * @author hxy
 */
public class PageFilter implements Filter {
    public static final Logger LOGGER = LoggerFactory.getLogger(PageFilter.class);
    public static final int PAGE_SIZE_MAX = 20;// 默认每页显示多少行

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        PageSystemContext.setPageNum(getOffset(httpRequest));
        PageSystemContext.setPageSize(getPageSize(httpRequest));

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            PageSystemContext.removePageNum();
            PageSystemContext.removePageSize();
        }
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

    private int getOffset(HttpServletRequest httpRequest) {
        int offset = 1;
        try {
            offset = Integer.parseInt(httpRequest.getParameter("pageNum"));
        } catch (Exception ingore) {
        }
        httpRequest.setAttribute("pageNum", offset);
        return offset;
    }

    private int getPageSize(HttpServletRequest httpRequest) {
        String pageSize = httpRequest.getParameter("pageSize");// 得到用户自己的每页显示的行数
        Integer ps = PAGE_SIZE_MAX;
        if (pageSize != null) {
            try {
                ps = Integer.parseInt(pageSize);
            } catch (Exception ignore) {

            }
        }
        httpRequest.getSession().setAttribute("pageSize", ps);
        return ps;
    }
}

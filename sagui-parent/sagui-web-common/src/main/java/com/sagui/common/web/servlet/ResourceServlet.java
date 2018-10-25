package com.sagui.common.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.sagui.commons.util.Resources;
import com.sagui.dataset.commons.log.FatuLoggerFactory;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/ResourceServlet")
public class ResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Logger log = FatuLoggerFactory.create();


    public ResourceServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream = null;
        try {
            String resName = request.getPathInfo();

            if (resName.startsWith("/")) {
                resName = resName.replaceFirst("/", "");
            }

            if (StringUtils.endsWith(resName, ".js")) {
                response.setContentType("text/javascript");
                response.setCharacterEncoding("ISO-8859-1");
            } else if (StringUtils.endsWith(resName, ".css")) {
                response.setContentType("text/css");
                response.setCharacterEncoding("ISO-8859-1");
            }

            Resources resource = new Resources(resName);
            outputStream = response.getOutputStream();
            outputStream.write(resource.asBinary());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.flush();
        }
    }

}

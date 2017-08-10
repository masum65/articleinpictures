package com.articleinpictures.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Teemu Hirvonen
 */
@Controller
public class RedirectController implements ErrorController {

    @GetMapping("/error")
    public void redirectErrorsToHome(final HttpServletResponse response) throws IOException {
        response.sendRedirect("/#/404");
    }
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
}

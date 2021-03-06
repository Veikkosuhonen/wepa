package com.github.vesuvesu.wepa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorCode", "404");
            } else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                model.addAttribute("errorCode", "405");
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorCode", "500");
            } else {
                model.addAttribute("errorCode", "");
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}

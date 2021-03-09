package io.softwarestrategies.projectx.ui.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SimpleUiServerErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String errorTitle = "Sorry!";
        String errorMessage = "Something went wrong.  We are working on it.";
        String errorPage = "error";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorTitle = "Oops!";
                errorMessage = "We couldn't find the page that you were looking for (404)";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorTitle = "Oops!";
                errorMessage = "Something went wrong and we are working on it.";
            }
        }

        model.addAttribute("errorTitle", errorTitle);
        model.addAttribute("errorMessage", errorMessage);

        return errorPage;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
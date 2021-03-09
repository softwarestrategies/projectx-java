package io.softwarestrategies.projectx.ui.web;

import io.softwarestrategies.projectx.common.data.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class HomeController {

    @Value("${resourceserver.api.project.url}")
    private String projectApiUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping("/home")
    public String getHome(Model model) {
        List<ProjectDTO> projects = webClient.get().uri(projectApiUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProjectDTO.class)
                .collectList()
                .block();
        model.addAttribute("projects", projects);
        return "home";
    }
}

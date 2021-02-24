package io.softwarestrategies.projectx.ui.controller;

import io.softwarestrategies.projectx.ui.data.dto.ProjectDTO;
import io.softwarestrategies.projectx.ui.data.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class ProjectController {

    @Value("${resourceserver.api.project.url}")
    private String projectApiUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping("/projects")
    public String getProjects(Model model) {
        List<ProjectDTO> projects = webClient.get().uri(projectApiUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProjectDTO.class)
                .collectList()
                .block();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/addproject")
    public String addNewProject(Model model) {
        model.addAttribute("project", new ProjectDTO(0, ""));
        return "addproject";
    }

    @PostMapping("/projects")
    public String saveProject(ProjectDTO projectDTO, Model model) {
        projectDTO.setStatus(Status.NEW.name());

        try {
            webClient.post()
                    .uri(projectApiUrl)
                    .bodyValue(projectDTO)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            return "redirect:/projects";
        }
        catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "addproject";
        }
    }
}

package io.softwarestrategies.projectx.ui.web;

import io.softwarestrategies.projectx.common.data.dto.ProjectDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

@Controller
public class ProjectController {

    private static Log log = LogFactory.getLog(ProjectController.class);

    @Value("${resourceserver.api.project.url}")
    private String projectApiUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping({ "/project", "/project/{id}" })
    public String addNewProject(@PathVariable(required = false) Integer id, Model model) {
        ProjectDTO projectDTO = new ProjectDTO();

        if (id != null) {
            projectDTO = webClient.get()
                    .uri(projectApiUrl + id)
                    .retrieve()
                    .bodyToMono(ProjectDTO.class).block();
        }

        model.addAttribute("project", projectDTO);
        return "project";
    }

    @PostMapping("/project")
    public String saveProject(@Valid @ModelAttribute("project") ProjectDTO projectDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "project";
        }

        try {
            if (projectDTO.getId() != null) {
                webClient.patch()
                        .uri(projectApiUrl + projectDTO.getId())
                        .bodyValue(projectDTO)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            }
            else {
                webClient.post()
                        .uri(projectApiUrl)
                        .bodyValue(projectDTO)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            }

            return "redirect:/home";
        }
        catch (Exception e) {
            log.error("Unable to save project: " + e.getMessage());
            model.addAttribute("globalError", "Unable to save project");
            return "project";
        }
    }

    @GetMapping("/delete-project/{id}")
    public String deleteProject(@PathVariable(required = false) Integer id) {
        try {
            webClient.delete()
                        .uri(projectApiUrl + id)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            return "redirect:/home";
        }
        catch (Exception e) {
            log.error("Unable to delete project: " + e.getMessage());
            return "redirect:/home";
        }
    }
}

package io.softwarestrategies.projectx.ui.data;

import lombok.Data;

@Data
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer userId;
    private ProjectStatus status;

    public ProjectDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

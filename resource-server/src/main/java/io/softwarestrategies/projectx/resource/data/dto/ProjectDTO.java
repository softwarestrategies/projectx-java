package io.softwarestrategies.projectx.resource.data.dto;

import lombok.Data;

@Data
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private String status;

    public ProjectDTO() {}
}

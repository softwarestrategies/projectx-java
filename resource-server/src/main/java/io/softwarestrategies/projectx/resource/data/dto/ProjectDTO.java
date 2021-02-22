package io.softwarestrategies.projectx.resource.data.dto;

import io.softwarestrategies.projectx.resource.data.entity.Project;
import io.softwarestrategies.projectx.resource.data.enums.ProjectStatus;
import lombok.Data;

@Data
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer userId;
    private ProjectStatus projectStatus;
}

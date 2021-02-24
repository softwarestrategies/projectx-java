package io.softwarestrategies.projectx.common.data.dto;

import io.softwarestrategies.projectx.common.data.enums.Status;
import lombok.Data;

@Data
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private String status;

    public ProjectDTO() {}

    public ProjectDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = Status.NEW.name();
    }
}
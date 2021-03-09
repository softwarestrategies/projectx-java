package io.softwarestrategies.projectx.common.data.dto;

import io.softwarestrategies.projectx.common.data.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProjectDTO {

    private Integer id;

    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Size(min = 10, max = 200, message = "Description must be between 10 and 255 characters")
    private String description;

    @NotEmpty
    private String status;

    public ProjectDTO() {}

    public ProjectDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = Status.NEW.name();
    }
}

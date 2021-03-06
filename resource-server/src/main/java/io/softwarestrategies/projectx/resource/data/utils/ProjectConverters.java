package io.softwarestrategies.projectx.resource.data.utils;

import io.r2dbc.spi.Row;
import io.softwarestrategies.projectx.common.data.dto.ProjectDTO;
import io.softwarestrategies.projectx.common.data.enums.Status;
import io.softwarestrategies.projectx.resource.data.entity.Project;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ProjectConverters {

    public static Mono<ProjectDTO> toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStatus(project.getStatus().name());
        return Mono.just(projectDTO);
    }

    public static Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(Status.fromName(projectDTO.getStatus()));
        return project;
    }

    public static Boolean rowHasValue(Row row, String key) {
        try {
            row.get(key);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static Project toProjectFromRow(Row row) {
        Project project = new Project();
        if (rowHasValue(row,"id"))          { project.setId((Integer) row.get("id") ); }
        if (rowHasValue(row,"version"))     { project.setVersion((Integer) row.get("version") ); }
        if (rowHasValue(row,"created_on"))  { project.setCreatedOn((LocalDateTime) row.get("created_on") ); }
        if (rowHasValue(row,"modified_on")) { project.setModifiedOn((LocalDateTime) row.get("modified_on") ); }
        if (rowHasValue(row,"created_by"))  { project.setCreatedBy((String) row.get("created_by") ); }
        if (rowHasValue(row,"modified_by")) { project.setModifiedBy((String) row.get("modified_by") ); }
        if (rowHasValue(row,"name"))        { project.setName((String) row.get("name") ); }
        if (rowHasValue(row,"description")) { project.setDescription((String) row.get("description") ); }
        if (rowHasValue(row,"status"))      { project.setStatus(Status.fromAbbreviation((String) row.get("status"))); }
        return project;
    }
}

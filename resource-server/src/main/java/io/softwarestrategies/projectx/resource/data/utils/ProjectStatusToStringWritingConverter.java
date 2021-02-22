package io.softwarestrategies.projectx.resource.data.utils;

import io.softwarestrategies.projectx.resource.data.entity.Project;
import io.softwarestrategies.projectx.resource.data.enums.ProjectStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ProjectStatusToStringWritingConverter implements Converter<ProjectStatus, String> {

    @Override
    public String convert(ProjectStatus projectStatus) {
        return projectStatus.getAbbreviation();
    }
}
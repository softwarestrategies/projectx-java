package io.softwarestrategies.projectx.resource.data.utils;

import io.softwarestrategies.projectx.resource.data.enums.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ProjectStatusToStringWritingConverter implements Converter<Status, String> {

    @Override
    public String convert(Status status) {
        return status.getAbbreviation();
    }
}
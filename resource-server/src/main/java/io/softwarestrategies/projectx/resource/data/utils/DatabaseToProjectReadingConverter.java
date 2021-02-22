package io.softwarestrategies.projectx.resource.data.utils;

import io.r2dbc.spi.Row;
import io.softwarestrategies.projectx.resource.data.entity.Project;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@ReadingConverter
public class DatabaseToProjectReadingConverter implements Converter<Row, Project> {

    @Override
    public Project convert(@NonNull Row row) {
        return ProjectConverters.toProjectFromRow(row);
    }
}

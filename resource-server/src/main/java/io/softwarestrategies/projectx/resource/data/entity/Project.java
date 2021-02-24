package io.softwarestrategies.projectx.resource.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.softwarestrategies.projectx.resource.data.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("project")
public class Project {

    @Id
    @Column("id")
    private Integer id;

    @Version
    @Column("version")
    private Integer version;

    @CreatedDate
    @JsonIgnore
    @Column("created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @LastModifiedDate
    @JsonIgnore
    @Column("modified_on")
    private LocalDateTime modifiedOn = LocalDateTime.now();

    @CreatedBy
    @JsonIgnore
    @Column("created_by")
    private String createdBy = "TODO";

    @LastModifiedBy
    @JsonIgnore
    @Column("modified_by")
    private String modifiedBy = "TODO";

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("status")
    private Status status;
}

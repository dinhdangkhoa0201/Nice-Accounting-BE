package com.example.mybackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_CATEGORY")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ID")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY")),
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE")),
        @AttributeOverride(name = "updateBy", column = @Column(name = "UPDATE_BY")),
        @AttributeOverride(name = "updateDate", column = @Column(name = "UPDATE_DATE")),
})
@Data
public class CategoryEntity extends AbstractBaseEntity implements Serializable {
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String desc;
}

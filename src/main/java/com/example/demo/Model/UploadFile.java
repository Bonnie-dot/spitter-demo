package com.example.demo.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="t_files")
public class UploadFile {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime createTime;



}

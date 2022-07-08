package com.ogbc.archive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassageModel
{
    @NotBlank
    private String book;

    @NotNull
    private Integer chapter;

    private Integer verse;
}

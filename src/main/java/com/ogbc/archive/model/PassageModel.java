package com.ogbc.archive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassageModel
{
    @NotBlank
    @Size(max = 32)
    private String book;

    @NotNull
    private Integer chapter;

    private Integer verse;
}

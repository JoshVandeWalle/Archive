package com.ogbc.archive.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

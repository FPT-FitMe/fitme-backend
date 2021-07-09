package com.fpt.fitme.dto.exercise;

import com.fpt.fitme.dto.tag.TagDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ExerciseTagDTO {
    private Long exerciseID;
    private Set<TagDTO> tags;
}

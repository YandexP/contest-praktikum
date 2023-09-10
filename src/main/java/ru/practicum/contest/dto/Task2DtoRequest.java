package ru.practicum.contest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task2DtoRequest {
    private String encoded;
    private Integer offset;
}

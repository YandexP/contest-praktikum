package ru.practicum.contest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task2DtoResult {
    private String nextTaskUrl;
    private String message;
    private Boolean completed;
}

package ru.practicum.contest.dto.stepFour;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task4DtoResult {
    private Boolean completed;
    private String message;
    private String errorMessage;
}

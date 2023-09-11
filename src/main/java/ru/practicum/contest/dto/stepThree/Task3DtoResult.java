package ru.practicum.contest.dto.stepThree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task3DtoResult {
    private String nextTaskUrl;
    private String message;
    private Boolean completed;
    private String prompt;
}

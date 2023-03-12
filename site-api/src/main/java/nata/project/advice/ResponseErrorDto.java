package nata.project.advice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseErrorDto {
    private final boolean success;
    private final String message;
}

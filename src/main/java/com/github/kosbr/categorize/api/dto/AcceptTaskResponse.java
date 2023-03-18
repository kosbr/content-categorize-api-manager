package com.github.kosbr.categorize.api.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * REST Response when the task to categorize the URL has been accepted.
 */
@Data
@AllArgsConstructor
public class AcceptTaskResponse {

    private UUID id;
}

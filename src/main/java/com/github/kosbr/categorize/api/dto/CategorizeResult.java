package com.github.kosbr.categorize.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;

/**
 * REST result for the client about categorization
 */
@Data
public class CategorizeResult {

    private UUID id;

    private String url;

    private OffsetDateTime created;

    private OffsetDateTime updated;

    private CategorizeStatus status;

    // todo better convert to List
    private String result;
}

package com.github.kosbr.categorize.api.dto;

import lombok.Data;

/**
 * REST DTO to create task to categorize URL
 */
@Data
public class CategorizeRequest {

    private String url;

}

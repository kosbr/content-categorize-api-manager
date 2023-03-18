package com.github.kosbr.categorize.api.converter;

import com.github.kosbr.categorize.api.domain.Status;
import com.github.kosbr.categorize.api.dto.CategorizeStatus;
import org.springframework.stereotype.Component;

@Component
public class StatusConverter {

    public CategorizeStatus convert(Status status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case ERROR: return CategorizeStatus.ERROR;
            case READY: return CategorizeStatus.READY;
            case PENDING: return CategorizeStatus.PENDING;
            default:
                throw new UnsupportedOperationException("Unknown status");
        }

    }

}

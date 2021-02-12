package com.notif.service.notif.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPhraseModel {
    @NonNull
    private String phrase;
    @NonNull
    private int page;
    @NonNull
    private int size;
}

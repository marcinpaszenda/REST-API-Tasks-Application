package com.crud.tasks.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

}

package com.onurbcd.eruservice.persistency.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
public class Identifier extends Audit {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
}

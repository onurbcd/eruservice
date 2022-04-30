package com.onurbcd.eruservice.persistency.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@MappedSuperclass
public class Identifier extends Audit {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
}

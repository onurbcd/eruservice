package com.onurbcd.eruservice.dto.document;

import com.onurbcd.eruservice.persistency.entity.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateDocumentDto {

    private Set<Document> newDocuments;

    private Set<Document> deleteDocuments;
}

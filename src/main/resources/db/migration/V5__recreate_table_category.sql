DROP TABLE public.category;

CREATE TABLE public.category (
    id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    parent_id UUID,
    level SMALLINT NOT NULL,
    last_branch BOOLEAN NOT NULL,
    description VARCHAR(250),
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT uc_category_name UNIQUE (name),
    CONSTRAINT fk_category_category_parent_id FOREIGN KEY (parent_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

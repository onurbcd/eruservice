CREATE TABLE IF NOT EXISTS public.category (
    id UUID NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    description VARCHAR(250),
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT uc_category_name UNIQUE (name)
);

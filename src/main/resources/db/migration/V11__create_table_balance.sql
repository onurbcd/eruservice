CREATE TABLE IF NOT EXISTS public.balance (
    id UUID NOT NULL,
    name VARCHAR(255),
    active BOOLEAN NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    sequence SMALLINT NOT NULL,
    day_id INTEGER NOT NULL,
    source_id UUID NOT NULL,
    category_id UUID NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    code VARCHAR(150) NOT NULL,
    description VARCHAR(250),
    balance_type VARCHAR(7) NOT NULL,
    CONSTRAINT pk_balance PRIMARY KEY (id),
    CONSTRAINT uc_balance_sequence_day_id UNIQUE (sequence, day_id),
    CONSTRAINT fk_balance_category_id FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_balance_day_id FOREIGN KEY (day_id)
        REFERENCES public.day (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_balance_source_id FOREIGN KEY (source_id)
        REFERENCES public.source (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

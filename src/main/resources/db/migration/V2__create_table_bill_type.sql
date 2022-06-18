CREATE TABLE IF NOT EXISTS public.bill_type
(
    id uuid NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    active boolean NOT NULL,
    name character varying(50) NOT NULL,
    CONSTRAINT bill_type_pkey PRIMARY KEY (id),
    CONSTRAINT uk_bill_type_name UNIQUE (name)
)

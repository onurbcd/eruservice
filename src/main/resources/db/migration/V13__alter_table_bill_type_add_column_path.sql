ALTER TABLE public.bill_type ADD COLUMN IF NOT EXISTS path CHARACTER VARYING(250) NULL;

UPDATE public.bill_type SET path = '/';

ALTER TABLE public.bill_type ALTER COLUMN path SET NOT NULL;

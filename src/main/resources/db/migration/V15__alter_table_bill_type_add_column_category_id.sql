ALTER TABLE public.bill_type ADD COLUMN IF NOT EXISTS category_id UUID NULL;

ALTER TABLE bill_type ADD CONSTRAINT fk_bill_type_category_id FOREIGN KEY (category_id) REFERENCES public.category (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

UPDATE public.bill_type SET category_id = (SELECT cat.id FROM public.category cat WHERE cat.name = 'Atar');

ALTER TABLE public.bill_type ALTER COLUMN category_id SET NOT NULL;

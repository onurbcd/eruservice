ALTER TABLE public.bill ADD COLUMN balance_id UUID NULL;

ALTER TABLE public.bill ADD CONSTRAINT fk_bill_balance_id FOREIGN KEY (balance_id) REFERENCES public.balance (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

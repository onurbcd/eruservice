ALTER TABLE public.bill ADD COLUMN IF NOT EXISTS closed BOOLEAN NULL;

UPDATE public.bill SET closed = false;

ALTER TABLE public.bill ALTER COLUMN closed SET NOT NULL;

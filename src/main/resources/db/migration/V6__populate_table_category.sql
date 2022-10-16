INSERT INTO public.category (id, name, active, created_date, last_modified_date, parent_id, level, last_branch, description)
VALUES (gen_random_uuid(), 'Atar', true, now(), now(), null, 1, true, 'parent of all the categories');

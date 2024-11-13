INSERT INTO public.role (
    role_id, role_name
) VALUES
    (NEXTVAL('public.role_role_id_seq'), 'ADMIN'),
    (NEXTVAL('public.role_role_id_seq'), 'INSTRUCTOR'),
    (NEXTVAL('public.role_role_id_seq'), 'STUDENT'),
    (NEXTVAL('public.role_role_id_seq'), 'USER'),
    (NEXTVAL('public.role_role_id_seq'), 'CO-ADMIN');

INSERT INTO public.users(
	user_id, first_name, last_name, email, mobile_number, password, created_at, updated_at)
  	VALUES (NEXTVAL('public.user_user_id_seq'),'Suryank','Mishra','suryankmishra70@gmail.com','6386484824','$2a$10$sCfUaiFnCPy3Dns85L.nXOdC1TqTmq2EZAx3JNXry4lDCpWUQY7Ia','2024-08-13','2024-08-13');

INSERT INTO public.users_role (
    user_id, role_id
) VALUES
    (CURRVAL('public.user_user_id_seq'), 1);


INSERT INTO public.qualification(qualification_id, qualification_name) VALUES
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Bachelor of Arts (BA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Bachelor of Science (BSc)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Bachelor of Commerce (BCom)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Bachelor of Engineering (BE)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Bachelor of Technology (BTech)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Master of Arts (MA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Master of Science (MSc)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Master of Commerce (MCom)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Master of Business Administration (MBA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Master of Technology (MTech)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Doctor of Philosophy (PhD)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Doctor of Business Administration (DBA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Chartered Accountant (CA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Certified Public Accountant (CPA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Diploma in Engineering'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Diploma in Education'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Diploma in Management'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Post Graduate Diploma in Computer Applications (PGDCA)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Bachelor of Education (BEd)'),
    (NEXTVAL('public.qualification_qualification_id_seq'), 'Master of Education (MEd)');


INSERT INTO public.subject(subject_id, subject_name) VALUES
    (NEXTVAL('public.subject_subject_id_seq'), '12th Biology'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Maths'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Maths'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Physics'),
    (NEXTVAL('public.subject_subject_id_seq'), '12th Biology'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Maths'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Biology'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Physics'),
    (NEXTVAL('public.subject_subject_id_seq'), '11th Accounts');
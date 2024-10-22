-- This script is generated for creating the database schema.

BEGIN;

-- Create tables
CREATE TABLE IF NOT EXISTS public.batch (
    batch_id serial NOT NULL,
    batch_name character varying(255) NOT NULL,
    batch_time character varying(100),
    course_id integer,
    CONSTRAINT batch_pkey PRIMARY KEY (batch_id)
);

CREATE TABLE IF NOT EXISTS public.batch_student_enrollment (
    batch_id integer NOT NULL,
    student_id integer NOT NULL,
    CONSTRAINT batch_student_enrollment_pkey PRIMARY KEY (batch_id, student_id)
);

CREATE TABLE IF NOT EXISTS public.coaching (
    coaching_id serial NOT NULL,
    coaching_name character varying(250),
    coaching_address character varying(250),
    coaching_vision character varying(700),
    coaching_admin_id integer,
    CONSTRAINT coaching_pkey PRIMARY KEY (coaching_id),
    CONSTRAINT coaching_coaching_name_key UNIQUE (coaching_name)
);

CREATE TABLE IF NOT EXISTS public.coaching_admin (
    coaching_admin_id serial NOT NULL,
    coaching_admin_name character varying(250) NOT NULL,
    coaching_admin_email character varying(250) NOT NULL,
    mob_num character varying(12) NOT NULL,
    CONSTRAINT coaching_admin_pkey PRIMARY KEY (coaching_admin_id)
);

CREATE TABLE IF NOT EXISTS public.coaching_instructor (
    coaching_id integer NOT NULL,
    instructor_id integer NOT NULL,
    CONSTRAINT coaching_instructor_id_pk PRIMARY KEY (coaching_id, instructor_id)
);

CREATE TABLE IF NOT EXISTS public.coaching_student (
    coaching_id integer NOT NULL,
    student_id integer NOT NULL,
    CONSTRAINT coaching_student_id_pk PRIMARY KEY (coaching_id, student_id)
);

CREATE TABLE IF NOT EXISTS public.comment (
    comment_id serial NOT NULL,
    video_id integer,
    student_id integer,
    comment_text text,
    time_stamp timestamp without time zone,
    CONSTRAINT comment_pkey PRIMARY KEY (comment_id)
);

CREATE TABLE IF NOT EXISTS public.course (
    course_id integer NOT NULL DEFAULT nextval('course_id_seq'::regclass),
    course_name character varying(255) NOT NULL,
    course_duration character varying(100),
    course_cost character varying(50),
    course_type_id integer,
    course_description text,
    start_date date,
    end_date date,
    course_image character varying(1000),
    coaching_id integer,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS public.course_type (
    course_type_id serial NOT NULL,
    course_type_name character varying(50) NOT NULL,
    CONSTRAINT course_type_pkey PRIMARY KEY (course_type_id),
    CONSTRAINT course_type_course_type_name_key UNIQUE (course_type_name)
);

CREATE TABLE IF NOT EXISTS public.date_offline_course_attendance (
    attendance_id integer NOT NULL,
    date_id integer NOT NULL,
    CONSTRAINT offline_attendence_date_pkey PRIMARY KEY (attendance_id, date_id)
);

CREATE TABLE IF NOT EXISTS public.date_online_course_attendance (
    attendance_id integer NOT NULL,
    date_id integer NOT NULL,
    CONSTRAINT online_attendence_date_pkey PRIMARY KEY (attendance_id, date_id)
);

CREATE TABLE IF NOT EXISTS public.date_table (
    date_id serial NOT NULL,
    attendance_date date,
    CONSTRAINT date_table_pkey PRIMARY KEY (date_id)
);

CREATE TABLE IF NOT EXISTS public.instructor (
    instructor_id integer NOT NULL DEFAULT nextval('instructor_id_seq'::regclass),
    instructor_name character varying(255) NOT NULL,
    email character varying(250) NOT NULL,
    CONSTRAINT instructor_pkey PRIMARY KEY (instructor_id),
    CONSTRAINT instructor_email_unique UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.instructor_course (
    instructor_id integer NOT NULL,
    course_id integer NOT NULL,
    CONSTRAINT instructor_course_pkey PRIMARY KEY (instructor_id, course_id)
);

CREATE TABLE IF NOT EXISTS public.instructor_info (
    instructor_id integer NOT NULL,
    instructor_info text,
    CONSTRAINT instructor_info_pkey PRIMARY KEY (instructor_id)
);

CREATE TABLE IF NOT EXISTS public.instructor_qualification (
    qualification_id integer NOT NULL,
    instructor_id integer NOT NULL,
    CONSTRAINT instructor_qualification_pkey PRIMARY KEY (instructor_id, qualification_id)
);

CREATE TABLE IF NOT EXISTS public.instructor_subject (
    instructor_id integer NOT NULL,
    subject_id integer NOT NULL,
    CONSTRAINT instructor_subject_pkey PRIMARY KEY (instructor_id, subject_id)
);

CREATE TABLE IF NOT EXISTS public.offline_course (
    course_id integer NOT NULL,
    status character varying(20) DEFAULT 'upcoming',
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT offline_course_pkey PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS public.offline_course_attendance_record (
    attendance_id integer NOT NULL DEFAULT nextval('attendence_record_attendence_id_seq'::regclass),
    course_id integer NOT NULL,
    batch_id integer NOT NULL,
    subject_id integer NOT NULL,
    student_id integer NOT NULL,
    attendance_count integer,
    CONSTRAINT offline_course_attendence_record_pkey PRIMARY KEY (attendance_id)
);

CREATE TABLE IF NOT EXISTS public.offline_course_subject (
    course_id integer NOT NULL,
    subject_id integer NOT NULL,
    CONSTRAINT subject_offline_course_pkey PRIMARY KEY (course_id, subject_id)
);

CREATE TABLE IF NOT EXISTS public.online_course (
    online_course_id serial NOT NULL,
    course_id integer NOT NULL,
    status character varying(20) DEFAULT 'upcoming',
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT online_course_id_pkey PRIMARY KEY (online_course_id)
);

CREATE TABLE IF NOT EXISTS public.online_course_attendance_record (
    attendance_id integer NOT NULL DEFAULT nextval('online_course_attendence_record_attendence_id_seq'::regclass),
    online_course_id integer NOT NULL,
    subject_id integer NOT NULL,
    student_id integer NOT NULL,
    attendance_count integer,
    CONSTRAINT online_course_attendence_record_pkey PRIMARY KEY (attendance_id),
    CONSTRAINT online_course_attendence_record_composite_unique UNIQUE (online_course_id, subject_id, student_id)
);

CREATE TABLE IF NOT EXISTS public.online_course_old (
    course_id integer NOT NULL,
    status character varying(20) DEFAULT 'upcoming',
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    online_course_id integer NOT NULL DEFAULT nextval('online_course_online_course_id_seq'::regclass),
    CONSTRAINT online_course_pkey PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS public.online_course_student_enrollment (
    online_course_id integer NOT NULL,
    student_id integer NOT NULL,
    CONSTRAINT course_student_enrollment_pkey PRIMARY KEY (online_course_id, student_id)
);

CREATE TABLE IF NOT EXISTS public.online_course_subject (
    online_course_id integer NOT NULL,
    subject_id integer NOT NULL,
    CONSTRAINT subject_online_course_pkey PRIMARY KEY (online_course_id, subject_id)
);

CREATE TABLE IF NOT EXISTS public.qualification (
    qualification_id serial NOT NULL,
    qualification_name character varying(255),
    CONSTRAINT qualification_pkey PRIMARY KEY (qualification_id)
);

CREATE TABLE IF NOT EXISTS public.role (
    role_id serial NOT NULL,
    role_name character varying(50) NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS public.student (
    student_id serial NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(250) NOT NULL,
    mobile_number character varying(12) NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (student_id),
    CONSTRAINT student_email_key UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.subject (
    subject_id serial NOT NULL,
    subject_name character varying(250),
    CONSTRAINT subject_pkey PRIMARY KEY (subject_id)
);

CREATE TABLE IF NOT EXISTS public.users (
    user_id integer NOT NULL DEFAULT nextval('user_user_id_seq'::regclass),
    first_name character varying(50),
    last_name character varying(50),
    email character varying(250),
    mobile_number character varying(12),
    password character varying(250),
    created_at date,
    updated_at date,
    CONSTRAINT user_pkey PRIMARY KEY (user_id),
    CONSTRAINT user_email_key UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.users_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_role_pkey PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS public.video (
    video_id serial NOT NULL,
    online_course_id integer,
    subject_id integer,
    video_url character varying(255),
    likes integer,
    CONSTRAINT video_pkey PRIMARY KEY (video_id)
);

-- Foreign Key Constraints
ALTER TABLE IF EXISTS public.batch
    ADD CONSTRAINT batch_course_id_fkey FOREIGN KEY (course_id)
    REFERENCES public.offline_course (course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.batch_student_enrollment
    ADD CONSTRAINT batch_student_enrollment_batch_id_fkey FOREIGN KEY (batch_id)
    REFERENCES public.batch (batch_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.batch_student_enrollment
    ADD CONSTRAINT batch_student_enrollment_student_id_fkey FOREIGN KEY (student_id)
    REFERENCES public.student (student_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.coaching
    ADD CONSTRAINT coaching_coaching_admin_id_fkey FOREIGN KEY (coaching_admin_id)
    REFERENCES public.coaching_admin (coaching_admin_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.coaching_instructor
    ADD CONSTRAINT coaching_instructor_coaching_id_fkey FOREIGN KEY (coaching_id)
    REFERENCES public.coaching (coaching_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.coaching_instructor
    ADD CONSTRAINT coaching_instructor_instructor_id_fkey FOREIGN KEY (instructor_id)
    REFERENCES public.instructor (instructor_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.coaching_student
    ADD CONSTRAINT coaching_student_coaching_id_fkey FOREIGN KEY (coaching_id)
    REFERENCES public.coaching (coaching_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.coaching_student
    ADD CONSTRAINT coaching_student_student_id_fkey FOREIGN KEY (student_id)
    REFERENCES public.student (student_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.comment
    ADD CONSTRAINT comment_student_id_fkey FOREIGN KEY (student_id)
    REFERENCES public.student (student_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.course
    ADD CONSTRAINT course_course_type_id_fkey FOREIGN KEY (course_type_id)
    REFERENCES public.course_type (course_type_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.course
    ADD CONSTRAINT course_coaching_id_fkey FOREIGN KEY (coaching_id)
    REFERENCES public.coaching (coaching_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.date_offline_course_attendance
    ADD CONSTRAINT date_offline_course_attendance_attendance_id_fkey FOREIGN KEY (attendance_id)
    REFERENCES public.offline_course_attendance_record (attendance_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.date_online_course_attendance
    ADD CONSTRAINT date_online_course_attendance_attendance_id_fkey FOREIGN KEY (attendance_id)
    REFERENCES public.online_course_attendance_record (attendance_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_course
    ADD CONSTRAINT instructor_course_instructor_id_fkey FOREIGN KEY (instructor_id)
    REFERENCES public.instructor (instructor_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_course
    ADD CONSTRAINT instructor_course_course_id_fkey FOREIGN KEY (course_id)
    REFERENCES public.course (course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_info
    ADD CONSTRAINT instructor_info_instructor_id_fkey FOREIGN KEY (instructor_id)
    REFERENCES public.instructor (instructor_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_qualification
    ADD CONSTRAINT instructor_qualification_qualification_id_fkey FOREIGN KEY (qualification_id)
    REFERENCES public.qualification (qualification_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_qualification
    ADD CONSTRAINT instructor_qualification_instructor_id_fkey FOREIGN KEY (instructor_id)
    REFERENCES public.instructor (instructor_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_subject
    ADD CONSTRAINT instructor_subject_instructor_id_fkey FOREIGN KEY (instructor_id)
    REFERENCES public.instructor (instructor_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.instructor_subject
    ADD CONSTRAINT instructor_subject_subject_id_fkey FOREIGN KEY (subject_id)
    REFERENCES public.subject (subject_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.offline_course
    ADD CONSTRAINT offline_course_course_id_fkey FOREIGN KEY (course_id)
    REFERENCES public.course (course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.offline_course_attendance_record
    ADD CONSTRAINT offline_course_attendance_record_course_id_fkey FOREIGN KEY (course_id)
    REFERENCES public.offline_course (course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.offline_course_attendance_record
    ADD CONSTRAINT offline_course_attendance_record_batch_id_fkey FOREIGN KEY (batch_id)
    REFERENCES public.batch (batch_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.offline_course_attendance_record
    ADD CONSTRAINT offline_course_attendance_record_subject_id_fkey FOREIGN KEY (subject_id)
    REFERENCES public.subject (subject_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.offline_course_subject
    ADD CONSTRAINT offline_course_subject_course_id_fkey FOREIGN KEY (course_id)
    REFERENCES public.offline_course (course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.offline_course_subject
    ADD CONSTRAINT offline_course_subject_subject_id_fkey FOREIGN KEY (subject_id)
    REFERENCES public.subject (subject_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course
    ADD CONSTRAINT online_course_course_id_fkey FOREIGN KEY (course_id)
    REFERENCES public.course (course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course_attendance_record
    ADD CONSTRAINT online_course_attendance_record_online_course_id_fkey FOREIGN KEY (online_course_id)
    REFERENCES public.online_course (online_course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course_attendance_record
    ADD CONSTRAINT online_course_attendance_record_subject_id_fkey FOREIGN KEY (subject_id)
    REFERENCES public.subject (subject_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course_student_enrollment
    ADD CONSTRAINT online_course_student_enrollment_online_course_id_fkey FOREIGN KEY (online_course_id)
    REFERENCES public.online_course (online_course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course_student_enrollment
    ADD CONSTRAINT online_course_student_enrollment_student_id_fkey FOREIGN KEY (student_id)
    REFERENCES public.student (student_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course_subject
    ADD CONSTRAINT online_course_subject_online_course_id_fkey FOREIGN KEY (online_course_id)
    REFERENCES public.online_course (online_course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.online_course_subject
    ADD CONSTRAINT online_course_subject_subject_id_fkey FOREIGN KEY (subject_id)
    REFERENCES public.subject (subject_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.users_role
    ADD CONSTRAINT users_role_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.users_role
    ADD CONSTRAINT users_role_role_id_fkey FOREIGN KEY (role_id)
    REFERENCES public.role (role_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.video
    ADD CONSTRAINT video_online_course_id_fkey FOREIGN KEY (online_course_id)
    REFERENCES public.online_course (online_course_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.video
    ADD CONSTRAINT video_subject_id_fkey FOREIGN KEY (subject_id)
    REFERENCES public.subject (subject_id) ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER SEQUENCE public.batch_batch_id_seq RESTART WITH 144;
    ALTER SEQUENCE public.comment_comment_id_seq RESTART WITH 124;
    ALTER SEQUENCE public.coaching_coaching_id_seq RESTART WITH 104;
    ALTER SEQUENCE public.coaching_admin_coaching_admin_id_seq RESTART WITH 105;
    ALTER SEQUENCE public.course_course_id_seq RESTART WITH 102;
    ALTER SEQUENCE public.course_type_course_type_id_seq RESTART WITH 167;
    ALTER SEQUENCE public.date_table_date_id_seq RESTART WITH 187;
    ALTER SEQUENCE public.instructor_instructor_id_seq RESTART WITH 156;
    ALTER SEQUENCE public.qualification_qualification_id_seq RESTART WITH 176;
    ALTER SEQUENCE public.student_student_id_seq RESTART WITH 174;
    ALTER SEQUENCE public.subject_subject_id_seq RESTART WITH 165;
    ALTER SEQUENCE public.users_user_id_seq RESTART WITH 134;
    ALTER SEQUENCE public.video_video_id_seq RESTART WITH 163;

  INSERT INTO public.role(
  	role_id, role_name)
  	VALUES (1,"ADMIN"),(  2,"INSTRUCTOR"),( 3,"STUDENT"),(4,"USER"),(5,"CO-ADMIN");
INSERT INTO public.users (first_name, last_name, email, mobile_number, password, created_at, updated_at)
  	VALUES (1,"Suryank","Mishra","suryankmishra70@gmail.com","6386484824","$2a$10$sCfUaiFnCPy3Dns85L.nXOdC1TqTmq2EZAx3JNXry4lDCpWUQY7Ia","2024-08-13","2024-08-13");
INSERT INTO  public.users_role(  user_id,role_id )VALUES(1,1);
COMMIT;

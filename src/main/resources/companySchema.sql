--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-10-29 14:00:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4954 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 41216)
-- Name: budgets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.budgets (
    id integer NOT NULL,
    total_amount numeric(15,2) NOT NULL,
    spend_amount numeric(15,2) NOT NULL,
    description character varying(50) NOT NULL,
    company_id integer
);


ALTER TABLE public.budgets OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 41215)
-- Name: budgets_budget_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.budgets_budget_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.budgets_budget_id_seq OWNER TO postgres;

--
-- TOC entry 4955 (class 0 OID 0)
-- Dependencies: 218
-- Name: budgets_budget_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.budgets_budget_id_seq OWNED BY public.budgets.id;


--
-- TOC entry 220 (class 1259 OID 41227)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    client_name character varying(30) NOT NULL,
    clinet_surname character varying(30) NOT NULL,
    clinet_email text NOT NULL,
    client_birthday date NOT NULL,
    client_active boolean NOT NULL,
    company_id integer
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 41192)
-- Name: companies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companies (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    CONSTRAINT companies_name_check CHECK ((length((name)::text) > 0))
);


ALTER TABLE public.companies OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 41239)
-- Name: departments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departments (
    id integer NOT NULL,
    deparmtent_name character varying(30) NOT NULL,
    department_number integer NOT NULL,
    company_id integer NOT NULL
);


ALTER TABLE public.departments OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 41256)
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    id integer NOT NULL,
    age integer NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    birth_day date NOT NULL,
    department_id integer NOT NULL
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 41311)
-- Name: laptops; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.laptops (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    brand character varying(50) NOT NULL,
    color character varying(50) NOT NULL,
    employee_id integer
);


ALTER TABLE public.laptops OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 41344)
-- Name: offices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offices (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    capacity integer,
    address_id text NOT NULL,
    company_id integer,
    CONSTRAINT offices_capacity_check CHECK ((capacity >= 0))
);


ALTER TABLE public.offices OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 41278)
-- Name: projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.projects (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    finished boolean NOT NULL,
    department_id integer NOT NULL
);


ALTER TABLE public.projects OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 41378)
-- Name: rooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rooms (
    id bigint NOT NULL,
    capacity integer,
    available boolean NOT NULL,
    room_number character varying(50) NOT NULL,
    office_id bigint NOT NULL,
    CONSTRAINT rooms_capacity_check CHECK ((capacity >= 0))
);


ALTER TABLE public.rooms OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 41377)
-- Name: rooms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rooms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rooms_id_seq OWNER TO postgres;

--
-- TOC entry 4956 (class 0 OID 0)
-- Dependencies: 229
-- Name: rooms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rooms_id_seq OWNED BY public.rooms.id;


--
-- TOC entry 223 (class 1259 OID 41268)
-- Name: salary; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salary (
    id integer NOT NULL,
    amount numeric(15,2) NOT NULL,
    bonus numeric(15,2) NOT NULL,
    cut_percentage integer NOT NULL,
    employee_id integer NOT NULL
);


ALTER TABLE public.salary OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 41402)
-- Name: takerooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.takerooms (
    department_id integer NOT NULL,
    room_id integer NOT NULL,
    take_time timestamp without time zone NOT NULL
);


ALTER TABLE public.takerooms OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 41289)
-- Name: tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks (
    task_id integer NOT NULL,
    name character varying(100) NOT NULL,
    start_time date NOT NULL,
    end_time date NOT NULL,
    project_id integer NOT NULL
);


ALTER TABLE public.tasks OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 41288)
-- Name: tasks_task_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tasks_task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tasks_task_id_seq OWNER TO postgres;

--
-- TOC entry 4957 (class 0 OID 0)
-- Dependencies: 225
-- Name: tasks_task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tasks_task_id_seq OWNED BY public.tasks.task_id;


--
-- TOC entry 4741 (class 2604 OID 41219)
-- Name: budgets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.budgets ALTER COLUMN id SET DEFAULT nextval('public.budgets_budget_id_seq'::regclass);


--
-- TOC entry 4743 (class 2604 OID 41381)
-- Name: rooms id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rooms ALTER COLUMN id SET DEFAULT nextval('public.rooms_id_seq'::regclass);


--
-- TOC entry 4742 (class 2604 OID 41292)
-- Name: tasks task_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks ALTER COLUMN task_id SET DEFAULT nextval('public.tasks_task_id_seq'::regclass);


--
-- TOC entry 4936 (class 0 OID 41216)
-- Dependencies: 219
-- Data for Name: budgets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.budgets (id, total_amount, spend_amount, description, company_id) FROM stdin;
\.


--
-- TOC entry 4937 (class 0 OID 41227)
-- Dependencies: 220
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (id, client_name, clinet_surname, clinet_email, client_birthday, client_active, company_id) FROM stdin;
\.


--
-- TOC entry 4934 (class 0 OID 41192)
-- Dependencies: 217
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companies (id, name) FROM stdin;
\.


--
-- TOC entry 4938 (class 0 OID 41239)
-- Dependencies: 221
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departments (id, deparmtent_name, department_number, company_id) FROM stdin;
\.


--
-- TOC entry 4939 (class 0 OID 41256)
-- Dependencies: 222
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employees (id, age, name, surname, email, birth_day, department_id) FROM stdin;
\.


--
-- TOC entry 4944 (class 0 OID 41311)
-- Dependencies: 227
-- Data for Name: laptops; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.laptops (id, name, brand, color, employee_id) FROM stdin;
\.


--
-- TOC entry 4945 (class 0 OID 41344)
-- Dependencies: 228
-- Data for Name: offices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.offices (id, name, capacity, address_id, company_id) FROM stdin;
\.


--
-- TOC entry 4941 (class 0 OID 41278)
-- Dependencies: 224
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.projects (id, name, finished, department_id) FROM stdin;
\.


--
-- TOC entry 4947 (class 0 OID 41378)
-- Dependencies: 230
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rooms (id, capacity, available, room_number, office_id) FROM stdin;
\.


--
-- TOC entry 4940 (class 0 OID 41268)
-- Dependencies: 223
-- Data for Name: salary; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salary (id, amount, bonus, cut_percentage, employee_id) FROM stdin;
\.


--
-- TOC entry 4948 (class 0 OID 41402)
-- Dependencies: 231
-- Data for Name: takerooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.takerooms (department_id, room_id, take_time) FROM stdin;
\.


--
-- TOC entry 4943 (class 0 OID 41289)
-- Dependencies: 226
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tasks (task_id, name, start_time, end_time, project_id) FROM stdin;
\.


--
-- TOC entry 4958 (class 0 OID 0)
-- Dependencies: 218
-- Name: budgets_budget_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.budgets_budget_id_seq', 1, false);


--
-- TOC entry 4959 (class 0 OID 0)
-- Dependencies: 229
-- Name: rooms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rooms_id_seq', 1, false);


--
-- TOC entry 4960 (class 0 OID 0)
-- Dependencies: 225
-- Name: tasks_task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tasks_task_id_seq', 1, false);


--
-- TOC entry 4752 (class 2606 OID 41221)
-- Name: budgets budgets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.budgets
    ADD CONSTRAINT budgets_pkey PRIMARY KEY (id);


--
-- TOC entry 4754 (class 2606 OID 41233)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 4748 (class 2606 OID 41202)
-- Name: companies companies_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_name_key UNIQUE (name);


--
-- TOC entry 4750 (class 2606 OID 41200)
-- Name: companies companies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (id);


--
-- TOC entry 4756 (class 2606 OID 41243)
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);


--
-- TOC entry 4758 (class 2606 OID 41262)
-- Name: employees employees_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_email_key UNIQUE (email);


--
-- TOC entry 4760 (class 2606 OID 41260)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- TOC entry 4768 (class 2606 OID 41315)
-- Name: laptops laptops_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laptops
    ADD CONSTRAINT laptops_pkey PRIMARY KEY (id);


--
-- TOC entry 4770 (class 2606 OID 41351)
-- Name: offices offices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);


--
-- TOC entry 4764 (class 2606 OID 41282)
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (id);


--
-- TOC entry 4772 (class 2606 OID 41384)
-- Name: rooms rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);


--
-- TOC entry 4774 (class 2606 OID 41386)
-- Name: rooms rooms_room_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_room_number_key UNIQUE (room_number);


--
-- TOC entry 4762 (class 2606 OID 41272)
-- Name: salary salary_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salary
    ADD CONSTRAINT salary_pkey PRIMARY KEY (id);


--
-- TOC entry 4776 (class 2606 OID 41406)
-- Name: takerooms takerooms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.takerooms
    ADD CONSTRAINT takerooms_pkey PRIMARY KEY (department_id, room_id);


--
-- TOC entry 4766 (class 2606 OID 41294)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (task_id);


--
-- TOC entry 4777 (class 2606 OID 41222)
-- Name: budgets budgets_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.budgets
    ADD CONSTRAINT budgets_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.companies(id) ON DELETE CASCADE;


--
-- TOC entry 4778 (class 2606 OID 41234)
-- Name: clients clients_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.companies(id) ON DELETE CASCADE;


--
-- TOC entry 4779 (class 2606 OID 41244)
-- Name: departments departments_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.companies(id) ON DELETE CASCADE;


--
-- TOC entry 4780 (class 2606 OID 41263)
-- Name: employees employees_department_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_department_id_fkey FOREIGN KEY (department_id) REFERENCES public.departments(id) ON DELETE CASCADE;


--
-- TOC entry 4784 (class 2606 OID 41316)
-- Name: laptops laptops_employee_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laptops
    ADD CONSTRAINT laptops_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employees(id);


--
-- TOC entry 4785 (class 2606 OID 41352)
-- Name: offices offices_company_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_company_id_fkey FOREIGN KEY (company_id) REFERENCES public.companies(id);


--
-- TOC entry 4782 (class 2606 OID 41283)
-- Name: projects projects_department_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_department_id_fkey FOREIGN KEY (department_id) REFERENCES public.departments(id) ON DELETE CASCADE;


--
-- TOC entry 4786 (class 2606 OID 41387)
-- Name: rooms rooms_office_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_office_id_fkey FOREIGN KEY (office_id) REFERENCES public.offices(id);


--
-- TOC entry 4781 (class 2606 OID 41273)
-- Name: salary salary_employee_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salary
    ADD CONSTRAINT salary_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employees(id);


--
-- TOC entry 4787 (class 2606 OID 41412)
-- Name: takerooms takerooms_department_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.takerooms
    ADD CONSTRAINT takerooms_department_id_fkey FOREIGN KEY (department_id) REFERENCES public.departments(id);


--
-- TOC entry 4788 (class 2606 OID 41407)
-- Name: takerooms takerooms_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.takerooms
    ADD CONSTRAINT takerooms_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.rooms(id);


--
-- TOC entry 4783 (class 2606 OID 41295)
-- Name: tasks tasks_project_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_project_id_fkey FOREIGN KEY (project_id) REFERENCES public.projects(id) ON DELETE CASCADE;


-- Completed on 2025-10-29 14:00:36

--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2023-05-09 14:06:38

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 90125)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id integer NOT NULL,
    street text,
    city text,
    state text,
    zip_code integer
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 90124)
-- Name: Address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Address_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Address_id_seq" OWNER TO postgres;

--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 216
-- Name: Address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Address_id_seq" OWNED BY public.address.id;


--
-- TOC entry 215 (class 1259 OID 90116)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    name text,
    phone text,
    email text,
    address_id integer
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 90115)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO postgres;

--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 214
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- TOC entry 219 (class 1259 OID 90160)
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    number integer NOT NULL,
    date date,
    item text,
    price double precision,
    customer_id integer
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 90159)
-- Name: order_number_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_number_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_number_seq OWNER TO postgres;

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 218
-- Name: order_number_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_number_seq OWNED BY public."order".number;


--
-- TOC entry 3184 (class 2604 OID 90128)
-- Name: address id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public."Address_id_seq"'::regclass);


--
-- TOC entry 3183 (class 2604 OID 90119)
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 3185 (class 2604 OID 90163)
-- Name: order number; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order" ALTER COLUMN number SET DEFAULT nextval('public.order_number_seq'::regclass);


--
-- TOC entry 3339 (class 0 OID 90125)
-- Dependencies: 217
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id, street, city, state, zip_code) FROM stdin;
2	Panda st.	Austin	Texas	14520
6	Bear st.	LA	California	14520
8	Tube Street	Portland	Oregon	87123
9	Ribbon Street	Tokyo	Japan	71234
4	Stone Street	Seattle	Washington	52525
\.


--
-- TOC entry 3337 (class 0 OID 90116)
-- Dependencies: 215
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (id, name, phone, email, address_id) FROM stdin;
6	Mary	987654321	mary@cpp.edu	6
2	Fred	123456789	fred@cpp.edu	2
8	Mark	19239123	mark@cpp.edu	8
4	MaryAnn	987654321	maryAnn@cpp.edu	4
9	Ben	821712312	ben@cpp.edu	9
\.


--
-- TOC entry 3341 (class 0 OID 90160)
-- Dependencies: 219
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."order" (number, date, item, price, customer_id) FROM stdin;
4	2023-10-10	Caesar Salad	10.91	2
5	2021-09-12	Caesar Salad	10.1	4
2	1969-12-31	Caesar Salad	5.18	6
8	2022-03-21	Greek Salad	8.62	9
6	2020-07-17	Cobb Salad	7.86	8
\.


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 216
-- Name: Address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Address_id_seq"', 11, true);


--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 214
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 11, true);


--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 218
-- Name: order_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_number_seq', 13, true);


--
-- TOC entry 3189 (class 2606 OID 90132)
-- Name: address Address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT "Address_pkey" PRIMARY KEY (id);


--
-- TOC entry 3187 (class 2606 OID 90148)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 3191 (class 2606 OID 90167)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (number);


--
-- TOC entry 3192 (class 2606 OID 90149)
-- Name: customer fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk FOREIGN KEY (address_id) REFERENCES public.address(id) NOT VALID;


--
-- TOC entry 3193 (class 2606 OID 90168)
-- Name: order fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk FOREIGN KEY (customer_id) REFERENCES public.customer(id);


-- Completed on 2023-05-09 14:06:38

--
-- PostgreSQL database dump complete
--


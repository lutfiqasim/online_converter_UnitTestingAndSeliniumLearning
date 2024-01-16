--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: currency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.currency (
    code character varying(255) NOT NULL,
    name character varying(255),
    number_of_decimals integer NOT NULL,
    seperator character(1) NOT NULL
);


ALTER TABLE public.currency OWNER TO postgres;

--
-- Name: exchange_rate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exchange_rate (
    id bigint NOT NULL,
    exchange_rate double precision NOT NULL,
    from_currency_name character varying(255) NOT NULL,
    to_currency_name character varying(255) NOT NULL
);


ALTER TABLE public.exchange_rate OWNER TO postgres;

--
-- Name: exchange_rate_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.exchange_rate_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.exchange_rate_id_seq OWNER TO postgres;

--
-- Name: exchange_rate_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.exchange_rate_id_seq OWNED BY public.exchange_rate.id;


--
-- Name: exchange_rate id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exchange_rate ALTER COLUMN id SET DEFAULT nextval('public.exchange_rate_id_seq'::regclass);


--
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.currency (code, name, number_of_decimals, seperator) FROM stdin;
AED	UAE Dirham	2	,
AFN	Afghan Afghani	2	,
ALL	Albanian Lek	2	,
AMD	Armenian Dram	2	,
ANG	Netherlands Antillian Guilder	2	,
AOA	Angolan Kwanza	2	,
ARS	Argentine Peso	2	,
AWG	Aruban Florin	2	,
AZN	Azerbaijani Manat	2	,
BAM	Bosnia and Herzegovina Mark	2	,
BBD	Barbados Dollar	2	,
BDT	Bangladeshi Taka	2	,
BWP	Botswana pula	2	.
BGN	Bulgarian Lev	2	,
BHD	Bahraini Dinar	3	,
BIF	Burundian Franc	0	,
BMD	Bermudian Dollar	2	,
BND	Brunei Dollar	2	.
BOB	Bolivian Boliviano	2	,
BRL	Brazilian Real	2	,
BSD	Bahamian Dollar	2	,
BTN	Bhutanese Ngultrum	2	,
BYN	Belarusian Ruble	2	,
BZD	Belize Dollar	2	,
CAD	Canadian Dollar	2	.
CDF	Congolese Franc	2	,
CHF	Swiss Franc	2	,
CLP	Chilean Peso	0	,
CNY	Chinese Renminbi	2	.
COP	Colombian Peso	2	,
CRC	Costa Rican Colon	2	,
CUP	Cuban Peso	2	,
CVE	Cape Verdean Escudo	2	,
CZK	Czech Koruna	2	,
DJF	Djiboutian Franc	0	,
DKK	Danish Krone	2	,
DOP	Dominican Peso	2	.
DZD	Algerian Dinar	2	,
EGP	Egyptian Pound	2	,
ERN	Eritrean Nakfa	2	,
ETB	Ethiopian Birr	2	,
EUR	Euro	2	.
FJD	Fiji Dollar	2	,
FKP	Falkland Islands Pound	2	,
FOK	Faroese Króna	2	,
GBP	Pound Sterling	2	.
GEL	Georgian Lari	2	,
GGP	Guernsey Pound	2	,
GHS	Ghanaian Cedi	2	,
GIP	Gibraltar Pound	2	,
GMD	Gambian Dalasi	2	,
GNF	Guinean Franc	0	,
GTQ	Guatemalan Quetzal	2	.
GYD	Guyanese Dollar	2	,
HKD	Hong Kong Dollar	2	.
HNL	Honduran Lempira	2	,
HRK	Croatian Kuna	2	,
HTG	Haitian Gourde	2	,
HUF	Hungarian Forint	2	,
IDR	Indonesian Rupiah	2	,
ILS	Israeli New Shekel	2	.
IMP	Manx Pound	2	,
INR	Indian Rupee	2	.
IQD	Iraqi Dinar	3	,
IRR	Iranian Rial	2	,
ISK	Icelandic Króna	0	,
JEP	Jersey Pound	2	,
JMD	Jamaican Dollar	2	,
JOD	Jordanian Dinar	3	,
JPY	Japanese Yen	0	.
KES	Kenyan Shilling	2	.
KGS	Kyrgyzstani Som	2	,
KHR	Cambodian Riel	2	,
KID	Kiribati Dollar	2	,
KMF	Comorian Franc	0	,
KRW	South Korean Won	0	.
KWD	Kuwaiti Dinar	3	,
KYD	Cayman Islands Dollar	2	,
KZT	Kazakhstani Tenge	2	,
LAK	Lao Kip	2	,
LBP	Lebanese Pound	2	.
LKR	Sri Lanka Rupee	2	.
LRD	Liberian Dollar	2	,
LSL	Lesotho Loti	2	,
LYD	Libyan Dinar	3	,
MAD	Moroccan Dirham	2	,
MDL	Moldovan Leu	2	,
MGA	Malagasy Ariary	2	,
MKD	Macedonian Denar	2	,
MMK	Burmese Kyat	2	,
MNT	Mongolian Tögrög	2	,
MOP	Macanese Pataca	2	,
MRU	Mauritanian Ouguiya	2	,
MUR	Mauritian Rupee	2	,
MVR	Maldivian Rufiyaa	2	,
MWK	Malawian Kwacha	2	,
MXN	Mexican Peso	2	.
MYR	Malaysian Ringgit	2	.
MZN	Mozambican Metical	2	,
NAD	Namibian Dollar	2	,
NGN	Nigerian Naira	2	.
NIO	Nicaraguan Córdoba	2	.
NOK	Norwegian Krone	2	,
NPR	Nepalese Rupee	2	.
NZD	New Zealand Dollar	2	.
OMR	Omani Rial	3	,
PAB	Panamanian Balboa	2	,
PEN	Peruvian Sol	2	,
PGK	Papua New Guinean Kina	2	,
PHP	Philippine Peso	2	.
PKR	Pakistani Rupee	2	.
PLN	Polish Złoty	2	,
PYG	Paraguayan Guarani	0	,
QAR	Qatari Riyal	2	,
RON	Romanian Leu	2	,
RSD	Serbian Dinar	2	,
RUB	Russian Ruble	2	,
RWF	Rwandan Franc	0	,
SAR	Saudi Riyal	2	,
SBD	Solomon Islands Dollar	2	,
SCR	Seychellois Rupee	2	,
SDG	Sudanese Pound	2	,
SEK	Swedish Krona	2	,
SGD	Singapore Dollar	2	.
SHP	Saint Helena Pound	2	,
SLL	Sierra Leonean Leone	2	,
SOS	Somali Shilling	2	,
SRD	Surinamese Dollar	2	,
SSP	South Sudanese Pound	2	,
STN	São Tomé and Príncipe Dobra	2	,
SYP	Syrian Pound	2	,
SZL	Eswatini Lilangeni	2	,
THB	Thai Baht	2	.
TJS	Tajikistani Somoni	2	,
TMT	Turkmenistan Manat	2	,
TND	Tunisian Dinar	3	,
TOP	Tongan Pa'anga	2	,
TRY	Turkish Lira	2	,
TTD	Trinidad and Tobago Dollar	2	,
TVD	Tuvaluan Dollar	2	,
TWD	New Taiwan Dollar	2	.
TZS	Tanzanian Shilling	2	.
UAH	Ukrainian Hryvnia	2	,
UGX	Ugandan Shilling	0	.
USD	United States Dollar	2	.
UYU	Uruguayan Peso	2	,
UZS	Uzbekistani So'm	2	,
VES	Venezuelan Bolívar Soberano	2	,
VND	Vietnamese Đồng	0	,
VUV	Vanuatu Vatu	0	,
WST	Samoan Tala	2	,
XAF	Central African CFA Franc	0	,
XCD	East Caribbean Dollar	2	,
XDR	Special Drawing Rights	2	,
XOF	West African CFA franc	0	,
XPF	CFP Franc	0	,
YER	Yemeni Rial	2	,
ZAR	South African Rand	2	,
ZMW	Zambian Kwacha	2	,
\.


--
-- Data for Name: exchange_rate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exchange_rate (id, exchange_rate, from_currency_name, to_currency_name) FROM stdin;
1	3.6725	USD	AED
2	3.6818	USD	ILS
3	0.709	USD	JOD
4	30.8417	USD	EGP
5	30.8417	USD	ERN
6	1.4902	USD	AUD
7	1.3354	USD	CAD
8	0.9142	USD	EUR
9	0.7872	USD	GBP
10	144.7989	USD	JPY
11	1315.4298	USD	KRW
12	83.18	USD	INR
13	16.9386	USD	MXN
14	7.1566	USD	CNY
15	4.8918	USD	BRL
\.


--
-- Name: exchange_rate_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exchange_rate_id_seq', 15, true);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (code);


--
-- Name: exchange_rate exchange_rate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exchange_rate
    ADD CONSTRAINT exchange_rate_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


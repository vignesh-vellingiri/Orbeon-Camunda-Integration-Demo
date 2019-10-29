-- Drop table

-- DROP TABLE public.orbeon_meta_data;

CREATE TABLE public.orbeon_meta_data (
	documentid varchar(50) NULL,
	username varchar(50) NULL,
	"data" varchar(100) NULL,
	camunda_id varchar(200) NULL,
	status varchar(50) NULL,
	column_1 varchar(200) NULL,
	column_2 varchar(200) NULL,
	column_3 varchar(200) NULL,
	column_4 varchar(200) NULL
);

-- Permissions

ALTER TABLE public.orbeon_meta_data OWNER TO postgres;
GRANT ALL ON TABLE public.orbeon_meta_data TO postgres;


CREATE SEQUENCE public.seq_tb_perfil_co_seq_perfil;

CREATE TABLE public.TB_PERFIL (
                CO_SEQ_PERFIL INTEGER NOT NULL DEFAULT nextval('public.seq_tb_perfil_co_seq_perfil'),
                NO_PERFIL VARCHAR(255) NOT NULL,
                DS_PERFIL VARCHAR(1000) NOT NULL,
                DT_HORA_INCLUSAO TIMESTAMP NOT NULL,
                DT_HORA_ALTERACAO TIMESTAMP,
                CONSTRAINT co_seq_perfil PRIMARY KEY (CO_SEQ_PERFIL)
);


ALTER SEQUENCE public.seq_tb_perfil_co_seq_perfil OWNED BY public.TB_PERFIL.CO_SEQ_PERFIL;

CREATE SEQUENCE public.seq_tb_endereco;

CREATE TABLE public.TB_ENDERECO (
                CO_SEQ_ENDERECO INTEGER NOT NULL DEFAULT nextval('public.seq_tb_endereco'),
                 CO_SEQ_PESSOA BIGINT NOT NULL,
                DS_LOGRADOURO VARCHAR(150) NOT NULL,
                DS_COMPLEMENTO VARCHAR(150) NOT NULL,
                DS_BAIRRO VARCHAR(250) NOT NULL,
                DS_CIDADE VARCHAR NOT NULL,
                CO_UF VARCHAR NOT NULL,
                DS_CEP VARCHAR(8) NOT NULL,
                CONSTRAINT seq_endereco PRIMARY KEY (CO_SEQ_ENDERECO)
);


CREATE SEQUENCE public.seq_tb_pessoa_co_seq_pessoa;

CREATE TABLE public.TB_PESSOA (
                CO_SEQ_PESSOA BIGINT NOT NULL DEFAULT nextval('public.seq_tb_pessoa_co_seq_pessoa'),
                NO_NOME VARCHAR(400) NOT NULL,
                DS_EMAIL VARCHAR(200) NOT NULL,
                DT_NASCIMENTO date not null ,
                ST_PESSOA BOOLEAN NOT NULL,
                CONSTRAINT co_seq_pessoa PRIMARY KEY (CO_SEQ_PESSOA)
);


ALTER SEQUENCE public.seq_tb_pessoa_co_seq_pessoa OWNED BY public.TB_PESSOA.CO_SEQ_PESSOA;

CREATE INDEX in_tb_pessoa_no_nome
 ON public.TB_PESSOA
 ( NO_NOME ASC );

CREATE UNIQUE INDEX in_tp_pessoa_ds_email
 ON public.TB_PESSOA
 ( DS_EMAIL );

CREATE SEQUENCE public.seq_tb_pessoa_perfil_co_seq_pessoal_perfil;

CREATE TABLE public.TB_PESSOA_PERFIL (
                CO_SEQ_PESSOAL_PERFIL INTEGER NOT NULL DEFAULT nextval('public.seq_tb_pessoa_perfil_co_seq_pessoal_perfil'),
                CO_SEQ_PESSOA BIGINT NOT NULL,
                CO_SEQ_PERFIL INTEGER NOT NULL,
                CONSTRAINT co_seq_pessoa_perfil PRIMARY KEY (CO_SEQ_PESSOAL_PERFIL)
);



ALTER TABLE public.TB_PESSOA_PERFIL ADD CONSTRAINT fk_tb_perfil_tb_usuario_perfi_fk
FOREIGN KEY (CO_SEQ_PERFIL)
REFERENCES public.TB_PERFIL (CO_SEQ_PERFIL)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE public.TB_PESSOA_PERFIL ADD CONSTRAINT fk_tb_pessoa_tb_pessoa_perfil
FOREIGN KEY (CO_SEQ_PESSOA)
REFERENCES public.TB_PESSOA (CO_SEQ_PESSOA)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE public.TB_ENDERECO ADD CONSTRAINT fk_tb_pessoa_tb_enderenco
FOREIGN KEY (CO_SEQ_PESSOA)
REFERENCES public.TB_PESSOA (CO_SEQ_PESSOA)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

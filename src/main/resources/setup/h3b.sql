create or replace function fn_audit_trigger() 
returns trigger 
as $BODY$
    begin
        if (tg_op = 'INSERT') then
		
			if(NEW.creationTime is null) then
				NEW.creationTime = current_timestamp;
			end if;
			
			if(NEW.userCreation is null) then
				NEW.userCreation = user;
			end if;            
            return NEW;
        elsif (tg_op = 'UPDATE') then
			
			if(NEW.creationTime is null) then
				NEW.creationTime = OLD.creationTime;
			end if;
			
			if(NEW.userCreation is null) then
				NEW.userCreation = OLD.userCreation;
			end if;            
		
            if(NEW.updatedTime is null) then
				NEW.updatedTime = current_timestamp;
			end if;
			
			if(NEW.userUpdated is null) then
				NEW.userUpdated = user;
			end if;
			return NEW;
        end if;
    end;
$BODY$ language plpgsql;

create table if not exists tb_bcc_index(
	code integer,
	name varchar(50) not null,
	variation integer not null,
	description varchar(500),
	creationTime TIMESTAMPTZ not null,
	userCreation varchar(50) not null,	
	updatedTime TIMESTAMPTZ,
	userUpdated varchar(50)	
);

alter table tb_bcc_index add constraint pk_tb_bc_index primary key (code);
alter table tb_bcc_index add constraint ck_variation check (variation in (1,2,3,4));

create trigger tg_bccIndex
before insert or update on tb_bcc_index
    for each row execute procedure fn_audit_trigger();
	
comment on table tb_bcc_index is 'Reference index codes from Brazilian Central Bank';
comment on column tb_bcc_index.code  is 'Index code';
comment on column tb_bcc_index.name  is 'Index name (CDI / IPCA / IGP-M ...)';
comment on column tb_bcc_index.variation  is 'Variation Type - 1-Dailly | 2-Weekly | 3-Monthly | 4-Annual';
comment on column tb_bcc_index.description  is 'Index Description';
comment on column tb_bcc_index.creationTime  is 'Date and time when the record was created';
comment on column tb_bcc_index.userCreation  is 'The user that created the record';
comment on column tb_bcc_index.updatedTime  is 'Date and time when the record was updated';
comment on column tb_bcc_index.userUpdated  is 'The user that updated the record';



insert into tb_bcc_index values (12,'CDI-D',1,'CERTIFICADOS DE DEPOSITO INTERBANCARIO - Diario',current_timestamp,user);

insert into tb_bcc_index values (188,'INPC',2,'INDICE NACIONAL DE PREÇOS AO CONSUMIDOR - verifica a variação do custo médio das famílias com rendimento familiar médio entre 1 e 5 salários mínimos. Indica as variações de preços nos grupos mais sensíveis, que gastam todo rendimento em consumo corrente (alimentação, remédio, etc.).',current_timestamp,user);

insert into tb_bcc_index values (189,'IGP-M',2,'INDICE GERAL DE PREÇOS DO MERCADO - Verifica preços do comércio no atacado, no varejo e na construção civil, pesquisados entre o dia 21 do mês anterior e 20 do mês de referência. É usado na correção de contratos de aluguel e tarifas de serviços públicos',current_timestamp,user);

insert into tb_bcc_index values (196,'Poupança',2,'CADERNETA DE POUPANCA',current_timestamp,user);

insert into tb_bcc_index values (433,'IPCA',2,'INDICE NACIONAL DE PREÇOS AO CONSUMIDOR-AMPLO - Calculado pelo IBGE, aponta mensalmente a variação do custo de vida médio de famílias com renda mensal entre 1 e 40 salários mínimos das 11 principais regiões metropolitanas do país. Os preços são coletados em mais de 28 mil comércios visitados pelos pesquisadores.',current_timestamp,user);

insert into tb_bcc_index values (4391,'CDI-M',2,'CERTIFICADOS DE DEPÓSITO INTERBANCÁRIO - Acumulada no mês',current_timestamp,user);
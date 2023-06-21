--create table doctor (
--	doctor_id bigserial primary key,
--	unic_guid varchar(20),
--	name_university varchar(30),
--	start_training date,
--	end_training date,
--	specialization varchar(30),
--	work_experience int
--);

create table doctor (
	doctor_id bigserial primary key,
	fio text,
	name_university text,
	specialization text,
	work_experience date
);

create table medicament (
	medicament_id bigserial primary key,
	medicament_name text, 
	name_substance text,
	recomendation text
);

create table note (
	note_id bigserial primary key,
	dose_medicament text,
	start_medication date,
	end_medication date,
	interval_medication date,
	doctor_id bigint not null references doctor(doctor_id),
	medication_id bigint not null references medicament(medicament_id)
);

INSERT INTO public.doctor
(unic_guid, start_training, end_training, specialization, work_experience)
VALUES('hex123', '12-12-2012', '12-12-2021', 'Проктолог', 9);

INSERT INTO public.medicament
(medicament_name, recomendation)
VALUES('Jopavin', 'Внутривенно');

INSERT INTO public.note
(dose_medicament, start_medication, end_medication, interval_medication, doctor_id, medication_id)
VALUES('50 гр', '12-12-2012', '12-12-2021', '1-1-1', 1, 1);

INSERT INTO public.note
(dose_medicament, start_medication, end_medication, interval_medication, doctor_id, medication_id)
VALUES('50 гр', '12-12-2012', '12-12-2021', '1-1-1', 1, 1);




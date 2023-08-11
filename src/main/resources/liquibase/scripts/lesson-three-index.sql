-- liquibase formatted sql

-- changeset d1m_k0:1
create index student_name_index on student (name);

-- changeset d1m_k0:2
create index faculty_name_color_index on faculty (name, color);
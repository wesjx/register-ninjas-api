-- Migration to add column of RANK in register table

ALTER TABLE tb_register_ninjas
ADD COLUMN rank VARCHAR(255);
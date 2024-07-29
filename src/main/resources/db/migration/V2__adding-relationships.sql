CREATE TABLE IF NOT EXISTS user_role (
    fk_user_id BIGINT NOT NULL,
    fk_role_id BIGINT NOT NULL,

    PRIMARY KEY (fk_user_id, fk_role_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (fk_user_id) REFERENCES tb_user(id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (fk_role_id) REFERENCES tb_role(id)
);

ALTER TABLE tb_record ADD COLUMN fk_user_id BIGINT;
ALTER TABLE tb_record ADD CONSTRAINT fk_record_user FOREIGN KEY (fk_user_id) REFERENCES tb_user(id);

ALTER TABLE tb_record ADD COLUMN fk_project_id BIGINT;
ALTER TABLE tb_record ADD CONSTRAINT fk_record_project FOREIGN KEY (fk_project_id) REFERENCES tb_project(id);
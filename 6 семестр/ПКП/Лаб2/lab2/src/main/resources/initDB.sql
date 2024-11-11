CREATE TABLE IF NOT EXISTS department
(
    id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    countStaff INT NOT NULL ,
    phone VARCHAR(20)  NOT NULL,
    rooms VARCHAR(50) NOT NULL
);



DROP TABLE IF EXISTS notification;

CREATE TABLE notification
(
    customer_id VARCHAR(20) PRIMARY KEY NOT NULL
);

DROP TABLE IF EXISTS notification_record;

CREATE TABLE notification_record
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(20) NOT NULL,
    mode        VARCHAR(10) NOT NULL,
    template    VARCHAR(20) NOT NULL,
    sent        BOOLEAN     NOT NULL,
    date        timestamp   NOT NULL,
    foreign key (customer_id) references notification(customer_id)
);

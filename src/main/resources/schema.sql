DROP TABLE IF EXISTS notification;

CREATE TABLE notification (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id VARCHAR(20) NOT NULL,
  mode VARCHAR(10) NOT NULL,
  template VARCHAR(20) NOT NULL,
  sent BOOLEAN NOT NULL,
  date timestamp NOT NULL
);

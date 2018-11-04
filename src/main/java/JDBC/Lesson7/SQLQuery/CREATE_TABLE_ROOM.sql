CREATE TABLE ROOM(
ID NUMBER PRIMARY KEY,
NUMBER_OF_GUESTS NUMBER,
PRICE NUMBER,
BREAKFAST_INCLUDED NUMBER,
PETS_ALLOWED NUMBER,
DATE_AVAILABLE_FORM TIMESTAMP,
HOTEL NUMBER,
CONSTRAINT HOTEL_FK FOREIGN KEY (HOTEL) REFERENCES HOTEL(ID)
);
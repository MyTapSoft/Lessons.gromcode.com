ELECT SALESMAN.NAME, SALESMAN.CITY, CUSTOMER.CUSTNAME, CUSTOMER.CITY FROM SALESMAN
JOIN CUSTOMER ON SALESMAN.CITY = CUSTOMER.CITY;

SELECT SALESMAN.NAME, SALESMAN.CITY, CUSTOMER.CUSTNAME, CUSTOMER.CITY
FROM SALESMAN, CUSTOMER
WHERE SALESMAN.CITY = CUSTOMER.CITY;
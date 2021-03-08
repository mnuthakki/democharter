
--Customer table inserts
INSERT INTO Customer (`Customer_ID`,`First_Name`,`Last_Name`,`Zip_Code`) VALUES (1,'User1','LastName1','12345');
INSERT INTO Customer (`Customer_ID`,`First_Name`,`Last_Name`,`Zip_Code`) VALUES (2,'User2','LastName2','98765');
INSERT INTO Customer (`Customer_ID`,`First_Name`,`Last_Name`,`Zip_Code`) VALUES (3,'User3','LastName3','56734');
INSERT INTO Customer (`Customer_ID`,`First_Name`,`Last_Name`,`Zip_Code`) VALUES (4,'User4','LastName4','87654');

--Transaction table inserts
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (1,220.22,'2021-02-03 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (2,75.00,'2021-01-03 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (3,550.00,'2021-01-24 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (4,40.00,'2021-02-15 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (5,440.00,'2020-11-15 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (6,1189.11,'2020-12-22 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (7,21111.99,'2021-03-03 00:00:00',1);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (8,134.89,'2021-03-02 00:00:00',2);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (9,22.00,'2021-01-15 00:00:00',2);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (10,765.88,'2021-02-22 00:00:00',2);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (11,167.98,'2020-11-30 00:00:00',2);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (12,5456.99,'2021-01-23 00:00:00',3);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (13,234.00,'2020-12-23 00:00:00',3);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (14,88.00,'2021-02-28 00:00:00',3);
INSERT INTO Transaction (`Transaction_ID`,`Transaction_Amount`,`Transaction_Date`,`Customer_ID`) VALUES (15,97.00,'2021-02-03 00:00:00',4);
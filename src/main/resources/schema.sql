--Customer Table Create Statement
CREATE TABLE Customer (
                            `Customer_ID` int NOT NULL,
                            `First_Name` varchar(45) DEFAULT NULL,
                            `Last_Name` varchar(45) NOT NULL,
                            `Zip_Code` varchar(45) NOT NULL,
                            PRIMARY KEY (`Customer_ID`),
                            UNIQUE KEY `CustomerID_UNIQUE` (`Customer_ID`)
);

--Transaction Table Create Statement
CREATE TABLE Transaction (
                               `Transaction_ID` int NOT NULL AUTO_INCREMENT,
                               `Transaction_Amount` decimal(20,2) NOT NULL,
                               `Transaction_Date` datetime NOT NULL,
                               `Customer_ID` int DEFAULT NULL,
                               PRIMARY KEY (`Transaction_ID`),
                               FOREIGN KEY (`Customer_ID`) REFERENCES `Customer` (`Customer_ID`)
);
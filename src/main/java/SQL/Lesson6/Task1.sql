CREATE TABLE Suppliers(
SupplierID NUMBER,
CONSTRAINT Supplier_PK PRIMARY KEY(SupplierID),
CompanyName NVARCHAR2(50),
ContactName NVARCHAR2(50),
ContactTitle NVARCHAR2(50),
Address NVARCHAR2(100),
Region NVARCHAR2(50),
PostalCode NUMBER,
Country NVARCHAR2(50),
Phone NUMBER,
Fax NUMBER,
HomePage NVARCHAR2(50)
);

CREATE TABLE Categories(
CategoryID NUMBER,
CONSTRAINT Categories_PK PRIMARY KEY(CategoryID),
CategoryName NVARCHAR2(50),
Description NVARCHAR2(150),
Picture NVARCHAR2(200)
);

CREATE TABLE Products(
ProductID NUMBER,
CONSTRAINT Product_PK PRIMARY KEY(ProductID),
ProductName NVARCHAR2(50),
SupplierID NUMBER,
CONSTRAINT Supplier_FK FOREIGN KEY(SupplierID) REFERENCES Suppliers(SupplierID),
CategoryID NUMBER,
CONSTRAINT Category_FK FOREIGN KEY(CategoryID) REFERENCES Categories(CategoryID),
QuantityPerUnit NUMBER,
UnitPrice NUMBER,
UnitsInStock NUMBER,
UnitsOnOrder NUMBER,
RecorderLevel NUMBER,
Discountinued NUMBER
);

CREATE TABLE Snippers(
SnipperID NUMBER,
CONSTRAINT Snipper_PK PRIMARY KEY (SnipperID),
CompanyName NVARCHAR2(50),
Phone NUMBER
);

CREATE TABLE Employees(
EmployeeID NUMBER,
CONSTRAINT Employee_PK PRIMARY KEY(EmployeeID),
LastName NVARCHAR2(25),
FirstName NVARCHAR2(25),
Title NVARCHAR2(50),
TitleOfCourtesy NVARCHAR2(50),
BirthDade TIMESTAMP,
HireDate TIMESTAMP,
Address NVARCHAR2(50),
City NVARCHAR2(50),
Region NVARCHAR2(30),
PostalCode NUMBER,
Country NVARCHAR2(30),
HomePhone NUMBER,
Extension NUMBER,
Photo NVARCHAR2(150),
Notes NVARCHAR2(250),
ReportsTo NUMBER
);

CREATE TABLE Customers(
CistomerID NUMBER,
CONSTRAINT Customer_PK PRIMARY KEY(CistomerID),
CompanyName NVARCHAR2(50),
ContactName NVARCHAR2(50),
ContactTitle NVARCHAR2(50),
Address NVARCHAR2(50),
City NVARCHAR2(50),
Region NVARCHAR2(50),
PostalCode NUMBER,
Country NVARCHAR2(50),
Phone NUMBER,
Fax Number
);

CREATE TABLE Orders(
OrderID NUMBER,
CONSTRAINT Order_PK PRIMARY KEY(OrderID),
CustomerID NUMBER,
CONSTRAINT Customer_FK FOREIGN KEY(CustomerID) REFERENCES Customers(CistomerID),
EmployeeID NUMBER,
CONSTRAINT EmployeeFK FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
OrderDate TIMESTAMP,
RequairedDate TIMESTAMP,
SnippedDate NUMBER,
CONSTRAINT Snipper_FK FOREIGN KEY (SnippedDate) REFERENCES Snippers(SnipperID),
ShipVia NVARCHAR2(50),
Freight NVARCHAR2(50),
ShipName NVARCHAR2(50),
ShipAddress NVARCHAR2(50),
ShipCity NVARCHAR2(50),
ShipRegion NVARCHAR2(50),
ShipPostalCode NUMBER,
ShipCountry NVARCHAR2(50)
);

CREATE TABLE OrderDetails(
OrderID NUMBER,
CONSTRAINT Order_FK FOREIGN KEY(OrderID) REFERENCES Orders(OrderID),
ProductID NUMBER,
CONSTRAINT Product_FK FOREIGN KEY(OrderID) REFERENCES Products(ProductID),
UnitPrice NUMBER,
QUANTITY NUMBER,
Discount NUMBER
);
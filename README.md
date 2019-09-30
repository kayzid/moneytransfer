Money Transfer Service using Micronaut Framework. 
The service runs in an embedded jetty server. To run the application simply downlod and do a mvn clean compile install.
The Service supports operations to Add an account, fetch an account and perform money transfer between accounts and within the same account.

Create Account:
POST http://localhost:8080/v1/accounts
RQ Body:
{
	"AccountDetails":{
		"AccountHolderFirstName":"Test",
		"AccountHolderLastName":"Test",
		"AccountHolderAddress":"USA",
		"AccountBalance":100.0
	},
	"AccountType":"Savings",
	"LoginIPAddress":"192.168.1.1"
}


RS:
Response Code: 201
{
    "accountId": "b005d166-e721-48d0-96e0-c74ed86205af",
    "accountHolderFirstName": "Test",
    "accountHolderLastName": "Test",
    "accountBalance": 100.0,
    "accountLastLoginDate": 1569826632937,
    "accountType": "Savings",
    "accountLastLoginIPAddress": "192.168.1.1",
    "createdDate": 1569826632937,
    "updatedDate": 1569826632937
}

Get Account by Id
GET http://localhost:8080/v1/accounts/94cc1144-2cf4-4f67-90b3-dcce9a1cc35c
RS:
{
    "accountId": "94cc1144-2cf4-4f67-90b3-dcce9a1cc35c",
    "accountHolderFirstName": "Test",
    "accountHolderLastName": "Test",
    "accountBalance": 100.0,
    "accountLastLoginDate": 1569826572538,
    "accountType": "Savings",
    "accountLastLoginIPAddress": "192.168.1.1",
    "createdDate": 1569826572538,
    "updatedDate": 1569826661073
}

Will thrown 404 NOT Found incase account is not present.

Perform Transfer Between two accounts:
POST http://localhost:8080/v1/transfers
{
	"OriginAccountId":"94cc1144-2cf4-4f67-90b3-dcce9a1cc35c",
	"DestinationAccountId":"b005d166-e721-48d0-96e0-c74ed86205af",
	"Amount":200,
	"Currency":"USD"
}

RS:
{
    "message": "Transfer completed successfully"
}

RS if not enough blaance for a transfer:
HttpStatus: 403 Forbidden
{
    "Message": "Transaction cannot be completed. Not enough balance.",
    "Code": "ET403"
}

Deposit or Debit money from same account:
POST http://localhost:8080/v1/transfers
{
	"OriginAccountId":"94cc1144-2cf4-4f67-90b3-dcce9a1cc35c",
	"Amount":200,
	"Currency":"USD",
	"TransferType":"Credit",
	"SelfTransfer":true
}

RS:
{
    "message": "Transfer completed successfully"
}

{
	"OriginAccountId":"94cc1144-2cf4-4f67-90b3-dcce9a1cc35c",
	"Amount":200,
	"Currency":"USD",
	"TransferType":"Debit",
	"SelfTransfer":true
}

RS:
{
    "message": "Transfer completed successfully"
}


The service currently has minimal input validation by design as the idea was to keep the api simple for now. 

The data is being stored in in memory h2 database. The DBMS backed operation assumes pessimistic concurrency.


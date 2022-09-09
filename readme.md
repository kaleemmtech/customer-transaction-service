1. POST /api/transaction - Executes a new transaction

URL: http://localhost:8080/api/transaction
headers:
content-type: application/json;
accept: application/json;

payload:
{
"sender":"xyz@coforge.com",
"receiver":"abc@coforge.com",
"amount": 200.0
}

response:
{"id":"e7b480b2-56ed-4e6f-b831-b4f83e0adcad"}

2. GET http://localhost:8080/api/transactions/xyz@coforge.com

headers:
content-type: application/json;
accept: application/json;

response:
[
{
"senderId": "xyz@coforge.com",
"receiverId": "abc@coforge.com",
"amout": 200,
"id": "4711b8cd-31ad-4c5c-8840-30fdc2474f31"
},
{
"senderId": "xyz@coforge.com",
"receiverId": "abc@coforge.com",
"amout": 100,
"id": "5bc3747d-5ee5-45a2-a619-6b0f9aebff37"
},
{
"senderId": "xyz@coforge.com",
"receiverId": "abc@coforge.com",
"amout": 50,
"id": "b2c24f8c-d634-4415-b3bc-a4fb5238bd95"
}
]

3. Get http://localhost:8080/api/transactions
   headers:
   content-type: application/json;
   accept: application/json;
   response:
   [
   {
   "senderId": "umar@coforge.com",
   "receiverId": "abc@coforge.com",
   "amout": 200,
   "id": "e7b480b2-56ed-4e6f-b831-b4f83e0adcad"
   },
   {
   "senderId": "xyz@coforge.com",
   "receiverId": "abc@coforge.com",
   "amout": 200,
   "id": "4711b8cd-31ad-4c5c-8840-30fdc2474f31"
   },
   {
   "senderId": "xyz@coforge.com",
   "receiverId": "abc@coforge.com",
   "amout": 100,
   "id": "5bc3747d-5ee5-45a2-a619-6b0f9aebff37"
   },
   {
   "senderId": "xyz@coforge.com",
   "receiverId": "abc@coforge.com",
   "amout": 50,
   "id": "b2c24f8c-d634-4415-b3bc-a4fb5238bd95"
   },
   {
   "senderId": "123@coforge.com",
   "receiverId": "abc@coforge.com",
   "amout": 50,
   "id": "b8ff3904-fc67-43ad-be54-e96d64b9f932"
   }
   ]

# Spring Boot 2, Restful Api with HATEOAS using Data-Jpa and H2 DB implementation example

Example of RESTFul HATEOAS services using Spring Boot 2, Data-Jpa, H2

REST OPERATIONS EXAMPLES:

Using Postman below are a sample of the operations performed:

   GET localhost:8080/payments 

      returns:

{
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/payments"
        }
    ],
    "data": []
}


POST  localhost:8080/payments 
 specifying the following JSON data in the Body of the post request

 {
            "type": "Davide",
            "version": 0,
            "organization_id": "IE-977"
        }

Will save the following data in the Database (H2, in memory)

        {
            "id": "fd94e97a-86ea-47e1-9825-3e8a5fb1e265",
            "type": "Davide",
            "version": 0,
            "organization_id": "IE-977"
        }

PUT localhost:8080/payments/fd94e97a-86ea-47e1-9825-3e8a5fb1e265 
  specifying the following JSON in the Body of the put request

        {
	  "id": "fd94e97a-86ea-47e1-9825-3e8a5fb1e265",
            "type": "Davide modified",
            "version": 0,
            "organization_id": "IE-977"
        }

will update the data present in the DB for the specific id: 
					 fd94e97a-86ea-47e1-9825-3e8a5fb1e265

NOTE: the update requires that all the fields present in the DB for that record to be sent, to allow the data not specified to be merged additional logic would need to be added to the implementation.

DELETE localhost:8080/payments/fd94e97a-86ea-47e1-9825-3e8a5fb1e265 

delete the Payment for the specific id fd94e97a-86ea-47e1-9825-3e8a5fb1e265 

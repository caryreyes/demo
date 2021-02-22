# demo

This is a simple RESTful Web Service using Java and Springboot. 
The service provides an HTTP GET request at http://localhost:8080/api/weather?city=?
The response is a simple weather info for the particular city in json format as shown below:

{
    "weather": {
        "id": "803",
        "main": "Clouds",
        "description": "broken clouds",
        "icon": "04d"
    }
}

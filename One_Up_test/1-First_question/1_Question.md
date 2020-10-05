# Exercise 1: TV Series REST API

Create a public RESTful API. This REST API is going to be a wrapper of the TVDB API.
You can use a framework like Spring (or not), you can follow [this boilerplate](https://spring.io/guides/gs/rest-service/) if you want to.    
The implementation is up to you, use your best judgment to make this API efficient and fast.  
The API will contain 2 endpoints. Each endpoint needs to call the TVDB API.  
The TVDB API required an access_token. To obtain this access_token, you need to call this TVDB endpoint first:
 https://api.thetvdb.com/swagger#!/Authentication/post_login with the credentials available at the end of the document.  

Here are the specifications of what you need to write:  

**GET /series/search**  
The search endpoint will call the https://api.thetvdb.com/swagger#!/Search/get_search_series endpoint of the TVDB API and return the results.

Query parameters:
- `name`: represents the name of the series

Response:
- an array of objects. Each object represents a series and contains their `id`, `seriesName`, `overview` and
 `banner URI`. You have to create a custom object, returning the response of TVDB API is not enough.

```JSON
[
    {
    "id": 73244,
    "seriesName": "The Office (US)",
    "overview": "A fresh and funny mockumentary-style glimpse into the daily interactions of the eccentric workers at the Dunder Mifflin paper supply company. This fast-paced comedy parodies contemporary American water-cooler culture.",
    "banner": "/banners/graphical/73244-g9.jpg"
    },
    ...
]
```
Errors:
- returns a 404 if the series is not found
- returns a 401 if the TVDB REST API credentials are not valid
- returns a 500 if something went wrong

**GET /series/{id}**  
The endpoint will call the https://api.thetvdb.com/swagger#!/Series/get_series_id endpoint of the TVDB API and return the results.

Query parameters:
- `id`: represents the TVDB series id

Response:
- An object containing detailed information about the TV series. You have to create a custom object, 
returning the response of TVDB API is not enough.

```JSON
{
  "banner": "graphical/73244-g9.jpg",
  "fanart": "fanart/original/73244-32.jpg",
  "firstAired": "2005-03-24",
  "genre": [
    "Comedy"
  ],
  "id": 73244,
  "network": "NBC",
  "overview": "A fresh and funny mockumentary-style glimpse into the daily interactions of the eccentric workers at the Dunder Mifflin paper supply company. This fast-paced comedy parodies contemporary American water-cooler culture.",
  "poster": "posters/73244-1.jpg",
  "season": "9",
  "seriesName": "The Office (US)",
  "siteRating": 8.9,
  "siteRatingCount": 9021,
  "status": "Ended"
}
```

Errors:
- returns a 404 if the series is not found
- returns a 401 if the TVDB REST API credentials are not valid
- returns a 500 if something went wrong

If you have time, consider implementing unit tests. If you have more time, consider implementing pagination.  

To retrieve information about the series, you will have to use the TVDB REST API.  

Here is the swagger for the TVDB REST API (v3.0.0): https://api.thetvdb.com/swagger#/ and
here are the credentials:
```JSON
{
  "apikey": "4f61cb9c2f9e437b40d6c7d7832dcd0c",
  "userkey": "5EAB0AE802E413.00848705",
  "username": "oneup-interview"
}
```

*Suggested effort: 100 minutes*
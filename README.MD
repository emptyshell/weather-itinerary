# Weather-Itinerary

1. Create an html (or Java Swing) page with an input control to enter a name of the city and
date, ‘Submit’ button next to it, and a table below to display results. The table should have
the following columns: City Name, Country Code, Temperature, and Clouds - or similar
columns if those are not available in API.
2. Check out the http://openweathermap.org/current and familiarise yourself with the
weather API.
3. Obtain a free API key at https://openweathermap.org/price - this should give you access to 5
day forecast - https://openweathermap.org/forecast5
4. Program the ‘Submit’ button to invoke openweathermar.org API and retrieve the 5-day
weather forecast details for the city specified in the input control. Please use ‘metric’ units,
see ‘Units Format’ section at the bottom of the page.
5. Program should be adding weather details to the table created. Each time the user clicks
‘Submit’ button a new record should be added for the whole city to the table. Sometimes
openweather.org API would return more than one record. This is the case if there is more
than one city with the same name. Please add all records between 12pm and 6pm to the
table.
6. Please store the retrieved weather forecast in an in-memory database on the back-end. Each
time a request is made it should check with the in-memory database, and only if the record
is not already in cache, to query the API. Otherwise it should return the results from the
cache. Cache should expire in 1 hour, records older than that should be queried from the
API.
7. Add functionality to save the itinerary with some name and start a new itinerary.
8. Add functionality to retrieve the saved itinerary from the database.
9. Test that the user can add those 3 cities – Tokyo, Moscow and London and the system can
retrieve the weather forecast. Test that this scenario can be saved and retrieved back.
10. `Bonus task.` Add a ‘Generate Summary’ button. When pressed the software should analyse
the temperature and clouds/rain and recommend ‘Please take a coat’ if temperature is
below 5C, ‘Please take an umbrella’ if there is any rain forecast.
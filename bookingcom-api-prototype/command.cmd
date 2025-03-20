//Voor locatie:Locatie


Input locatie amsterdam
curl --request GET
	--url 'https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?query=london'
	--header 'x-rapidapi-host: booking-com15.p.rapidapi.com'
	--header 'x-rapidapi-key: 3ab405fed0msh0d94904ed446f58p155f23jsn6fc0bfb7477e'

Output

0:
dest_id:"-2601889"
search_type:"city"
lc:"en"
label:"London, England, United Kingdom"
cc1:"gb"
hotels:14522
type:"ci"
region:"England"
longitude:-0.12763432
name:"London"
latitude:51.50739
city_name:"London"
roundtrip:"GhAxYjQ3ODdjZWI0NDUwNDNkIAAoATICZW46BmxvbmRvbkAASgBQAA=="
city_ufi:null
country:"United Kingdom"
image_url:"https://cf.bstatic.com/xdata/images/city/150x150/977261.jpg?k=6e056b414cda72f979d7227aff6f5cb43035a30555649dce0292bae146ba4d57&o="
nr_hotels:14522
dest_type:"city"

1:
dest_id:"2280"
search_type:"district"
nr_hotels:5445
dest_type:"district"
city_name:"London"
roundtrip:"GhAxYjQ3ODdjZWI0NDUwNDNkIAEoATICZW46BmxvbmRvbkAASgBQAA=="
city_ufi:-2601889
country:"United Kingdom"
image_url:"https://cf.bstatic.com/xdata/images/district/150x150/56431.jpg?k=c580405cbf1e9c2cf17fd39015b08d13a04589a72afab80bb9419b6bfcebdd51&o="
type:"di"
hotels:5445
longitude:-0.13493559
region:"England"
latitude:51.507282
name:"Central London"
lc:"en"
label:"Central London, London, England, United Kingdom"
cc1:"gb"

2:
dest_id:"4"
search_type:"airport"
longitude:-0.452693
region:"England"
type:"ai"
hotels:110
latitude:51.4713
name:"London Heathrow Airport"
lc:"en"
cc1:"gb"
label:"London Heathrow Airport, London, England, United Kingdom"
dest_type:"airport"
nr_hotels:110
city_ufi:-2601889
country:"United Kingdom"
roundtrip:"GhAxYjQ3ODdjZWI0NDUwNDNkIAIoATICZW46BmxvbmRvbkAASgBQAA=="
city_name:"London"
image_url:"https://cf.bstatic.com/static/img/plane-100.jpg"

and more


//Voor startDatum:Datum eindDatum:Datum

Input dest_id=-2601889 arrival_date=18-11-2025 departure_date=27-11-2025
curl --request GET
	--url 'https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels?dest_id=929&search_type=CITY&arrival_date=2025-03-22&departure_date=2025-03-23&adults=1&children_age=0%2C17&room_qty=1&page_number=1&units=metric&temperature_unit=c&languagecode=en-us&currency_code=AED'
	--header 'x-rapidapi-host: booking-com15.p.rapidapi.com'
	--header 'x-rapidapi-key: 3ab405fed0msh0d94904ed446f58p155f23jsn6fc0bfb7477e'

Output

hotels:
0:
    hotel_id:5930526
    accessibilityLabel:"Locke at Broken Wharf. 4 out of 5 stars. 8.9 Excellent 1274 reviews. ‎City of London‬ • ‎2.2 km from downtown‬. Private suite : 1 bed. Original price 21433 AED. Current price 8345 AED.. Includes taxes and fees."
1:
   hotel_id:36845
   accessibilityLabel:"The Dilly. 5 out of 5 stars. 8.3 Very Good 11016 reviews. ‎Westminster Borough‬ • ‎650 m from downtown‬. Hotel room : 2 beds. 23409 AED. Includes taxes and fees."


Prijs is min en max, in plaats van vaste prijs in reis


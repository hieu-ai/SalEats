
# SalEats

# Topics Covered

## HTML/CSS | Databases/JDC/SQL | Java Servlets | AJAX | JSP

## Introduction


Just in time! Magnificent job with the successful launch of the SalEats driver scheduling
system, and as a result you have received special blessings from the director. “Perhaps it’s
time to do something more modern?” Honestly, it’s not such a bad idea given the amount of
success you’ve had. Don’t resist the modern call of duty - embrace it.
This time around, the Council of SAL is looking to expand their product line and make their
new product more up-to-date and user-friendly.


## Overview
Making a web application that builds on the Yelp API. The program will allow users to search for restaurants, add
them to their list of favorites, and schedule reservations for these restaurants on their
personal Google calendars. Use a variety of different APIs, as well
as implement a database to keep track of user information.
The rest of this document includes mockups of the web pages.


## Google Sign-in API
In addition to allowing the user to create an account on your web app, program will also
allow users to sign in with their Google account. In other words, users will have two different
login options, as shown in ​ **Exhibit 3.** ​ You can learn more about the Google Sign-in API here:
https://developers.google.com/identity/sign-in/web/sign-in​.
Google Calendar API


If users are logged in with their Google credentials, they can also make a reservation to the
restaurant by adding it as an event on their Google calendar, as seen in ​ **Exhibit 6** ​. More
information on the Google Calendar API here:
https://developers.google.com/calendar/overview​.


## Google Maps API
When querying a search, users will have to type in the latitude and longitude, or instead use
the Google Maps API to generate a map overview that can populate coordinates. An
example of this is shown in ​ Exhibit 2

more about the API here:
https://developers.google.com/maps/documentation/javascript/tutorial​.
Please keep in mind that you may have to generate API keys for the Google-affiliated APIs,
so​ 


## MySQL
In this assignment, you will also need to track user information. You should construct a
simple MySQL database that stores usernames, passwords, emails, user favorites, etc. for
each user. You will need to display this data on the Favorites page, shown in ​ **Exhibit 8** ​.
Home Page


**Exhibit 1 Home Page (landing page)**


This is the landing page for your web assignment. Users can run a search by entering a
restaurant name and coordinates, selecting the order of the search results (using the radio
buttons), and then clicking on the magnifying glass icon. The user can also use the Google
Maps button to open a Google Maps overlay, shown in ​ **Exhibit 2** ​, and select a location from
the map for the coordinates. A successful search leads the user to the Search Results Page,
as shown in ​ **Exhibit 4** ​.


Users can use the top menu bar to navigate between the Home Page by clicking on “Home,”
and the Login/Signup Page by clicking on “Login / Sign Up.” If the user is already logged in,
the menu bar should instead contain “Home,” “Favorites,” and “Logout.” This menu bar
should persist between all pages of this application.
Clicking on the logo (SalEats!) should redirect the user to the Home Page as well. This
should apply to all the pages in this assignment.
**Exhibit 2 Home Page (Google Maps overlay)**
The user arrives at this page after clicking on the Google Maps button from ​ **Exhibit 1** ​. The
user can click any location on the map, and doing so will populate the latitude and longitude
fields with the latitude and longitude of the selected location. For example, if the user clicks
on the Los Angeles area, the latitude and longitude could be populated with 34.02116 and
-118.287132 respectively.


## Login/Signup Page
**Exhibit 3 Login/Signup Page**
Users can sign up for a new account, or login with a pre-existing account on this page.
When signing up, the user will enter their information in the required fields (be sure to
display an appropriate error message if there are missing or improperly formatted fields).
Upon a successful sign up, the user should automatically be logged in and redirect back to
the Home Page (​ **Exhibit 1** ​).
Here are some possible errors to check for:
● Data is missing
● Data is malformed (i.e. Email does not contain an @ or end in .com, .net, .edu, etc.)
● There is already an account associated with the email
On the other side, users can choose to login either with their credentials or with Google
Signin. Again, errors should be displayed appropriately upon missing or improperly
formatted fields. Upon a successful login, the user is redirected back to the Home Page
(​ **Exhibit 1** ​).


## Search Results Page
**Exhibit 4 Search Results Page**
The user arrives at this page after a successful query from the Home Page. Using the
information from the Yelp API, your program should display the 10 best results based on the
search query and sorting option. Each row of information contains an image related to the
restaurant, the restaurant name, the restaurant address, and a link to their Yelp page.
Clicking on the image redirects the user to the Details Page for that restaurant (​ **Exhibit 5** ​).


## Details Page
**Exhibit 5 Details Page**
This page contains additional details about the restaurant that was selected from the Search
Results Page. This page should contain the restaurant name, address, same image from the
Search Results Page, phone number, cuisine type, price (in dollar signs), as well as star
rating. Clicking on the image now redirects the user to the Yelp page of the restaurant.
Additionally, underneath the image should be two buttons: “Add to Favorites” and “Add
Reservation.” The former button will display either “Add to Favorites” or “Remove from
Favorites” depending on whether the restaurant is already in the user’s favorites list or not.
Once the button is clicked and the restaurant is added to the favorites list, the text and
functionality should change without needing to refresh the entire webpage.


**Exhibit 6 Details Page (Add Reservation)**
Clicking on the “Add Reservation” button will generate an error message if the user is not
logged in with their Google account, and no additional effects should occur. If the user ​ _is_
logged in with the Google account, then the appropriate text fields should display
underneath the button without needing to refresh the entire webpage. The user can select a
date on the calendar, enter in a time, as well as any additional notes for the reservation. The
reservation is added to the user’s Google Calendar when they click on the “Submit
Reservation” button. ​ **Exhibit 7** ​displays what the calendar dropdown should look like when
the user clicks on the “Date” field.


**Exhibit 7 Details Page (Calendar)**
The calendar can be generated with the default HTML datepicker, so don’t worry if the
styling of your calendar is not exactly the same as the one in the mock up. If any of the fields
are missing information, be sure to print out the appropriate error message.


## Favorites Page
**Exhibit 8 Favorites Page**
The user can access their Favorites Page using the menu bar at the top of each page. The
favorites are displayed chronologically (most recent first) by default, but the user can change
the sorting order by using the “Sort by” dropdown menu on the page. Selecting a new sort
order should reorder the favorites list without needing to refresh the entire page.
Alphabetical is based on the name of the restaurant, rating is based on the star rating of the
restaurant, and recency is based on the time the restaurant was added to the favorites list.
The user should also be able to navigate to the Details Page (​ **Exhibit 5** ​) of the restaurant by
clicking on the image associated with that restaurant.





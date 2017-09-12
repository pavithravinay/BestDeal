Project: BestDeal
Author: Pavithra Vinay


About this application:
-This application is built using Servlets, html, CSS, Mysql, MongoDb.
-New class called “DealMatchesUtilities.java” has been added, it has all the functionalities related to deal match feature implementation.
-When BestDeal app is started by Tomcat server, it will read ANY 2 Tweets/lines from DealMatches.txt file and display them on BestDeal app homepage along with links to the individual products that BestDeal app can match of the offered/displayed deals by BestBuy on the homepage of BestDeal app.
-All user transactions, info and products are stored in Mysql database.
-Review are stored in MongoDb
-When app server restarts, products table in database will be repopulated based on product.xml file

Note:
“credentials.txt”, BestBuyDealMatches.ipynb”, “DealMatches.txt” files can be found in the directory csj/DealMatch

To compile:
javac -cp .:/Library/Tomcat/lib/servlet-api.jar:/Library/Tomcat/lib/mongo-java-driver-3.2.2.jar:/Library/Tomcat/lib/mysql-connector-java-5.1.40-bin.jar: /Library/Tomcat/webapps/csj/WEB-INF/classes/*.java


To run this application:

1.Make sure to place ‘csj’ folder in “Tomcat/webapps/“ directory.
2.Make sure Apache Tomcat, mySQL and Mongo db are up and running.
3.Go to browser, type “localhost/csj”, it will display the home page
4.Two Tweets from dealMatches.txt file will be displayed along with matching products.
5. Products matching is based on alphabetical order on “model” field of Products table.
6. Delete the product which is being displayed on homepage to see other tweets and matching products on homepage





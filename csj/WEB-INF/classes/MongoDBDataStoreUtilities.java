import java.util.logging.*;
import java.util.HashMap;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.MongoException;
import com.mongodb.AggregationOutput;

import java.util.List;
import java.util.Set;

public class MongoDBDataStoreUtilities {

	static DBCollection myreviews = null;

	public static Boolean storeReview(Reviews reviewIn) {

		// Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
		// " Logging from storeReview method " + reviewIn.toString());

		// HashMap<String, ArrayList<Reviews>> reviews = new HashMap<String,
		// ArrayList<Reviews>>();

		// try {
		// // Select all the existing reviews;
		// reviews = MongoDBDataStoreUtilities.selectReview();
		// } catch (Exception ex) {
		// Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.SEVERE,
		// ex.getMessage(),
		// ex.getStackTrace());
		// return false;
		// }

		// // If there are no reviews for the input product
		// if (!reviews.containsKey(reviewIn.getProductModelName())) {
		// ArrayList<Reviews> arr = new ArrayList<Reviews>();
		// reviews.put(reviewIn.getProductModelName(), arr);
		// Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
		// reviewIn.getProductModelName());
		// }

		// ArrayList<Reviews> reviewList =
		// reviews.get(reviewIn.getProductModelName());
		// reviewList.add(reviewIn);

		try {
			MongoDBDataStoreUtilities.insertReview(reviewIn);
		} catch (Exception ex) {
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.SEVERE, ex.getMessage(),
					ex.getStackTrace());
			return false;
		}

		return true;

	}

	public static LinkedHashMap<String, Float> mostLikedProducts() {
		getConnection();

		// DBObject match = new BasicDBObject("$match",new BasicDBObject());
		DBObject groupFields = new BasicDBObject("_id", 0);
		groupFields.put("_id", "$productName");
		groupFields.put("average", new BasicDBObject("$avg", "$reviewRating"));
		DBObject group = new BasicDBObject("$group", groupFields);

		DBObject sort = new BasicDBObject();
		sort.put("average", -1);
		DBObject orderby = new BasicDBObject("$sort", sort);

		DBObject limit = new BasicDBObject("$limit", 5);

		AggregationOutput output = myreviews.aggregate(group, orderby, limit);

		// Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
		// "total output : " + output.toString());
		LinkedHashMap<String, Float> productList = new LinkedHashMap<String, Float>();

		for (final DBObject result : output.results()) {
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
					"individual output " + result.toString());
			productList.put(result.get("_id").toString(), Float.parseFloat(result.get("average").toString()));
		}

		return productList;

	}

	public static LinkedHashMap<String, Integer> top5zipcodes() {
		getConnection();

		BasicDBObject query = new BasicDBObject();
		query.put("retailerZip", new BasicDBObject("$ne", null));
		query.put("retailerZip", new BasicDBObject("$ne", ""));

		DBObject match = new BasicDBObject("$match", query);

		DBObject groupFields = new BasicDBObject("_id", 0);
		groupFields.put("_id", "$retailerZip");
		groupFields.put("count", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);

		DBObject sort = new BasicDBObject();
		sort.put("count", -1);
		DBObject orderby = new BasicDBObject("$sort", sort);

		DBObject limit = new BasicDBObject("$limit", 5);

		AggregationOutput output = myreviews.aggregate(match, group, orderby, limit);

		Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO, "total output : " + output.toString());
		LinkedHashMap<String, Integer> productList = new LinkedHashMap<String, Integer>();

		for (final DBObject result : output.results()) {
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
					"individual output " + result.toString());
			productList.put(result.get("_id").toString(), Integer.parseInt(result.get("count").toString()));
		}

		return productList;

	}

	public static LinkedHashMap<String, Integer> mostReviewedProducts() {
		getConnection();

		LinkedHashMap<String, ArrayList<Reviews>> reviewHashmap = new LinkedHashMap<String, ArrayList<Reviews>>();

		// DBObject match = new BasicDBObject("$match",new BasicDBObject());
		DBObject groupFields = new BasicDBObject("_id", 0);
		groupFields.put("_id", "$productName");
		groupFields.put("count", new BasicDBObject("$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);

		DBObject sort = new BasicDBObject();
		sort.put("count", -1);
		DBObject orderby = new BasicDBObject();
		orderby = new BasicDBObject("$sort", sort);

		DBObject limit = new BasicDBObject("$limit", 5);

		AggregationOutput output = myreviews.aggregate(group, orderby, limit);

		Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO, "total output : " + output.toString());
		LinkedHashMap<String, Integer> productList = new LinkedHashMap<String, Integer>();

		for (final DBObject result : output.results()) {
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
					"individual output " + result.toString());
			productList.put(result.get("_id").toString(), Integer.parseInt(result.get("count").toString()));
		}

		return productList;

	}

	public static ArrayList<Reviews> dataAnalytics(String productName, String category, String zip, String city,
			String state, String productOnSale, String manufacturer, String users, String gender, String occupation,
			Integer rating, String reviewText, Double price, Integer age, String priceCriteria, String ageCriteria,
			String ratingCriteria) {
		getConnection();

		ArrayList<Reviews> reviewList = new ArrayList<Reviews>();

		BasicDBObject query = new BasicDBObject();
		if (productName != null && !productName.equals("*")) {
			query.put("productName", new BasicDBObject("$eq", productName));
		}
		if (category != null && !category.equals("*")) {
			query.put("productType", new BasicDBObject("$eq", category));
		}
		if (zip != null && !zip.equals("*")) {
			query.put("retailerZip", new BasicDBObject("$eq", zip));
		}
		if (city != null && !city.equals("*")) {
			query.put("retailerCity", new BasicDBObject("$eq", city));
		}
		if (state != null && !state.equals("*")) {
			query.put("retailerState", new BasicDBObject("$eq", state));
		}
		if (productOnSale != null && !productOnSale.equals("*")) {
			query.put("productOnSale", new BasicDBObject("$eq", productOnSale));
		}
		if (manufacturer != null && !manufacturer.equals("*")) {
			query.put("manufacturerName", new BasicDBObject("$eq", manufacturer));
		}
		if (users != null && !users.equals("*")) {
			query.put("userName", new BasicDBObject("$eq", users));
		}
		if (gender != null && !gender.equals("*")) {
			query.put("gender", new BasicDBObject("$eq", gender));
		}
		if (occupation != null && !occupation.equals("*")) {
			query.put("userOccupation", new BasicDBObject("$eq", occupation));
		}
		if (rating != null) {
			if (ratingCriteria != null) {
				switch (ratingCriteria) {
				case "eq":
					query.put("reviewRating", new BasicDBObject("$eq", rating));
					break;
				case "gt":
					query.put("reviewRating", new BasicDBObject("$gt", rating));
					break;
				case "lt":
					query.put("reviewRating", new BasicDBObject("$lt", rating));
					break;

				}
			}
			
		}
		if (reviewText != null) {
			query.put("reviewText", java.util.regex.Pattern.compile(reviewText));
		}
		if (price != null) {
			if (priceCriteria != null) {
				switch (priceCriteria) {
				case "eq":
					query.put("productPrice", new BasicDBObject("$eq", price));
					break;
				case "gt":
					query.put("productPrice", new BasicDBObject("$gt", price));
					break;
				case "lt":
					query.put("productPrice", new BasicDBObject("$lt", price));
					break;

				}
			}

		}
		if (age != null) {
			if (ageCriteria != null) {
				switch (ageCriteria) {
				case "eq":
					query.put("userAge", new BasicDBObject("$eq", age));
					break;
				case "gt":
					query.put("userAge", new BasicDBObject("$gt", age));
					break;
				case "lt":
					query.put("userAge", new BasicDBObject("$lt", age));
					break;

				}
			}
			
		}
		// DBObject groupFields = new BasicDBObject("_id", 0);
		// if(groupByOption!=null){
			 
		// 	groupFields.put("_id", groupByOption);
		// 	groupFields.put("count", new BasicDBObject("$sum", 1));
			

		// }
		// DBObject group = new BasicDBObject("$group", groupFields);

		DBObject match = new BasicDBObject("$match", query);

		AggregationOutput output = myreviews.aggregate(match);

		// if(countOrDetail!=null && countOrDetail.equals("count")){
		// 	for (final DBObject result : output.results()) {
		// 	// Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
		// 	// "data analytics individual output " + result.toString());
		// 	Reviews review = new Reviews();

		// 	switch(groupByOption){
		// 		case "productName":
		// 			review.setProductModelName((String) result.get("productName"));
		// 			break;
		// 		case "productType":
		// 		review.setProductCategory((String) result.get("productType"));
		// 			break;
		// 		case "productPrice":
		// 		review.setProductPrice((Double) result.get("productPrice"));
		// 		break;
		// 		case "retailerName":
		// 		review.setRetaileName((String) result.get("retailerName"));
		// 		break;
		// 		case "retailerZip":
		// 		review.setRetailerZip((String) result.get("retailerZip"));
		// 		break;
		// 		case "retailerCity":
		// 		review.setRetailerCity((String) result.get("retailerCity"));
		// 		break;
		// 		case "retailerState":
		// 		review.setRetailerState((String) result.get("retailerState"));
		// 		break;
		// 		case "productOnSale":
		// 		review.setProductOnSale((String) result.get("productOnSale"));
		// 		break;
		// 		case "manufacturerName":

		// 		review.setManufacturerName((String) result.get("manufacturerName"));
		// 		break;
		// 		case "manufacturerRebate":
		// 		review.setManufacturerRebate((String) result.get("manufacturerRebate"));
		// 		break;
		// 		case "userName":
		// 		review.setUserId((String) result.get("userName"));
		// 		break;
		// 		case "userAge":
		// 		review.setUserAge((Integer) result.get("userAge"));
		// 		break;
		// 		case "gender":
		// 		review.setUserGender((String) result.get("gender"));
		// 		break;
		// 		case "userOccupation":
		// 		review.setOccupation((String) result.get("userOccupation"));
		// 		break;
		// 		case "reviewRating":
		// 		review.setRating((Integer) result.get("reviewRating"));
		// 		break;
		// 		case "reviewDate":
		// 		review.setReviewDate((String) result.get("reviewDate"));
		// 		break;
		// 		case "reviewText":
		// 		review.setReviewText((String) result.get("reviewText"));
		// 		break;
		// 		default:
		// 		review.setCount((Integer)result.get("count"));
		// 		break;

		// 	}
			
			
		// 	reviewList.add(review);
		// }

		// }
		// else {


		for (final DBObject result : output.results()) {
			// Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
			// "data analytics individual output " + result.toString());
			Reviews review = new Reviews();
			review.setProductModelName((String) result.get("productName"));
			review.setProductCategory((String) result.get("productType"));
			review.setProductPrice((Double) result.get("productPrice"));
			review.setRetaileName((String) result.get("retailerName"));
			review.setRetailerZip((String) result.get("retailerZip"));
			review.setRetailerCity((String) result.get("retailerCity"));
			review.setRetailerState((String) result.get("retailerState"));
			review.setProductOnSale((String) result.get("productOnSale"));

			review.setManufacturerName((String) result.get("manufacturerName"));
			review.setManufacturerRebate((String) result.get("manufacturerRebate"));
			review.setUserId((String) result.get("userName"));
			review.setUserAge((Integer) result.get("userAge"));
			review.setUserGender((String) result.get("gender"));
			review.setOccupation((String) result.get("userOccupation"));
			review.setRating((Integer) result.get("reviewRating"));
			review.setReviewDate((String) result.get("reviewDate"));
			review.setReviewText((String) result.get("reviewText"));
			reviewList.add(review);
		}
	//}

		return reviewList;

	}

	public static HashMap<String, ArrayList<Reviews>> selectReview() {
		getConnection();
		HashMap<String, ArrayList<Reviews>> reviewHashmap = new HashMap<String, ArrayList<Reviews>>();

		DBCursor cursor = myreviews.find();

		while (cursor.hasNext()) {
			BasicDBObject obj = (BasicDBObject) cursor.next();
			if (!reviewHashmap.containsKey(obj.getString("productName"))) {
				ArrayList<Reviews> arr = new ArrayList<Reviews>();
				reviewHashmap.put(obj.getString("productName"), arr);
			}
			ArrayList<Reviews> listReview = reviewHashmap.get(obj.getString("productName"));
			Reviews review = new Reviews(obj.getString("productName"), obj.getString("productType"),
					obj.getDouble("productPrice"), obj.getString("retailerName"), obj.getString("retailerZip"),
					obj.getString("retailerCity"), obj.getString("retailerState"), obj.getString("productOnSale"),
					obj.getString("manufacturerName"), obj.getString("manufacturerRebate"), obj.getString("userName"),
					obj.getInt("userAge"), obj.getString("gender"), obj.getString("userOccupation"),
					obj.getInt("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"));

			listReview.add(review);
		}
		return reviewHashmap;
	}

	public static void insertReview(Reviews reviewIn) {
		getConnection();
		BasicDBObject doc = new BasicDBObject("title", "myreviews").append("userName", reviewIn.getUserId())
				.append("productName", reviewIn.getProductModelName())
				.append("productType", reviewIn.getProductCategory()).append("productPrice", reviewIn.getProductPrice())
				.append("retailerName", reviewIn.getRetaileName()).append("retailerZip", reviewIn.getRetailerZip())
				.append("retailerCity", reviewIn.getRetailerCity()).append("retailerState", reviewIn.getRetailerState())
				.append("productOnSale", reviewIn.getProductOnSale())
				.append("manufacturerName", reviewIn.getManufacturerName())
				.append("manufacturerRebate", reviewIn.getManufacturerRebate()).append("userAge", reviewIn.getUserAge())
				.append("gender", reviewIn.getUserGender()).append("userOccupation", reviewIn.getOccupation())
				.append("reviewRating", reviewIn.getRating()).append("reviewDate", reviewIn.getReviewDate())
				.append("reviewText", reviewIn.getReviewText());

		myreviews.insert(doc);

	}

	public static void getConnection() {
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// MongoClient mongoClient = new MongoClient( "localhost" );

			DB db = mongoClient.getDB("reviews");
			myreviews = db.getCollection("myreviews");

			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO, "Successfully connected to mongodb");

		} catch (Exception ex) {
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.SEVERE, "Failed to connect to mongodb",
					ex.getStackTrace());
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.SEVERE, ex.getMessage(),
					ex.getStackTrace());
		}
	}

}
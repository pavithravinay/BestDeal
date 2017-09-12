

public class Reviews {
	private String productModelName;
	private String productCategory;
	private double productPrice;
	private String retaileName;
	private String retailerZip;
	private String retailerCity;
	private String retailerState;
	private String productOnSale;
	private String manufacturerName;
	private String manufacturerRebate;
	private String userId;
	private int userAge;
	private String userGender;
	private String occupation;
	private Integer rating;
	private String reviewDate;
	private String reviewText;
	private Integer count;

	public Reviews(){}

	public Reviews(String productModelName, String productCategory, double productPrice, String retaileName,
			String retailerZip, String retailerCity, String retailerState, String productOnSale,
			String manufacturerName, String manufacturerRebate, String userId, int userAge, String userGender,
			String occupation, Integer rating, String reviewDate, String reviewText) {
		
		this.productModelName = productModelName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.retaileName = retaileName;
		this.retailerZip = retailerZip;
		this.retailerCity = retailerCity;
		this.retailerState = retailerState;
		this.productOnSale = productOnSale;
		this.manufacturerName = manufacturerName;
		this.manufacturerRebate = manufacturerRebate;
		this.userId = userId;
		this.userAge = userAge;
		this.userGender = userGender;
		this.occupation = occupation;
		this.rating = rating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
		
	}

	public Reviews(String productModelName, String productCategory, double productPrice, String retaileName,
			String retailerZip, String retailerCity, String retailerState, String productOnSale,
			String manufacturerName, String manufacturerRebate, String userId, int userAge, String userGender,
			String occupation, Integer rating, String reviewDate, String reviewText, Integer count) {
		
		this.productModelName = productModelName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.retaileName = retaileName;
		this.retailerZip = retailerZip;
		this.retailerCity = retailerCity;
		this.retailerState = retailerState;
		this.productOnSale = productOnSale;
		this.manufacturerName = manufacturerName;
		this.manufacturerRebate = manufacturerRebate;
		this.userId = userId;
		this.userAge = userAge;
		this.userGender = userGender;
		this.occupation = occupation;
		this.rating = rating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
		this.count = count;
		
	}

	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count = count;
	}
	public String getProductModelName() {
		return productModelName;
	}
	public void setProductModelName(String productModelName) {
		this.productModelName = productModelName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getRetaileName() {
		return retaileName;
	}
	public void setRetaileName(String retaileName) {
		this.retaileName = retaileName;
	}
	public String getRetailerZip() {
		return retailerZip;
	}
	public void setRetailerZip(String retailerZip) {
		this.retailerZip = retailerZip;
	}
	public String getRetailerCity() {
		return retailerCity;
	}
	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}
	public String getRetailerState() {
		return retailerState;
	}
	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}
	public String getProductOnSale() {
		return productOnSale;
	}
	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}
	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String toString() {
		return "Employee [productModelName=" + productModelName + ", productCategory=" + productCategory
				+ ", productPrice=" + productPrice + ", retaileName=" + retaileName + ", retailerZip=" + retailerZip
				+ ", retailerCity=" + retailerCity + ", retailerState=" + retailerState + ", productOnSale="
				+ productOnSale + ", manufacturerName=" + manufacturerName + ", manufacturerRebate="
				+ manufacturerRebate + ", userId=" + userId + ", userAge=" + userAge + ", userGender=" + userGender
				+ ", occupation=" + occupation + ", rating=" + rating + ", reviewDate=" + reviewDate + ", reviewText="
				+ reviewText + "]";
	}
}
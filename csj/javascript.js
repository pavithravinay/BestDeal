function init() {
	completeField = document.getElementById("searchId");
	completeTable = document.getElementById("completeTable");
	autoRow = document.getElementById("autoRow");
}

function doCompletion() {
	var url = "AutoCompleteServlet?action=complete&searchId=" + escape(searchId.value);
	req = initRequest();
	req.open("GET", url, true);
	req.send();
	req.onreadystatechange = callback;
}

function initRequest() {
	if (window.XMLHttpRequest) {
		if (navigator.userAgent.indexOf('MSIE') != -1) {
			isIE = true; 
		}
		return new XMLHttpRequest();
	 } 
	 else if (window.ActiveXObject) {
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP"); 
	}
}

function appendProduct(productModel,productId, productBrandName) { 
	var row;
	var cell;
	var linkElement;
	isIE = false; 
	if (isIE) {
		completeTable.style.display = 'block';
		row = completeTable.insertRow(completeTable.rows.length);
		cell = row.insertCell(0);
	} 
	else {
		completeTable.style.display = 'table'; 
		completeTable.style.background = "#eee";
		completeTable.style.width = "180px";
		row = document.createElement("tr"); 
		cell = document.createElement("td"); 
		row.appendChild(cell); 
		completeTable.appendChild(row);
	}
	cell.className = "popupCell";
	linkElement = document.createElement("a");
	linkElement.className = "popupItem";
	linkElement.setAttribute("href", "SingleProductDisplayServlet?model=" + productModel); 
	// linkElement.appendChild(document.createTextNode(productBrandName)); 
	linkElement.appendChild(document.createTextNode(productModel)); 
	// linkElement.appendChild(document.createTextNode(productId)); 
	
	cell.appendChild(linkElement);
}

function parseMessages(responseXML) { 
	// no matches returned
	if (responseXML == null) {
		return false; 
	} 
	else {
		var products = responseXML.getElementsByTagName("products")[0]; 
		if (products.childNodes.length > 0) {
			completeTable.setAttribute("bordercolor", "black"); 
			completeTable.setAttribute("border", "1");
			for (loop = 0; loop < products.childNodes.length; loop++) {
				var product = products.childNodes[loop];
				var productModel = product.getElementsByTagName("model")[0]; 
				var productId = product.getElementsByTagName("id")[0]; 
				var productBrandName = product.getElementsByTagName("brandName")[0]; 
				appendProduct(productModel.childNodes[0].nodeValue, productId.childNodes[0].nodeValue, 
					productBrandName.childNodes[0].nodeValue); 
			}
		} 
	}
}


function callback() { 
	clearTable();
	if (req.readyState == 4) {
		if (req.status == 200) { 
			parseMessages(req.responseXML);
		} 
	}
}

function clearTable() {
	if (completeTable.getElementsByTagName("tr").length > 0) {
		completeTable.style.display = 'none';
		for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
			completeTable.removeChild(completeTable.childNodes[loop]); 
		}
	} 
}

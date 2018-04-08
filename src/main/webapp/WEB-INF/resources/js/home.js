function showTables(tableList){
	
	$.ajax({
		url: "HomeRestController/getGameTableList",
		type: "get",
		dataType: "json"
	})
	.done(tables => insertBook(tableList, tables));
}


document.addEventListener("DOMContentLoaded", function(){
	
	
	var tableList = document.querySelector("#tableList");
	
	showTables(tableList);
	
})
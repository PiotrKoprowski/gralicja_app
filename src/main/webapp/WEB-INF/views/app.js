function insertGameTable(gameTableList, gameTables){
	var id;
	gameTables.forEach( gameTable => {
		let title = document.createElement("h4");
		title.classList.add("list-group-item-heading");
		title.innerText = gameTable.tableName;
		
		let aElem = document.createElement("a");
		aElem.appendChild(title);
		aElem.classList.add("list-group-item");
		
		let divElem = document.createElement("div");
		divElem.style.display = 'none'
		aElem.appendChild(divElem);
		
		gameTableList.appendChild(aElem);
		
		id = gameTable.id
		
		var clicked = false;
		aElem.addEventListener("click", function(e){
			e.preventDefault();
			if (divElem.style.display === 'none') {
				divElem.style.display = 'block';
				if(clicked == false){
					showInfo(title, divElem, gameTable.id);
				}
				clicked = true;
//			} else {
//				divElem.style.display = 'none';
		    }
		})
	})
}


function showGameTables(gameTableList){
	console.log($('form').serialize());
	$.ajax({
		url: "http://localhost:8080/gralicja_app/homeRest/",
		type: "get",
		dataType: "json"
	})
	.done(gameTables => insertGameTable(gameTableList, gameTables));
}


document.addEventListener("DOMContentLoaded", function(){
	
	var gameTableList = document.querySelector(".list-group");

	showGameTables(gameTableList);

})






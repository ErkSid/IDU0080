var car_from_server;

function Car()
{
this.make;
this.model;
this.series;
this.year;
}

function get_cars()
{

 
$.ajaxSetup({ cache: false });
$.ajax({

    url: 'service/cars' ,
    type: "GET",
    dataType: 'json',
    success: function(data) {
    	display_cars(data);
        console.log(JSON.stringify(data));

    }
  });


}


function get_car(id)
{

 
$.ajaxSetup({ cache: false });
$.ajax({

    url: 'service/car/' + id ,
    type: "GET",
    dataType: 'json',
    success: function(data) {
    	car_from_server = data;
    	display_car(data);
        console.log(JSON.stringify(data));

    }
  });


}


function save_car()
{
	
	car_from_server.make=document.forms[0].make.value;
	car_from_server.model=document.forms[0].model.value;
	car_from_server.series=document.forms[0].series.value;
	car_from_server.year=document.forms[0].year.value;
		
var jsonData = JSON.stringify(car_from_server); 
$.ajaxSetup({ cache: false });
$.ajax({

    url: 'service/car/' + car_from_server.id ,
    type: "POST",
    data: jsonData,
    dataType: 'json',
    contentType : 'application/json',
    success: function(data) {
    	show_message("Salvestatud");
        console.log(JSON.stringify(data));

    }
  });


}

function delete_car(id) {
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : 'service/car/' + id,
		type : "DELETE",
		contentType : 'application/json',
		success : function(data) {
			show_message("Kustutatud");

			get_cars();
			console.log(JSON.stringify(data));
		}
	});
}

function add_car() {
	var car_to_server = new Car();
	car_to_server.make = document.forms[0].new_car_make.value;
	car_to_server.model = document.forms[0].new_car_model.value;
	car_to_server.series = document.forms[0].new_car_series.value;
	car_to_server.year = document.forms[0].new_car_year.value;
	var jsonData = JSON.stringify(car_to_server);
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : 'service/car/',
		type : "PUT",
		data : jsonData,
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			show_message("Auto lisatud");
			console.log(JSON.stringify(data));

			get_cars();
		}
	});
}



function display_car(car)
{
	 var out_data="";
	 out_data = out_data + "<table bgcolor=eeeeee><tr><td>Muutmine. Auto id: <b>" + car.id + "</b></td></tr>";

		out_data = out_data + "<tr><td>Mark:</td><td><input type=text name=make value='" + car.make + "'></td></tr>";
		out_data = out_data + "<tr><td>Mudel:</td><td><input type=text name=model value='" + car.model + "'></td></tr>";
		out_data = out_data + "<tr><td>Seeria:</td><td><input type=text name=series value='" + car.series + "'></td></tr>";
		out_data = out_data + "<tr><td>Aasta:</td><td><input type=text name=year value='" + car.year + "'></td></tr>";
		out_data = out_data + "<td><button type='button' class='btn'  onClick='javascript:save_car()'>Salvesta</button></td>";
		out_data = out_data + "<td><button type='button' class='btn'  onClick='javascript:delete_car("+ car.id + ")'>Kustuta</button></td></tr>";
		out_data = out_data + "</table>";

	

	
	 $("#one_car").html(out_data);
}




function display_cars(data)
{
	var out_data="";
	 out_data = out_data + "<table bgcolor=eeeeee><tr><td colspan=4>Autosid kokku: <b>" + data.length + "</b></td></tr>";
	 for(var  i in data) {
   	  var car = data[i];
		out_data = out_data + "<tr><td>mark:</td><td bgcolor=ffffff>" + car.make + "</td><td>mudel:</td><td bgcolor=ffffff>" + car.model + "</td>";
		out_data = out_data + "<td><button type='button' class='btn'  onClick='javascript:get_car(" + car.id + ")'>Vali</button></td>";
		
	 }
	 out_data = out_data + "</table>";

	
	 $("#cars_table").html(out_data);
}


function show_message(message)
{
	
	 $("#msg_text").html(message);
}

import { HttpClient, json } from 'aurelia-fetch-client'

export class foodAdd {


  foodData = {}
  foodList = []
  foodCategoryData = {}
  foodCategoryList = []
  restaurantsList = []

  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/foodCategories')
      .then(response => response.json())
      .then(foodCategories => this.foodCategoryList = foodCategories);
    client.fetch('http://localhost:8080/foods')
      .then(response => response.json())
      .then(foods => this.foodList = foods);
    client.fetch('http://localhost:8080/restaurants/1')
      .then(response => response.json())
      .then(restaurants => this.restaurantList = restaurants);
  }
  
  addCategory() {
    var toAdd = ""
    let client = new HttpClient();
    client.fetch('http://localhost:8080/restaurants/addFoodCategory/1', {
        'method': "POST",
        'body': json(this.foodCategoryData)
      })
      .then(response => response.json())
      .then(data => {
        client.fetch('http://localhost:8080/restaurants/1')
          .then(response => response.json())
          .then(data2 =>{
            console.log("category count= " + data2.foodCategories.length)
            deleteCategory(data2.foodCategories);
        });
      });

    console.log("Category Add executed!")

    function deleteCategory(data){
    var id = data[data.length-1].id
    toAdd = "<div class='row' id='row"+ id + "'>" +
      "<div class='col-sm-12 col-xs-12 well well-sm text-center text-info item'>" +
      "<div class='col-sm-12 col-xs-12 text-center'>" +
      data[data.length-1].categoryName +
      "<button type='button' id='btn"+id+"' class='categoryBtn btn btn-danger pull-right' " +
      "click.delegate='deleteCategory(foodCategory.name)'>" +
      "Delete" +
      "</button>" +
      "</div>" +
      "</div>" +
      "</div>";

    $(".categories").prepend(toAdd);

    $("#btn"+id).bind( "click", function() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/foodCategories/' + id)
      .then(response => response.json())
      .then(foodCategory => this.foodCategory = foodCategory);

    client.fetch('http://localhost:8080/foodCategories/'+ id, {
        'method': "DELETE",
        'body': json(this.foodCategory)
      });
    $("#row"+ id).slideUp(100, function(){ $(this).remove();});
    console.log("Category Delete executed!")
    });
    }

  }

  addFood() {
    console.log("addFood")
    let client = new HttpClient();
    client.fetch('http://localhost:8080/foodCategories/addFood/1', {
        'method': "POST",
        'body': json(this.foodData)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.name);
      });

    console.log("Method executed!")

    var toAdd = '<div class="row" repeat.for="food of foodList">' +
      '<div class="col-sm-12 col-xs-12 well well-sm text-center text-info item">' +
      '<div class="col-sm-10 col-xs-10 text-center">' +
      this.foodData.description +
      '</div>' +
      '<div class="col-sm-2 col-xs-2 well well-sm text-center text-danger rounded-0">' +
      this.foodData.price +
      '<span class="glyphicon glyphicon-euro"></span>' +
      '</div>' +
      '</div>' +
      '</div>';

    $(".food").prepend($(toAdd).fadeIn('slow'));

  }

  deleteCategory(id) {
    console.log("delete")
    let client = new HttpClient();

    client.fetch('http://localhost:8080/foodCategories/' + id)
      .then(response => response.json())
      .then(foodCategory => this.foodCategory = foodCategory);

    client.fetch('http://localhost:8080/foodCategories/'+ id, {
        'method': "DELETE",
        'body': json(this.foodCategory)
      });
    $("#" + id).slideUp(100, function(){ $(this).remove();});

    console.log("Method executed!")

  }

  editCategory(id){
    let client = new HttpClient();
    if($("#btn"+id).val() == "edit"){
      editCategory(id);
    }else{
      saveCategory(id);
    }
 
    function editCategory(id){
      client.fetch('http://localhost:8080/foodCategories/' + id)
        .then(response => response.json())
        .then(data => {
          $("#btn"+id).html('Save<span class="glyphicon glyphicon-check"></span>');
          $("#btn"+id).toggleClass('btn-primary btn-success');
          $("#btn"+id).attr("value", "save");
          $("#editCategory"+id).html("<div class='form-group'><input type='text' class='form-control input-sm' id='input"+id+"' value='"+data.categoryName+"'></div>");
        });
    }

    function saveCategory(id){
      var foodCategory = $("#input"+id).val();
      let foodCategorySave = { categoryName: foodCategory};
      client.fetch('http://localhost:8080/foodCategories/'+ id, {
        'method': "PUT",
        'body': json(foodCategorySave)
      });
      console.log(id)
      $("#btn"+id).html('Edit<span class="glyphicon glyphicon-edit"></span>');
      $("#btn"+id).toggleClass('btn-success btn-primary');
      $("#btn"+id).attr("value", "edit");
      $("#editCategory"+id).html(foodCategory);
      $("#"+id).hide().fadeIn(500);
    }
  }
  

}

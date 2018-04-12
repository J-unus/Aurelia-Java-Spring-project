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
    let client = new HttpClient();
    client.fetch('http://localhost:8080/restaurants/addFoodCategory/1', {
        'method': "POST",
        'body': json(this.foodCategoryData)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.name);
      });

    console.log("Method executed!")

    var toAdd = "<div class='row'" +
      "repeat.for='food of foodList'>" +
      "<div class='col-sm-12 col-xs-12 well well-sm text-center text-info item'>" +
      "<div class='col-sm-12 col-xs-12 text-center'>" +
      this.foodCategoryData.categoryName +
      "</div>" +
      "</div>" +
      "</div>";

    $(".categories").prepend($(toAdd).fadeIn('slow'));

  }

  addFood() {
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

}

import { HttpClient, json } from 'aurelia-fetch-client'

export class home {

    foodData = {}
    foodList = []
    foodCategoryData = {}
    foodCategoryList = []
    restaurantData = {}
    restaurantList = []
    userData = {}

  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/foodCategories')
      .then(response => response.json())
      .then(foodCategories => this.foodCategoryList = foodCategories);
    client.fetch('http://localhost:8080/foods')
      .then(response => response.json())
      .then(foods => this.foodList = foods);
    client.fetch('http://localhost:8080/restaurants')
      .then(response => response.json())
      .then(restaurants => this.restaurantList = restaurants);
  }

  addUser() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/users/add', {
        'method': "POST",
        'body': json(this.userData)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.name);
      });

    console.log("Method executed!")

  }

}

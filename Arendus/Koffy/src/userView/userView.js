import { HttpClient, json } from 'aurelia-fetch-client'

export class userView {

  foodData = {}
  foodList = []
  foodCategoryData = {}
  foodCategoryList = []
  restaurantData = {}
  restaurantList = []

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

  restaurantLike(id){
    let client = new HttpClient();
    let restaurantLike = { rating: true };
    client.fetch('http://localhost:8080/restaurantLikes/add?user_id=4&restaurant_id='+id, {
        'method': "POST",
        'body': json(restaurantLike)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.id);
      });
  }
  restaurantDislike(id){
    let client = new HttpClient();
    let restaurantDislike = { rating: false };
    client.fetch('http://localhost:8080/restaurantLikes/add?user_id=4&restaurant_id='+id, {
        'method': "POST",
        'body': json(restaurantDislike)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.id);
      });
  }

  foodLike(id){
    let client = new HttpClient();
    let foodLike = { rating: true };
    client.fetch('http://localhost:8080/foodLikes/add?user_id=4&food_id='+id, {
        'method': "POST",
        'body': json(foodLike)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.id);
      });
  }
  foodDislike(id){
    let client = new HttpClient();
    let foodDislike = { rating: false };
    client.fetch('http://localhost:8080/foodLikes/add?user_id=4&food_id='+id, {
        'method': "POST",
        'body': json(foodDislike)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.id);
      });
  }

}

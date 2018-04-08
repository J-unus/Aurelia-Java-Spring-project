import { HttpClient, json } from 'aurelia-fetch-client'

export class restaurantAdd {

  restaurantData = {}
  restaurantList = []

  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/restaurants')
      .then(response => response.json())
      .then(restaurants => this.restaurantList = restaurants);
  }

  addRestaurant() {
    let client = new HttpClient();
    client.fetch('http://localhost:8080/restaurants/add', {
        'method': "POST",
        'body': json(this.restaurantData)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.name);
      });

    console.log("Method executed!")
  }
}

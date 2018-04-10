//import { HttpClient, json } from 'aurelia-fetch-client'
export class foodAdd {
        addTab(){
          var tabID = $('.tab-pane').length;
          console.log(tabID);
          tabID++;
          $('#tab-list').append($('<li><a href="#tab' + tabID + '" role="tab" data-toggle="tab">Tab ' + tabID + '<button class="close" type="button" title="Remove this page">×</button></a></li>'));
          $('#tab-content').append($('<div class="tab-pane fade" id="tab' + tabID + '">Tab ' + tabID + ' content</div>'));
        }




  /*
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
    client.fetch('http://localhost:8080/users/addRestaurant/1', {
        'method': "POST",
        'body': json(this.restaurantData)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.name);
      });

    console.log("Method executed!")
  }
  */
}
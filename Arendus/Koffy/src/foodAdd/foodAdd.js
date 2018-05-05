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
          .then(data2 => {
            console.log("category count= " + data2.foodCategories.length)
            deleteCategory(data2.foodCategories);
          });
      });

    console.log("Category Add executed!")

    function deleteCategory(data) {
      var id = data[data.length - 1].id
      var insertCategory = $($.parseHTML(data[data.length - 1].categoryName)).text();
      console.log("insertCategory =" + insertCategory)
      toAdd = "<div class='row' id='row" + id + "'>" +
        "<div class='col-sm-12 col-xs-12 well well-sm text-center text-info item'>" +
        "<div class='col-sm-12 col-xs-12 text-center'>" +
        "<span class='col-md-8 col-xs-8' id='editCategory" + id + "'>" +
        insertCategory +
        "</span>" +
        "<button type='button' id='categoryDeleteBtn" + id + "' class='categoryBtn btn btn-danger pull-right' " +
        "click.delegate='deleteCategory(foodCategory.name)'>" +
        "Delete " +
        "<span class='glyphicon glyphicon-trash'></span>" +
        "</button>" +
        "<button type='button' id='categoryEditBtn" + id + "' value='edit' class='btn btn-primary pull-right' " +
        "click.delegate='editCategory(foodCategory.id)'>" +
        "Edit " +
        "<span class='glyphicon glyphicon-edit'></span>" +
        "</button>"
      "</div>" +
      "</div>" +
      "</div>";

      $(".categories").prepend(toAdd);

      $("#categoryDeleteBtn" + id).bind("click", function() {
        let client = new HttpClient();

        client.fetch('http://localhost:8080/foodCategories/' + id)
          .then(response => response.json())
          .then(foodCategory => this.foodCategory = foodCategory);

        client.fetch('http://localhost:8080/foodCategories/' + id, {
          'method': "DELETE",
          'body': json(this.foodCategory)
        });
        $("#row" + id).slideUp(100, function() { $(this).remove(); });
        console.log("Category Delete executed!")
      });

      $("#categoryEditBtn" + id).bind("click", function() {
        let client = new HttpClient();
        if ($("#categoryEditBtn" + id).val() == "edit") {
          console.log("edit")
          editCategory(id);
        } else {
          console.log("save")
          saveCategory(id);
        }

        function editCategory(id) {
          client.fetch('http://localhost:8080/foodCategories/' + id)
            .then(response => response.json())
            .then(data => {
              $("#categoryEditBtn" + id).html('Save<span class="glyphicon glyphicon-check"></span>');
              $("#categoryEditBtn" + id).toggleClass('btn-primary btn-success');
              $("#categoryEditBtn" + id).attr("value", "save");
              $("#editCategory" + id).html("<div class='form-group'><input type='text' class='form-control input-sm' id='input" + id + "' value='" + data.categoryName + "'></div>");
            });
          console.log("editCategory with id = " + id)
        }

        function saveCategory(id) {
          var foodCategory = $("#input" + id).val();
          let foodCategorySave = { categoryName: foodCategory };
          client.fetch('http://localhost:8080/foodCategories/' + id, {
            'method': "PUT",
            'body': json(foodCategorySave)
          });
          console.log(id)
          $("#categoryEditBtn" + id).html('Edit<span class="glyphicon glyphicon-edit"></span>');
          $("#categoryEditBtn" + id).toggleClass('btn-success btn-primary');
          $("#categoryEditBtn" + id).attr("value", "edit");
          $("#editCategory" + id).html(foodCategory);
          $("#" + id).hide().fadeIn(500);
        }
      });

    }

  }

  addFood(id) {
    console.log("addFood")
    let client = new HttpClient();
    client.fetch('http://localhost:8080/foodCategories/addFood/' + id, {
        'method': "POST",
        'body': json(this.foodData)
      })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.name);
      });

    console.log("Method executed!")

    var toAdd = "<div class='row' repeat.for='food of foodCategory.foods' id='food${food.id}'>" +
      "<div class='col-sm-12 col-xs-12 well well-sm text-center text-info item'>" +
      "<div class='col-sm-2 col-xs-2 well well-sm text-center text-danger rounded-0'>" +
      this.foodData.price +
      "<span class='glyphicon glyphicon-euro'></span>" +
      "</div>" +
      "<div class='col-sm-8 col-xs-8 text-center'>" +
      this.foodData.title +
      "</div>" +
      "<div class='col-sm-8 col-xs-8 text-center'>" +
      this.foodData.description +
      "</div>" +
      "<div class='col-sm-2 col-xs-2 text-center'>" +
      "<button type='button' id='btn${foodCategory.id}' value='edit' " +
      "class='col-sm-12 col-xs-12 btn btn-primary pull-right' " +
      "click.delegate='editFood(food.id)''>" +
      "Edit" +
      "<span class='glyphicon glyphicon-edit'</span>" +
      "</button>" +
      "<button type='button' class='btn btn-danger pull-right col-sm-12 col-xs-12' " +
      "click.delegate='deleteFood(food.id)''>" +
      "Delete" +
      "<span class='glyphicon glyphicon-trash'></span>" +
      "</button>" +
      "</div>" +
      "</div>" +
      "</div>" ;

    $(".food" + id).prepend($(toAdd).fadeIn('slow'));

  }

  deleteCategory(id) {
    console.log("category delete")
    let client = new HttpClient();

    client.fetch('http://localhost:8080/foods/' + id)
      .then(response => response.json())
      .then(food => this.food = food);

    client.fetch('http://localhost:8080/foods/' + id, {
      'method': "DELETE",
      'body': json(this.food)
    });
    $("#food" + id).slideUp(100, function() { $(this).remove(); });

    console.log("Method executed!")

  }

  deleteFood(id) {
    console.log("food delete")
    let client = new HttpClient();

    client.fetch('http://localhost:8080/foods/' + id)
      .then(response => response.json())
      .then(foodCategory => this.foodCategory = foodCategory);

    client.fetch('http://localhost:8080/foods/' + id, {
      'method': "DELETE"
    });
    $("#food" + id).slideUp(100, function() { $(this).remove(); });

    console.log("Method executed!")

  }

  editCategory(id) {
    let client = new HttpClient();
    if ($("#btn" + id).val() == "edit") {
      editCategory(id);
    } else {
      saveCategory(id);
    }

    function editCategory(id) {
      client.fetch('http://localhost:8080/foodCategories/' + id)
        .then(response => response.json())
        .then(data => {
          $("#btn" + id).html('Save<span class="glyphicon glyphicon-check"></span>');
          $("#btn" + id).toggleClass('btn-primary btn-success');
          $("#btn" + id).attr("value", "save");
          $("#editCategory" + id).html("<div class='form-group'><input type='text' class='form-control input-sm' id='input" + id + "' value='" + data.categoryName + "'></div>");
        });
    }

    function saveCategory(id) {
      var foodCategory = $("#input" + id).val();
      let foodCategorySave = { categoryName: foodCategory };
      client.fetch('http://localhost:8080/foodCategories/' + id, {
        'method': "PUT",
        'body': json(foodCategorySave)
      });
      console.log(id)
      $("#btn" + id).html('Edit<span class="glyphicon glyphicon-edit"></span>');
      $("#btn" + id).toggleClass('btn-success btn-primary');
      $("#btn" + id).attr("value", "edit");
      $("#editCategory" + id).html(foodCategory);
      $("#" + id).hide().fadeIn(500);
    }
  }

  editFood(id) {
    let client = new HttpClient();
    if ($("#foodbtn" + id).val() == "edit") {
      console.log("editfood2")
      editFood(id);
    } else {
      console.log("savefood")
      saveFood(id);
    }

    function editFood(id) {
      client.fetch('http://localhost:8080/foods/' + id)
        .then(response => response.json())
        .then(data => {
          $("#foodbtn" + id).html('Save<span class="glyphicon glyphicon-check"></span>');
          $("#foodbtn" + id).toggleClass('btn-primary btn-success');
          $("#foodbtn" + id).attr("value", "save");
          $("#editFoodTitle" + id).html("<div class='form-group'><input type='text' class='form-control input-sm' id='foodTitle" + id + "' value='" + data.title + "'></div>");
          $("#editFoodDescription" + id).html("<div class='form-group'><input type='text' class='form-control input-sm' id='foodDescription" + id + "' value='" + data.description + "'></div>");
          $("#editFoodPrice" + id).html("<div class='form-group'><input type='number' class='form-control input-sm' id='foodPrice" + id + "' value='" + data.price + "'></div>");
        });
    }

    function saveFood(id) {
      var foodTitle = $("#foodTitle" + id).val();
      var foodDescription = $("#foodDescription" + id).val();
      var foodPrice = $("#foodPrice" + id).val();
      let foodSave = {description: foodDescription,price: foodPrice,title: foodTitle };
      client.fetch('http://localhost:8080/foods/' + id, {
        'method': "PUT",
        'body': json(foodSave)
      });
      $("#foodbtn" + id).html('Edit<span class="glyphicon glyphicon-edit"></span>');
      $("#foodbtn" + id).toggleClass('btn-success btn-primary');
      $("#foodbtn" + id).attr("value", "edit");
      $("#editFoodTitle" + id).html(foodTitle);
      $("#editFoodDescription" + id).html(foodDescription);
      $("#editFoodPrice" + id).html(foodPrice+"<span class='glyphicon glyphicon-euro'></span>");
    }
  }


}

export class App {
  constructor() {
    this.message = 'Hello World!';
  }
  configureRouter(config, router) {
    this.router = router;
    config.title = 'Koffy';
    config.map([
      { route: ['', 'home'], name: 'home', moduleId: 'home/index' },
      { route: 'contact', name: 'contact', moduleId: 'contact/index', nav: true, title: 'Contact' },
      { route: 'about', name: 'about', moduleId: 'about/about', nav: true, title: 'About' },
      { route: 'restaurantAdd', name: 'restaurantAdd', moduleId: 'restaurantAdd/restaurantAdd', nav: true, title: 'restaurantAdd' },
      { route: 'foodAdd', name: 'foodAdd', moduleId: 'foodAdd/foodAdd', nav: true, title: 'foodAdd' }
    ]);
  }
}

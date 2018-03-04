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
      { route: 'about', name: 'about', moduleId: 'about/index', nav: true, title: 'About' }
    ]);
  }
}

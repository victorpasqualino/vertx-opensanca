
//Adiciona um consumidor ao endereço `news-feed` 
vertx.eventBus().consumer("news-feed").handler(function(message) {
	console.log('Received news: ' + message.body());
});